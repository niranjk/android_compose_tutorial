package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.list.MyItem
import com.niranjan.khatri.androidcomposetutorial.list.MyList
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.lists.Item

@Preview
@Composable
fun BrokenFirstFrameListExample() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    var firstItemHeightPx by remember { mutableStateOf(0) }

    // Derive firstItemHeight in dp from firstItemHeightPx
    val firstItemHeight = with(LocalDensity.current) { firstItemHeightPx.toDp() }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item { // Header
            Text(
                text = "Header",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(bottom = firstItemHeight) // Incorrect padding initially
            )
        }
        items(items) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (item == items[0]) Color.Yellow else Color.White)
                    .padding(16.dp)
                    .onGloballyPositioned { coordinates ->
                        if (item == items[0]) {
                            firstItemHeightPx = coordinates.size.height
                        }
                    }
            )
        }
    }
}


@Preview
@Composable
fun SubcompositionGridExample() {
    val items = (1..20).toList()

    BoxWithConstraints {
        val columns = if (maxWidth > 600.dp) 3 else 2

        Column {
            items.chunked(columns).forEach { rowItems ->
                Row {
                    rowItems.forEach { item ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .background(Color.LightGray)
                                .padding(8.dp)
                        ) {
                            Text(text = "Item $item")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailPaneScaffoldExample() {
    val navigator = rememberListDetailPaneScaffoldNavigator<String>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                MyList(
                    onItemClick = { item ->
                        // Navigate to the detail pane with the passed item
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                    },
                )
            }
        },
        detailPane = {
            AnimatedPane {
                // Show the detail pane content if selected item is available
                navigator.currentDestination?.content?.let {
                    MyDetails(it)
                }
            }
        },
    )
}


@Composable
fun MyList(
    items: List<String> = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5"),
    onItemClick: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items) { item ->
            ListItem(
                headlineContent = { Text(item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun MyDetails(item: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Details for:")
        Text(text = item, style = MaterialTheme.typography.headlineMedium)
        // Add more detail content here as needed
    }
}


@Preview
@Composable
fun AdaptiveButtons() {
    Layout(
        content = {
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("primary")) {
                Text("Primary Button")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("secondary")) {
                Text("Secondary Button ")
            }
        }
    ) { measurables, constraints ->
        val primaryPlaceable = measurables.find { it.layoutId == "primary" }?.measure(constraints)
        val secondaryPlaceable = measurables.find { it.layoutId == "secondary" }?.measure(constraints)

        val primaryWidth = primaryPlaceable?.width ?: 0
        val secondaryWidth = secondaryPlaceable?.width ?: 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            if (primaryWidth + secondaryWidth + 16.dp.roundToPx() <= constraints.maxWidth) {
                // Place side-by-side
                primaryPlaceable?.placeRelative(0, 0)
                secondaryPlaceable?.placeRelative(primaryWidth + 16.dp.roundToPx(), 0)
            } else {
                // Place vertically
                primaryPlaceable?.placeRelative(0, 0)
                secondaryPlaceable?.placeRelative(0,
                    primaryPlaceable?.height ?: (0 + 16.dp.roundToPx())
                )
            }
        }
    }
}