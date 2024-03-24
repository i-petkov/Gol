package com.example.gol.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.gol.logic.GolStarter

@Composable
fun SettingsScreen(onBackPressed: ()-> Unit, viewModel: SettingsViewModelInterface) {
    Column(horizontalAlignment = Alignment.End) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewModel.starters) {
                Button(
                    onClick = {
                        viewModel.selectStarter(it)
                        onBackPressed()
                    }
                ) {
                    Text(text = it.name)
                }
            }
        }
        Button(
            onClick = { onBackPressed() },
            modifier = Modifier.padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Black)
        ) {
            Text(text = "Cancel")
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBackPressed = { /* no op */ }, object : SettingsViewModelInterface {
        override val starters: List<GolStarter> = GolStarter.starters
        override fun selectStarter(starter: GolStarter) { /* no op */ }
    })
}

interface SettingsViewModelInterface {
    val starters: List<GolStarter>
    fun selectStarter(starter: GolStarter)
}

class SettingsScreenViewModel : ViewModel(), SettingsViewModelInterface {
    override val starters = GolStarter.starters

    override fun selectStarter(starter: GolStarter) {

    }
}