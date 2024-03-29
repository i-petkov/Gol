package com.example.gol.ui.screens.gol

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gol.logic.BaseGolStarter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GolScreen(onSettingsClicked: ()->Unit, viewModel: GolViewModelInterface) {
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val gridItems = viewModel.items.collectAsStateWithLifecycle(initialValue = listOf())
        val gridColumns = viewModel.itemsColumn.collectAsStateWithLifecycle(initialValue = 0)
        val isGameRunning = viewModel.isGameRunning.collectAsStateWithLifecycle(initialValue = false)
        val gameSpeed = viewModel.gameSpeed.collectAsStateWithLifecycle(initialValue = Speed.Normal)
        val basePatternName = viewModel.basePatternName.collectAsStateWithLifecycle(initialValue = "Loading...")

        val isValid = gridColumns.value > 0

        Text(text = buildAnnotatedString {
            append("Using Base Pattern: ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.surfaceTint)) {
                append(basePatternName.value)
            }
        })

        if (isValid) {

            // approximate size to fill screen (360 dp)
            val tileSize = 360.0 / gridColumns.value

            LazyVerticalGrid(
                modifier = Modifier.width((gridColumns.value * tileSize).dp),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center,
                columns = GridCells.Fixed(gridColumns.value)
            ) {
                items(gridItems.value) { isAlive: Boolean ->
                    GolTile(isAlive, tileSize.dp)
                }
            }
        } else {
            Spacer(modifier = Modifier
                .width(360.dp)
                .height(280.dp)
                .background(color = MaterialTheme.colorScheme.background)
            )
        }

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp),
            ) {
                Button(
                    onClick = { viewModel.setSpeed(Speed.Slow) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    enabled = isValid && gameSpeed.value != Speed.Slow,
                ) {
                    Text(text = "Slow")
                }

                Button(
                    onClick = { viewModel.setSpeed(Speed.Normal) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    enabled = isValid && gameSpeed.value != Speed.Normal
                ) {
                    Text(text = "Normal")
                }

                Button(
                    onClick = { viewModel.setSpeed(Speed.Fast) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    enabled = isValid && gameSpeed.value != Speed.Fast
                ) {
                    Text(text = "Fast")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.stop()
                        onSettingsClicked()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)
                ) {
                    Text(text = "Settings")
                }
                Spacer(modifier = Modifier
                    .weight(1f)
                    .padding(6.dp),)
                Button(
                    onClick = {
                        if (isGameRunning.value == GameActiveState.Running) {
                            viewModel.pauseGame()
                        } else {
                            viewModel.resumeGame()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp),
                    enabled = isValid
                ) {
                    Text(text = if(isGameRunning.value == GameActiveState.Running) "Pause" else "Run")
                }
            }
        }
    }
}

@Composable
fun GolTile(isAlive: Boolean, size: Dp) {
    Spacer(
        modifier = Modifier
            .height(size)
            .width(size)
            .background(
                color = if (isAlive) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.inversePrimary
                }
            )
    )
}

@Preview
@Composable
fun GolScreenPreview() {
    val starter = BaseGolStarter.infinite_glider
   GolScreen(onSettingsClicked = { /* no-op */ }, object : GolViewModelInterface {
       override val gameSpeed: StateFlow<Speed>
           get() = MutableStateFlow(Speed.Normal)
       override val isGameRunning: StateFlow<GameActiveState>
           get() = MutableStateFlow(GameActiveState.Stopped)
       override val items: StateFlow<List<Boolean>>
           get() = MutableStateFlow(starter.data.flatten())
       override val itemsColumn: StateFlow<Int>
           get() = MutableStateFlow(starter.numCols)
       override val basePatternName: StateFlow<String>
           get() = MutableStateFlow("Loading...")

       override fun pauseGame() { /* no-op */ }

       override fun resumeGame() { /* no-op */ }

       override fun setSpeed(speed: Speed) { /* no-op */ }

       override fun stop() { /* no-op */ }
   })
}