package com.example.gol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gol.logic.GolStarter
import com.example.gol.logic.TileFactory
import com.example.gol.logic.evolve
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

interface GolViewModelInterface {
    val isGameRunning: StateFlow<Boolean>
    val items: SharedFlow<List<Boolean>>
    val itemsColumn: Int

    fun pauseGame()
    fun resumeGame()
}

class GolViewModel : ViewModel(), GolViewModelInterface {
    private val starter = GolStarter.infinite_glider
    private val _isGameRunning: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override val isGameRunning: StateFlow<Boolean> = _isGameRunning
    override val items: SharedFlow<List<Boolean>> = buildGameFlow(starter.data) { isGameRunning.value }
        .shareIn(viewModelScope, replay = 1, started = SharingStarted.WhileSubscribed())
    override val itemsColumn: Int = starter.nubCols

//    val itemsCount: Int
//        get() = items.fold(0) { acc, items -> acc + items.size }

    private val tileFactory = object : TileFactory<Boolean> {
        override fun createAliveTile(): Boolean = true

        override fun createDeadTile(): Boolean = false

        override fun isTileAlive(tile: Boolean): Boolean = tile
    }

    override fun pauseGame() {
        _isGameRunning.compareAndSet(expect = true, false)
    }

    override fun resumeGame() {
        _isGameRunning.compareAndSet(expect = false, true)
    }

    private fun buildGameFlow(data: List<List<Boolean>>, isPlaying:()->Boolean): Flow<List<Boolean>> {
        return flow {
            var buffer = data
            emit(buffer.flatten())
            while (true) {
                if (isPlaying()) {
                    emit(buffer.flatten())
                    buffer = buffer.evolve(tileFactory)
                }
                delay(250)
            }
        }
    }
}