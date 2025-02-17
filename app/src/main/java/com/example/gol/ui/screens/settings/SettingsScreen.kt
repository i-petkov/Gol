package com.example.gol.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gol.logic.BaseGolStarter

@Composable
fun SettingsScreen(onBackPressed: ()-> Unit, viewModel: SettingsViewModelInterface) {
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 12.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            text = "Select a Pattern"
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
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
            modifier = Modifier.padding(bottom = 12.dp, top = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.scrim)
        ) {
            Text(text = "Cancel")
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onBackPressed = { /* no op */ }, object : SettingsViewModelInterface {
        override val starters: List<BaseGolStarter> = BaseGolStarter.starters
        override fun selectStarter(starter: BaseGolStarter) { /* no op */ }
    })
}
