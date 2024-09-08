package com.niranjan.khatri.androidcomposetutorial.hexagon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import coil.compose.AsyncImage


data class Game(val title: String, val coverImageUrl: String)

val sampleGames = listOf(
    Game("Game 1", "https://picsum.photos/200/300"),
    Game("Game 2", "https://picsum.photos/300/400"),
    Game("Game 3", "https://picsum.photos/400/500"),
    Game("Game 4", "https://picsum.photos/500/600"),
    Game("Game 5", "https://picsum.photos/600/700"),
    Game("Game 6", "https://picsum.photos/700/800"),
    // Add more games here
)

@Composable
fun GameCatalogScreen(games: List<Game>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy((-60).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(games) { game ->
            GameTile(game = game)
        }
    }
}

@Composable
fun GameTile(game: Game) {
    Box(
        modifier = Modifier
            .width(0.55f * LocalConfiguration.current.screenWidthDp.dp)
            .height(200.dp)
            .clip(HexagonShape)
            .background(Color.LightGray)
    ) {
        AsyncImage(
            model = game.coverImageUrl,
            contentDescription = game.title,
            modifier = Modifier.fillMaxSize()
        )
    }
}

private val HexagonShape = GenericShape { size, _ ->
    val radius = size.minDimension / 2f
    (0..5).forEach { i ->
        val angle = 2.0 * Math.PI * i / 6
        val x =radius * (1 + Math.cos(angle)).toFloat()
        val y = radius * (1 + Math.sin(angle)).toFloat()
        if (i == 0) {
            moveTo(x, y)
        } else {
            lineTo(x, y)
        }
    }
    close()
}

@Preview
@Composable
fun GamePreview(){
    GameCatalogScreen(games = sampleGames)
}