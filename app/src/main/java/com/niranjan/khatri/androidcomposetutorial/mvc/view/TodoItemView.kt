package com.niranjan.androidtutorials.mvc.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niranjan.androidtutorials.mvc.model.TodoItem

@Composable
fun TodoItemView(todoItem: TodoItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = todoItem.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = todoItem.description, style = MaterialTheme.typography.headlineSmall)
        }
    }
}
