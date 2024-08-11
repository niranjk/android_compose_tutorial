package com.niranjan.khatri.androidcomposetutorial.ds.lists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography

data class Item(
    val name: String
)
@Composable
fun NLists(){
    val filteredItems = listOf(Item("Pizza"), Item("Pasta"), Item("Salads"), Item("Roast"))
    LazyColumn {
        items(filteredItems) { item ->
            ListItem(
                headlineContent = { Text(item.name) },
                modifier = Modifier.clickable { /* Handle item click */ },
                leadingContent = { Icon(Icons.Filled.Favorite, contentDescription = "Fav Food") }, // Optional
                trailingContent = { IconButton(onClick = { /* Action */ }) { Icon(Icons.Filled.Face, contentDescription = "Fav Food") } } // Optional
            )
        }
    }
    val sortedItems = filteredItems.sortedBy { it.name }

    LazyColumn {
        items(sortedItems) { item ->
            // ...
        }
    }

    LazyColumn {
        // One-Line List Item
        item {
            ListItem(
                headlineContent = { Text("Pizza") }
            )
        }
        // Two-Line List Item
        item {
            ListItem(headlineContent = { Text("Pizza") },
                supportingContent = { Text("A baked dish of Italian origin consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients") }
            )
        }
        // Three-Line List Item
        item {
            ListItem(
                headlineContent = { Text("Pizza") },
                supportingContent = { Text("A baked dish of Italian origin") },
                overlineContent = { Text("Food") }
            )
        }
    }
}
@Preview
@Composable
fun NLists_Preview(){
    NLists()
}

@Composable
fun Expandable_List(){
    ExpandableListItem(header = "My Expandable Item") {
        Column(Modifier.padding(16.dp)) {
            Text("This is the expanded content.")
            // Add more content here if needed
        }
    }
}


@Composable
fun ExpandableListItem(
    header: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        ListItem(
            headlineContent = { Text(header) },
            trailingContent = {
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,contentDescription = if (expanded) "Collapse" else "Expand"
                )
            },
            modifier = Modifier.clickable { expanded = !expanded }
        )
        AnimatedVisibility(visible = expanded) {
            content()
        }
    }
}

@Preview
@Composable
fun ExpandableLists_Preview(){
    Expandable_List()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NSwipeableListExample() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }
    LazyColumn {
        items(items, key = { it }) { item ->
            val state = rememberSwipeToDismissBoxState()
            SwipeToDismissBox(
                state = state,
                backgroundContent = {
                    // Background content (e.g., delete icon)
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete"
                        )
                    }
                },
                enableDismissFromStartToEnd = false, // Only allow dismiss from end to start
                content = {
                    // Content of the list item
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                       ListItem(
                            headlineContent = { Text(item) }
                        )
                    }
                }
            )

            // Handle item dismissal (e.g., remove from list)
            val isDismissed = state.currentValue == SwipeToDismissBoxValue.EndToStart
            if (isDismissed) {
                // Perform dismiss action here (e.g., remove item from list)
                items.remove(item)
            }
        }
    }
}

@Preview
@Composable
fun SwipableLists_Preview(){
    NSwipeableListExample()
}

@Composable
fun DraggableListExample() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }
    var draggedIndex by remember { mutableStateOf<Int?>(null) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(onDragStart = { offset ->
                    draggedIndex = items.indexOfFirst { item ->
                        val itemBounds = getDraggableBoundsFor(offset)
                        itemBounds.contains(offset)
                    }
                },
                    onDrag = { change, _ ->
                        change.consume()
                        draggedIndex?.let { draggedItem ->
                            val newIndex =
                                calculateNewIndex(items.size, draggedItem, change.position)
                            if (newIndex != draggedItem) {
                                items.move(draggedItem, newIndex)
                                draggedIndex = newIndex
                            }
                        }
                    },
                    onDragEnd = {
                        draggedIndex = null
                    },
                    onDragCancel = {
                        draggedIndex = null
                    }
                )
            }
    ) {
        items(items.size) { index ->
            val item = items[index]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(if (draggedIndex == index) Color.LightGray else Color.Cyan)
            ) {
                Text(item, style = LocalTypography.current.headlineLarge)
            }
        }
    }
}

fun <T> MutableList<T>.move(fromIndex: Int, toIndex: Int) {
    val item = this.removeAt(fromIndex)
    this.add(toIndex, item)
}

private fun getDraggableBoundsFor(offset: Offset): Rect {
    // Calculate the bounds of the draggable area based on the offset and item size
    // This is a simplified example, you might need to adjust this based on your layout
    return Rect(offset.x - 50, offset.y - 50, offset.x + 50, offset.y + 50)
}

private fun calculateNewIndex(listSize: Int, currentIndex: Int, position: Offset): Int {val middleY = position.y
    for (i in 0 until listSize) {
        // Calculate the center Y position of each item
        val itemCenterY = (i * 100) + 50 // Assuming each item is 100px tall
        if (middleY > itemCenterY - 50 && middleY < itemCenterY + 50) {
            return i
        }
    }
    return currentIndex
}

@Preview
@Composable
fun DraggableLists_Preview(){
    DraggableListExample()
}



