package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.chips

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalColorScheme
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalShapes
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.LocalTypography


// Assist
@Composable
fun NAssistChips(
    modifier: Modifier = Modifier,
    onAssistChipClick: () -> Unit,
    leadingIconDrawableRes: Int? = null,
    trailingIconDrawableRes: Int? = null,
    enabled: Boolean = true,
    chipLabel: String,
) {
    AssistChip(
        modifier = modifier,
        onClick = onAssistChipClick,
        leadingIcon = {
            if (leadingIconDrawableRes != null) {
                Icon(
                    modifier =
                        Modifier.size(
                            width = LocalShapes.current.space.spaceLarge,
                            height = LocalShapes.current.space.spaceLarge,
                        ),
                    painter = painterResource(id = leadingIconDrawableRes),
                    contentDescription = null,
                )
            }
        },
        trailingIcon = {
            if (trailingIconDrawableRes != null) {
                Icon(
                    modifier =
                        Modifier.size(
                            width = LocalShapes.current.space.spaceLarge,
                            height = LocalShapes.current.space.spaceLarge,
                        ),
                    painter = painterResource(id = trailingIconDrawableRes),
                    contentDescription = null,
                )
            }
        },
        label = {
            Text(text = chipLabel, maxLines = 1, style = LocalTypography.current.displayMedium)
        },
        colors =
            AssistChipDefaults.assistChipColors().copy(
                containerColor = LocalColorScheme.current.primary.primaryContainer,
                labelColor = LocalColorScheme.current.primary.primary,
            ),
        border =
            BorderStroke(
                width = 1.dp,
                color = LocalColorScheme.current.primary.onPrimaryContainer,
            ),
    )
}



@Preview
@Composable
fun NAssistChips_Preview() {
    var selected by remember {
        mutableStateOf(false)
    }
    NAssistChips(
        modifier = Modifier.padding(16.dp),
        onAssistChipClick = {
            selected = !selected
        },
        leadingIconDrawableRes = R.drawable.ic_boy,
        chipLabel = "Assist Chips",
    )
}

// Filter Chip
@Composable
fun NFilterChips() {
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }
    val availableFilters = listOf("Category A", "Category B", "Category C")

    LazyRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(availableFilters) { filter ->
            FilterChip(
            selected = filter in selectedFilters,
            onClick = {
                selectedFilters = if (filter in selectedFilters) {
                    selectedFilters - filter
                } else {
                    selectedFilters + filter
                }
            },
            label = { Text(filter) }
        )
        }
    }
}

@Preview
@Composable
fun NFilterChip_Preview(){
    NFilterChips()
}

// Input Chips
@Composable
fun NInputChips() {
    var enteredText by remember { mutableStateOf("") }
    val inputChips = remember{ mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = enteredText,
            onValueChange = { enteredText = it },
            label = { Text("Enter tags") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (enteredText.isNotBlank()) {
                        inputChips.add(enteredText)
                        enteredText = ""
                    }
                }
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(inputChips) {chip ->
                InputChip(
                    selected = false,
                    onClick = { inputChips.remove(chip) },
                    label = { Text(chip) },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Remove Chip"
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun NInputChip_Preview(){
    NInputChips()
}

// Suggestion Chip
@Composable
fun NSuggestionChip() {
    var enteredText by remember { mutableStateOf("") }
    val suggestions = listOf("apple", "banana", "cherry", "date", "elderberry")
    val filteredSuggestions = suggestions.filter { it.contains(enteredText, ignoreCase = true) }

    Column(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = enteredText,onValueChange = { enteredText = it },
            label = { Text("Enter fruit") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredSuggestions) { suggestion ->
                SuggestionChip(
                    onClick = { enteredText = suggestion },
                    label = { Text(suggestion) }
                )
            }
        }}
}

@Preview
@Composable
fun NSuggestionChip_Preview(){
    NSuggestionChip()
}




