package com.niranjan.androidtutorials.mvc.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niranjan.androidtutorials.mvc.model.TodoItem
import com.niranjan.androidtutorials.mvc.view.TodoItemView

class TodoMVCActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

@Composable
fun TodoApp() {
    val todoList by remember { mutableStateOf(generateTodoList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        todoList.forEach { todo ->
            TodoItemView(todoItem = todo)
        }
    }
}

private fun generateTodoList(): List<TodoItem> {
    return listOf(
        TodoItem("MVC Task 1", "Description for Task 1")
    )
}
