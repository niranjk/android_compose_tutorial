package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.carousel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(
    images: List<String>, // List of image resources
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Carousel Demo", style = LocalTypography.current.headlineLarge)
        Spacer(modifier = Modifier.size(32.dp))
        HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = modifier.fillMaxWidth(),
            itemSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 64.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        // Calculate scale based on page offset
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth()
                    .aspectRatio(1f),
                shape = RoundedCornerShape(20.dp)
            ) {
                AsyncImage(
                    model = images[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                    Modifier
                        .fillMaxSize()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp),
            activeColor = Color.Blue,
            inactiveColor = Color.LightGray
        )
    }
}

val images = listOf(
    "https://picsum.photos/200/300",
    "https://picsum.photos/400/500",
    "https://picsum.photos/600/700",
)



@Preview
@Composable
fun Carousel_Preview(){
    ImageCarousel(images = images)
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun Accessibility_Carousel(){
    // This is a conceptual example, actual implementation may vary
    Box(
        modifier = Modifier.semantics {
            role = Role.DropdownList
            // ... other semantics properties
        }
    ) {
       HorizontalPager(count = images.size) { page ->
            Box(
                modifier = Modifier.semantics {
                    role = Role.DropdownList
                    // ... other semantics properties
                }
            ) {
                // Carousel item content
            }
        }
    }
}


