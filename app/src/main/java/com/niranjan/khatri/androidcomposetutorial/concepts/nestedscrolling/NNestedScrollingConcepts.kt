package com.niranjan.khatri.androidcomposetutorial.concepts.nestedscrolling

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import com.niranjan.khatri.androidcomposetutorial.R
import kotlin.math.abs

@Composable
fun NestedScrollingScreen(){
    val nestedScrollConnection =
        remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset {
                    // Consume scroll delta and update UI elements
                    // Consume vertical scroll events when the sheet is expanded
                    val delta = available.y
                    // ... (Calculate new image size, alpha, FAB offset)
                    return Offset(0f, available.y) // Return consumed delta
                }
                // ... (Optional: Implement other callbacks like postScroll)
            }
        }


    Box(
        modifier = Modifier.nestedScroll(nestedScrollConnection)) {
        // Child composables (Image, List, FAB)
    }
}


@Composable
fun NestedScrollingExample() {
    val maxImageSize = 300.dp
    val minImageSize = 100.dp
    var imageSize by remember { mutableStateOf(maxImageSize) }
    var imageAlpha by remember { mutableStateOf(1f) }
    var fabOffset by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    val imageSizePercentage by remember {
        derivedStateOf {(imageSize - minImageSize) / (maxImageSize - minImageSize)
        }
    }
    val threshold = 50f // threshold value

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                val delta = available.y
                if (abs(delta) > threshold) {
                    val newSize = imageSize + delta.dp
                    imageSize = max(minImageSize, min(maxImageSize, newSize))
                    val consumed = newSize - imageSize

                    imageAlpha = imageSizePercentage.toFloat()
                    fabOffset = (1 - imageSizePercentage) * 100

                    return Offset(0f, consumed.value * density.density)
                } else {
                    return Offset.Zero // No consumption if delta is below threshold
                }
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (available.y > 0) { // Check if there's unconsumed scroll in the upward direction
                    val newSize = imageSize + available.y.dp
                    imageSize = max(minImageSize, min(maxImageSize, newSize))
                    val consumedDelta = newSize - imageSize
                    return Offset(0f, consumedDelta.value * density.density)
                }
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Image(
            // Replace with your image
            painter = painterResource(id = R.drawable.ic_car),
            contentDescription = "Podcast Image",
            modifier = Modifier
                .height(imageSize)
                .fillMaxWidth()
                .alpha(imageAlpha)
                .graphicsLayer {
                    scaleX = imageSize.value / maxImageSize.value
                    scaleY = imageSize.value / maxImageSize.value
                }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = imageSize)
        ) {
            items(podcastList) { podcast ->
                PodcastCard(podcast)
            }
            item {
                Spacer(modifier = Modifier.height(maxImageSize - imageSize + 20.dp))
            }
        }
        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .offset { IntOffset(0, fabOffset.toInt()) }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}

data class Podcast(val title: String, val author: String)

val podcastList = listOf(
    Podcast("Episode 1", "Author A"),
    Podcast("Episode 2", "Author B"),
    Podcast("Episode 3", "Author A"),
    Podcast("Episode 4", "Author B"),
    Podcast("Episode 5", "Author C"),
    Podcast("Episode 6", "Author D"),
    Podcast("Episode 7", "Author E"),
    Podcast("Episode 8", "Author A"),
    Podcast("Episode 9", "Author B"),
    Podcast("Episode 10", "Author C"),
    Podcast("Episode 11", "Author D"),
)

@Composable
fun PodcastCard(podcast: Podcast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(podcast.title, style = MaterialTheme.typography.headlineLarge)
            Text(podcast.author, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun NestedScrollingExample_Preview(){
    NestedScrollingExample()
}