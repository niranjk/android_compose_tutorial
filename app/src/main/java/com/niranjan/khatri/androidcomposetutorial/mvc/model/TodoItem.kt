package com.niranjan.androidtutorials.mvc.model

data class TodoItem(val title: String, val description: String, var isCompleted: Boolean = false)
