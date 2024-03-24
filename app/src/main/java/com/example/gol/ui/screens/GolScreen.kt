package com.example.gol.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gol.GolViewModelInterface
import com.example.gol.logic.GolStarter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GolScreen(onSettingsClicked: ()->Unit, viewModel: GolViewModelInterface) {
    Surface(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)
        ),
        color = MaterialTheme.colorScheme.onBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            val gridItems = viewModel.items.collectAsStateWithLifecycle(initialValue = listOf())
            val isGameRunning = viewModel.isGameRunning.collectAsStateWithLifecycle(initialValue = false)
            LazyVerticalGrid(
                columns = GridCells.Fixed(viewModel.itemsColumn)
            ) {
                items(gridItems.value) { isAlive: Boolean ->
                    GolTile(isAlive)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onSettingsClicked) {
                    Text(text = "Settings")
                }
                Button(onClick = {
                    if (isGameRunning.value) {
                        viewModel.pauseGame()
                    } else {
                        viewModel.resumeGame()
                    }
                }) {
                    Text(text = if(isGameRunning.value) "Pause" else "Run")
                }
            }
        }
    }
}

@Composable
fun GolTile(isAlive: Boolean) {
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .width(8.dp)
            .background(color = if (isAlive) Color.Black else Color.Magenta)
    )
}

@Preview
@Composable
fun GolScreenPreview() {
    val starter = GolStarter.pulsar
   GolScreen(onSettingsClicked = { /* no-op */ }, object : GolViewModelInterface {
       override val isGameRunning: StateFlow<Boolean>
           get() = MutableStateFlow(false)
       override val items: SharedFlow<List<Boolean>>
           get() = MutableStateFlow(starter.data.flatten())
       override val itemsColumn: Int
           get() = starter.nubCols

       override fun pauseGame() { /* no-op */ }

       override fun resumeGame() { /* no-op */ }
   })
}