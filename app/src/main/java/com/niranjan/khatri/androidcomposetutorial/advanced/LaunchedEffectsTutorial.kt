package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.cancel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.chatapp.repository.ChatDetail
import com.niranjan.khatri.androidcomposetutorial.chatapp.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * LaunchedEffect Usecases:
 */

@Composable
fun TriggeringSideEffectOnComposition(viewModel: MyViewModel = hiltViewModel<MyViewModel>()){
    val isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = "Key1") {
        viewModel.fetchData() // Fetch data when the composable enters the composition
    }

    LaunchedEffect(key1 = "Key Animation") {
        // Start an animation when the value of 'isVisible' changes
        if (isVisible) {
            startAnimation()
        }
    }
}

fun startAnimation(){
    // Start your animation
}



// 2. Cancelling and Restarting Effects
@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    var searchTerm by remember { mutableStateOf("") }

    Column {
        TextField(
            value = searchTerm,
            onValueChange = { searchTerm = it },
            label = { Text("Search") }
        )

        LaunchedEffect(searchTerm) {
            // Cancel previous search and start a new one when 'searchTerm' changes
            val results = viewModel.search(searchTerm)
            // Assuming viewModel.search() returns a Flow or LiveData
            // Collect the results and update the UI accordingly
            // ...
        }

        // Display search results here
        // ...
    }
}


// 3. Integrating with lifecycle events
@Composable
fun MyComposableLifecycleEventIntegrationInLaunchedEffect() {
    var timerRunning by remember { mutableStateOf(true) }

    // Combine LaunchedEffect and DisposableEffect using a remember block
    val timerLoop = remember {
        object : Any() { // Use an object to hold the job and onDispose logic
            var job: Job? = null

            fun start() {
                job = CoroutineScope(Dispatchers.Main).launch {
                    println("LaunchedEffect: Started")
                    while (timerRunning) {
                        delay(1000)
                        println("Timer tick")
                    }
                }
            }

            fun stop() {
                job?.cancel()
                println("LaunchedEffect: onDispose called - Cleaning up")
                timerRunning = false
            }
        }
    }

    // Start the timer loop when the composable enters the composition
    LaunchedEffect(Unit) {
        timerLoop.start()
    }

    // Stop the timer loop when the composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            timerLoop.stop()
        }
    }

    Button(onClick = { /* Trigger some action */ }) {
        Text("My Button")
    }
}


// 4. Handling Delays and Timeouts
@Composable
fun DelaySnackbarExample() {
    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = showSnackbar) {
        if (showSnackbar) {
            delay(2000) // Delay for 2 seconds
            snackbarHostState.showSnackbar(
                message = "This is a delayed Snackbar!",
                duration = SnackbarDuration.Short
            )
            showSnackbar = false // Reset the state to hide the Snackbar
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        Button( contentPadding = contentPadding, onClick = { showSnackbar = true }) {
            Text("Show Snackbar")
        }
    }
}


// 5. Combing with rememeberCoroutineScope

@Composable
fun CounterWithCoroutineScope() {
    var count by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(1000)
            count++ // Increment count every second
        }
    }

    Column {
        Text("Count: $count")
        Button(onClick = {
            scope.launch {
                count += 5 // Increment count by 5 on button click
            }
        }) {
            Text("Increment by 5")
        }
    }
}

class SearchViewModel {
    // ... (Repository, data fetching logic, etc.)

    fun search(searchTerm: String): Flow<List<SearchResult>> {
        // Simulate a search operation (replace with your actual logic)
        return flow {
            delay(500) // Simulate network delay
            emit(listOf(SearchResult(searchTerm))) // Emit search results
        }
    }
}

data class SearchResult(val title: String)


/**
 * This composable is responsible for displaying the UI based on the state provided by the MyViewModel.
 * It uses hiltViewModel<MyViewModel>() to inject the ViewModel using Hilt.
 * LaunchedEffect(key1 = Unit) is used to trigger the fetchData() function in the ViewModel when the composable enters the composition.
 * A when statement is used to display different UI elements based on the current uiState (Loading, Success, or Error).
 */
@Composable
fun MyLaunchedEffectExample(viewModel: MyViewModel = hiltViewModel<MyViewModel>()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    when (uiState) {
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> {
            val successState = uiState as UiState.Success
            ChatList(chats = successState.data)
        } // Pass chat list to ChatList composable
        is UiState.Error -> {
            val errorState = uiState as UiState.Error
            Text(text = "Error: ${errorState.message}")
        }
    }
}

/**
 * This is a Hilt ViewModel that holds the UI state and performs the data fetching logic.
 * _uiState is a MutableStateFlow that holds the current UI state.
 * uiState is a StateFlow exposed to the composable to observe UI state changes.
 * fetchData() is a function that launches a coroutine to fetch data from the repository. It updates the _uiState based on the result (Success or Error).
 *
 */
@HiltViewModel
class MyViewModel @Inject constructor(
    val repository: ChatRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            try {
                val data = repository.getChats().first() // Get the first emission from the Flow
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

/***
 * This sealed class represents the different states of the UI: Loading, Success, or Error.
 * Success holds the fetched data (a list of ChatDetail).
 * Error holds the error message.
 */
sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<ChatDetail>) : UiState()
    data class Error(val message: String) : UiState()
}

/**
 * This composable displays a list of chats using LazyColumn.
 * It takes a list of ChatDetail as input and renders each chat item using the ChatItem composable.
 */
@Preview
@Composable
fun ChatList(chats: List<ChatDetail> = listOf(ChatDetail(12, "Hi how are you?"))) {
    LazyColumn {
        items(chats) { chat ->
            ChatItem(chat = chat)
        }
    }
}

/**
 * This composable displays a single chat item.
 * It shows the sender's image, sender's name (using chat.id as a placeholder), and the chat message.
 */
@Composable
fun ChatItem(chat: ChatDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_car),
            contentDescription = "Sender Image",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = chat.id.toString(), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
            Text(text = chat.message, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}




