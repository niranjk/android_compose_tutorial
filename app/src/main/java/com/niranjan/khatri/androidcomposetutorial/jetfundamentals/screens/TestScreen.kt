package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @author NIRANJAN KHATRI
 * @since 16/04/24
 * @version
 */


data class User( val name: String, val avatar: Int)
val userList = listOf(User("Alice", R.drawable.ic_girl), User("Bob", R.drawable.ic_boy))

@Composable
fun RecompositionExample(){
// This might recompose the entire list for every item change
    Column {
        userList.forEach { user ->
            Text(text = user.name)
            Image(
                painter = painterResource(id = user.avatar),
                modifier = Modifier
                    .padding(1.dp),
                contentDescription = null,
            )
        }
    }
}
@Composable
fun RecompositionLazyExample(){
    // Use LazyColumn for efficient list rendering
    LazyColumn {
        items(userList) { user ->
            Text(text = user.name)
            Image(
                painter = painterResource(id = user.avatar),
                modifier = Modifier
                    .padding(1.dp),
                contentDescription = null,
            )
        }
    }
}


@Composable
fun MyComposable() {
    Box(modifier = Modifier.fillMaxSize()) { // Parent box fills the screen
        // This image might overflow if it's larger than available space
        Image(painter =  rememberAsyncImagePainter(model = "https://picsum.photos/id/237/2000/700"), "Dog")
        // Use modifiers to define image size within the box
        Image(
            painter = rememberAsyncImagePainter(R.drawable.ic_girl),
            contentDescription = "Girl",
            modifier = Modifier.size(100.dp)
        )
    }
}


@Composable
fun OverusingModifiers(){
    // This can be hard to read with many modifiers
    Text(
        text = "Hello, World!",
        color = Color.Red,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.LightGray)
    )
    StyledText(text = "Hello, World!")
}

// Create a custom composable for styled text
@Composable
fun StyledText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = Color.Red,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(16.dp).background(color = Color.LightGray)
    )
}


// This might lead to unexpected recompositions
@Composable
fun MyNetworkCall() {
    val data = fetchDataFromNetwork() // Network call within a composable
    Text(text = data)
}

fun fetchDataFromNetwork(): String{
    // NEtwork call
    return "HTTP Network Response..."
}

// Use LaunchedEffect for side effects triggered by composable lifecycle
@Composable
fun MyComposableSideEffects() {
    var data by remember { mutableStateOf("") }
    LaunchedEffect(Unit) { // Runs on composable composition
        data = fetchDataFromNetwork()
    }
    Text(text = data)
}

@Composable
fun ComposeAccessibility(){
    Image(
        painter = painterResource(R.drawable.ic_girl),
        contentDescription = "App logo", // Describe the image for screen readers
        modifier = Modifier.semantics { contentDescription = "Girl logo" } // Set content description for accessibility
    )
}

data class Car(var name: String, var id: Int)
@Composable
fun ComposableListExample(){
    var carList= listOf(Car("Alice", 1), Car("Bob", 2))

// This might not work well for updates or animations
    Column {
        carList.forEach { user ->
            Row {
                Text(text = user.name)
                Button(onClick = {
                    val newList = carList.filterNot { it.name == user.name }
                    carList = newList
                }) {
                    Text("Mike")
                }
            }
        }
    }
// Use keys for each item in the list
    LazyColumn {
        items(carList, key = { user -> user.id }) { user ->
            Row {
                Text(text = user.name)
                Button(onClick = {
                    val newList = carList.filterNot { it.id == user.id }
                    carList = newList
                }) {
                    Text("Mike")
                }
            }
        }
    }
}



// Custom text field implementation (might be time-consuming)
@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTextField(text: String = "Super Man", onTextChanged: (String) -> Unit = {}) {
    // Implement text field logic
    // So Use prebuilt TextField composable
    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = Modifier.fillMaxWidth()
    )
}




@Composable
fun TitleSubtitle(title: String = "Spider Man", subtitle: String = "Returns") {
    Column {
        Text(text = title, fontSize = 20.sp) // Adjustments as needed
        Spacer(modifier = Modifier.height(8.dp)) // Adds vertical space
        Text(text = subtitle, fontSize = 16.sp) // Adjustments as needed
    }
}


// Use ViewModel for more complex state management
class MainViewModel : ViewModel() {
    //  using MutableStateFlow
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()
    fun incrementCounter() {
        _counter.value++
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyComposableWithViewModel(viewModel: MainViewModel = MainViewModel()) {
    Column {
        Button(onClick = { viewModel.incrementCounter() }) {
            Text(text = "Count: ${viewModel.counter.value}")
        }
    }
}