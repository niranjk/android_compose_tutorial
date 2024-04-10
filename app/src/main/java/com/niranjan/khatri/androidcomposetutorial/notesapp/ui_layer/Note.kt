package com.niranjan.khatri.androidcomposetutorial.notesapp.ui_layer

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ui.theme.green

@Composable
fun Note() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .then(
            Modifier.padding(8.dp) // Apply padding after fillMaxWidth()
        )) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(green)
                .then(Modifier.aspectRatio(1f)) // ensure square box
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Title", maxLines = 1)
            Text(text = "Content", maxLines = 1)
        }
        Checkbox(
            checked = false,
            onCheckedChange = { },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnItem(){
    val items = (0..1000).map { "Item $it" }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items){ item ->
            ListItem(
                headlineText = { Text(text = item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun MyLayout(content: @Composable () -> Unit) {
    Column {
        Text(text = "Top Header")
        Box(Modifier.weight(1f).then(Modifier.fillMaxWidth())) {
            content() // Slot for child composable
        }
        Text(text = "Bottom Footer")
    }
}

@Composable
fun MySlotContentUse(){
    MyLayout {
        // Content within the slot
        Text(text = "This is the content within the slot")
    }
}

@Preview(showBackground = true)
@Composable
private fun NotePreview() {
    MySlotContentUse()
}