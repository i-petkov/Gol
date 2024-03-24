package com.example.gol.ui.screens.gol

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gol.gameStarterState
import com.example.gol.logic.TileFactory
import com.example.gol.logic.evolve
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

interface GolViewModelInterface {
    val gameSpeed: StateFlow<Speed>
    val isGameRunning: StateFlow<Boolean>
    val items: StateFlow<List<Boolean>>
    val itemsColumn: StateFlow<Int>

    fun pauseGame()
    fun resumeGame()
    fun setSpeed(speed: Speed)
}

enum class Speed(val delay: Long) {
    Slow(600), Normal(250), Fast(100)
}

class GolViewModel(application: Application) : AndroidViewModel(application), GolViewModelInterface {
    private val _isGameRunning: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _items: MutableStateFlow<List<Boolean>> = MutableStateFlow(emptyList())
    private val _itemsColumn: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _gameSpeed: MutableStateFlow<Speed> = MutableStateFlow(Speed.Normal)

    override val isGameRunning: StateFlow<Boolean> = _isGameRunning
    override val items: StateFlow<List<Boolean>> = _items
    override val itemsColumn: StateFlow<Int> = _itemsColumn
    override val gameSpeed: StateFlow<Speed> = _gameSpeed

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

    override fun setSpeed(speed: Speed) {
        _gameSpeed.value = speed
    }

    init {
        viewModelScope.launch {
            application.gameStarterState.collectLatest {
                var buffer = it.data
                _items.emit(buffer.flatten())
                _itemsColumn.emit(it.numCols)
                while (true) {
                    if (isGameRunning.value) {
                        _items.emit(buffer.flatten())
                        buffer = buffer.evolve(tileFactory)
                    }
                    delay(gameSpeed.value.delay)
                }
            }
        }
    }
}