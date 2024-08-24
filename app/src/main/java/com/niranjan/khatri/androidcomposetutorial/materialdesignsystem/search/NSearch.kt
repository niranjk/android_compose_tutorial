package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    val searchResults = remember { mutableStateListOf<String>() }
    var isActive by remember { mutableStateOf(false) } // State for active

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Search") }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(innerPadding)) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 200.dp)
                    .padding(8.dp).semantics {
                        stateDescription = if(isActive) "Active" else "Inactive"
                    },
                query = searchText,
                onQueryChange = { newText ->
                    searchText = newText
                    isActive = newText.isNotEmpty()
                    // Fetch new suggestions based on newText (if needed)
                    // Update searchResults based on newText (if you want real-time updates)
                    searchResults.clear()
                    searchResults.addAll(getSuggestions(newText))
                },
                onSearch = {
                    // Perform search based on searchText
                    searchResults.clear()
                    searchResults.addAll(performSearch(searchText))
                },
                active = isActive, // Change to true to show the search results
                onActiveChange = {
                    isActive = it
                },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                trailingIcon = {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear")
                    }
                },
                placeholder = { Text("Search for fruits...") },
                shape = RoundedCornerShape(16.dp)
            ) {
                // Display search suggestions here using LazyColumn
                val suggestions = getSuggestions(searchText)
                LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                    items(suggestions) { suggestion ->
                        Text(
                            text = suggestion,
                            modifier = Modifier.clickable {
                                searchText = suggestion
                                isActive = false // Deactivate after clicking a suggestion
                            }
                        )
                    }
                }
            }

            HorizontalDivider()
            // Display search results here using LazyColumn or similar
            LazyColumn {
                items(searchResults) { result ->
                    Text(text = result,  style = LocalTypography.current.bodyLarge)
                }
            }
        }
    }
}

// Replace with your actual searchlogic
fun performSearch(query: String): List<String> {
    val suggestions = listOf("Apple", "Banana", "Orange", "Grape", "Strawberry", "Mango", "Pear", "Papaya", "Kiwi", "Pomigranate")
    return suggestions.filter { it.contains(query, ignoreCase = true) }
}

// Replace with your actual suggestion logic
fun getSuggestions(query: String): List<String> {
    val suggestions = listOf("Apple", "Banana", "Orange", "Grape", "Strawberry")
    return suggestions.filter { it.contains(query, ignoreCase = true) }
}


@Preview
@Composable
fun SearchBar_Preview(){
    SearchScreen()
}