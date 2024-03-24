package com.example.gol.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gol.GolApplication
import com.example.gol.logic.GolStarter
import kotlinx.coroutines.launch

interface SettingsViewModelInterface {
    val starters: List<GolStarter>
    fun selectStarter(starter: GolStarter)
}

class SettingsScreenViewModel(application: Application) : AndroidViewModel(application), SettingsViewModelInterface {
    override val starters = GolStarter.starters

    override fun selectStarter(starter: GolStarter) {
        viewModelScope.launch {
            getApplication<GolApplication>().updateStarter(starter)
        }
    }
}
