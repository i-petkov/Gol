package com.example.gol.ui.screens.gol

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gol.GolApplication
import com.example.gol.logic.GolStarter
import com.example.gol.logic.TileFactory
import com.example.gol.logic.evolve
import com.example.gol.logic.findBase
import com.example.gol.logic.toIntermediate
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

interface GolViewModelInterface {
    val gameSpeed: StateFlow<Speed>
    val isGameRunning: StateFlow<GameActiveState>
    val items: StateFlow<List<Boolean>>
    val itemsColumn: StateFlow<Int>
    val basePatternName: StateFlow<String>

    fun pauseGame()
    fun resumeGame()
    fun stop()
    fun setSpeed(speed: Speed)
}

enum class Speed(val delay: Long) {
    Slow(600), Normal(250), Fast(100)
}

enum class GameActiveState {
    Running, Paused, Stopped
}

class GolViewModel(application: Application) : AndroidViewModel(application), GolViewModelInterface {
    companion object {
        const val LOG_TAG = "GolViewModel"

        const val cancellationStateStop = "Stop"
        const val cancellationStateSuspended = "Suspended"
        const val cancellationStateError = "Error"
        const val cancellationStateNew = "New"

        const val gameStateLoading = "Loading..."
    }

    private val _isGameRunning: MutableStateFlow<GameActiveState> = MutableStateFlow(GameActiveState.Stopped)
    private val _items: MutableStateFlow<List<Boolean>> = MutableStateFlow(emptyList())
    private val _itemsColumn: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _gameSpeed: MutableStateFlow<Speed> = MutableStateFlow(Speed.Normal)
    private val _basePatternName: MutableStateFlow<String> = MutableStateFlow(gameStateLoading)

    override val isGameRunning: StateFlow<GameActiveState> = _isGameRunning
    override val items: StateFlow<List<Boolean>> = _items
    override val itemsColumn: StateFlow<Int> = _itemsColumn
    override val basePatternName: StateFlow<String> = _basePatternName
    override val gameSpeed: StateFlow<Speed> = _gameSpeed

//    val itemsCount: Int
//        get() = items.fold(0) { acc, items -> acc + items.size }

    private val tileFactory = object : TileFactory<Boolean> {
        override fun createAliveTile(): Boolean = true

        override fun createDeadTile(): Boolean = false

        override fun isTileAlive(tile: Boolean): Boolean = tile
    }

    override fun pauseGame() {
        _isGameRunning.value = GameActiveState.Paused
    }

    override fun resumeGame() {
        _isGameRunning.value = GameActiveState.Running
    }

    override fun stop() {
        _isGameRunning.value = GameActiveState.Stopped
    }

    override fun setSpeed(speed: Speed) {
        _gameSpeed.value = speed
    }

    init {
        viewModelScope.launch {
            var updateCycleJob: Job? = null
            getApplication<GolApplication>().gameStarterState.collectLatest { starter ->
                updateCycleJob?.cancel(CancellationException(cancellationStateNew))
                // load state
                _items.emit(starter.data.flatten())
                _itemsColumn.emit(starter.numCols)
                _basePatternName.emit(starter.findBase().name)
                // run game loop
                isGameRunning.collectLatest { gameActiveState ->
                    when (gameActiveState) {
                        GameActiveState.Stopped -> {
                            // cancel update cycle without persisting state
                            updateCycleJob?.cancel(CancellationException(cancellationStateStop))
                        }
                        GameActiveState.Paused -> {
                            // cancel update cycle and persist state
                            updateCycleJob?.cancel(CancellationException(cancellationStateSuspended))
                        }
                        GameActiveState.Running -> {
                            // start update cycle
                            updateCycleJob?.cancel(CancellationException(cancellationStateError))
                            updateCycleJob = startEvolutionFlowJob(starter)
                        }
                    }
                }
            }
        }
    }

    private fun startEvolutionFlowJob(starter: GolStarter): Job {
        return viewModelScope.launch { startEvolutionFlow(starter).collect() }
    }

    private fun startEvolutionFlow(starter: GolStarter): Flow<Unit> {
        var buffer = starter.data
        return flow<Unit> {
            while (true) {
                _items.emit(buffer.flatten())
                buffer = buffer.evolve(tileFactory)
                delay(gameSpeed.value.delay)
            }
        }.onCompletion {
            if (cancellationStateSuspended == it?.message) {
                getApplication<GolApplication>().updateStarter(starter.toIntermediate(buffer))
            } else if (cancellationStateError == it?.message) {
                Log.e(LOG_TAG, "Stale Evolution Flow canceled with an error, please investigate")
            } else if (cancellationStateNew == it?.message) {
                Log.e(LOG_TAG, "Stale Evolution Flow canceled with a NEW State")
            } else { // stopped
                getApplication<GolApplication>().updateStarter(starter.findBase())
            }
            _basePatternName.emit(gameStateLoading)
        }
    }
}
