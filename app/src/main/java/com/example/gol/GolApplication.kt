package com.example.gol

import android.app.Application
import com.example.gol.logic.BaseGolStarter
import com.example.gol.logic.GolStarter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GolApplication : Application() {

    private var _gameStarterState = MutableStateFlow<GolStarter>(BaseGolStarter.infinite_glider)
    val gameStarterState: StateFlow<GolStarter> = _gameStarterState

    suspend fun updateStarter(starter: GolStarter) {
        _gameStarterState.emit(starter)
    }
}

val Application.gameStarterState: StateFlow<GolStarter>
    get() = (this as GolApplication).gameStarterState
