package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.pager

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPager() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val pagerState = rememberPagerState(pageCount = { colors.size })

    val coroutineScope = rememberCoroutineScope()


    val customFlingBehavior = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(2),
        lowVelocityAnimationSpec = tween(
            easing = FastOutLinearInEasing,
            durationMillis = 3000
        ),
        highVelocityAnimationSpec = rememberSplineBasedDecay(),
        snapAnimationSpec = tween(
            easing = FastOutSlowInEasing,
            durationMillis = 1000
        ),
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .weight(1f)
                    .background(Color.White),
                 contentPadding = PaddingValues(20.dp),
                 beyondBoundsPageCount = 1, // You can use beyondBoundsPageCount to place more pages before and after the visible pages.
                flingBehavior = customFlingBehavior
                // pageSize = PageSize.Fixed(170.dp)
            ) { page ->
                // Our page content
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(colors[page]),
                    contentAlignment = Alignment.Center){
                    Text(
                        text = "Pager ${page +1 }",
                        style = LocalTypography.current.headlineLarge
                    )
                }
            }
            // Horizontal Pager Indicator ...
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(16.dp)
                    )
                }
            }
        }
    }
    // scroll to page
    Button(
        modifier = Modifier.padding(start = 15.dp),
        onClick = {
        coroutineScope.launch {
            // Call scroll to on pagerState
            pagerState.animateScrollToPage(3)
        }
    }, ) {
        Text("Jump to Page 3")
    }
}

@Preview
@Composable
fun MyPager_Preview(){
    MyPager()
}

// Pager Tips
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyHorizontalPager() {
    val pagerState = rememberPagerState(pageCount = {5})
    HorizontalPager(pagerState) { page ->
        Box(
            modifier = Modifier.semantics {
                contentDescription = "Page ${page + 1} of 3"
            }
        ) {
            // Page content
        }
    }
}

@Preview
@Composable
fun MyHorizontalPager_Preview(){
    MyHorizontalPager()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyVerticalPager() {
    val pagerState = rememberPagerState(pageCount = {3})
    VerticalPager(
        state = pagerState) { page ->
        Text("Page: ${page + 1}")
    }
}

@Preview
@Composable
fun MyVerticalPager_Preview(){
    MyVerticalPager()
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPagerWithScrolling() {
    val pagerState = rememberPagerState(pageCount = {5})
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(state = pagerState) { page ->
        Text("Page: ${page + 1}")
    }

    LaunchedEffect(Unit) { // Example: Scroll to page 3 after a delay
        delay(1000)
        coroutineScope.launch {
            pagerState.animateScrollToPage(2)
        }
    }
}
