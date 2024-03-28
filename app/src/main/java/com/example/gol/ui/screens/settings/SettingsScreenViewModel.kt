package com.example.gol.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gol.GolApplication
import com.example.gol.logic.BaseGolStarter
import kotlinx.coroutines.launch

interface SettingsViewModelInterface {
    val starters: List<BaseGolStarter>
    fun selectStarter(starter: BaseGolStarter)
}

class SettingsScreenViewModel(application: Application) : AndroidViewModel(application), SettingsViewModelInterface {
    override val starters = BaseGolStarter.starters

    override fun selectStarter(starter: BaseGolStarter) {
        viewModelScope.launch {
            getApplication<GolApplication>().updateStarter(starter)
        }
    }
}
