package com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NButton() {
    Button(onClick = { /*Action to perform when clicked*/ }) {
        Text(text = "Filled Button")
    }
}

@Preview
@Composable
fun Preview_Button() {
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        NButton()
    }
}

@Composable
fun NElevatedButtonExample() {
    ElevatedButton(onClick = { /* Action to perform when clicked */ }) {
        Text("Elevated Button")
    }
}

@Preview
@Composable
fun NElevatedButtonExample_Preview() {
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        NElevatedButtonExample()
    }
}

@Composable
fun NFilledTonalButtonExample() {
    FilledTonalButton(onClick = { /* Action to perform when clicked */ }){
        Text("Filled Tonal Button")
    }
}

@Preview
@Composable
fun NFilledTonalButtonExample_Preview() {
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        NFilledTonalButtonExample()
    }
}

@Composable
fun NOutlinedButtonExample() {
    OutlinedButton(onClick = { /* Action to perform when clicked */ }) {
        Text("Outlined Button")
    }
}

@Preview
@Composable
fun NOutlinedButtonExample_Preview() {
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        NOutlinedButtonExample()
    }
}

@Composable
fun NTextButtonExample() {
    TextButton(onClick = { /* Action to perform when clicked */ }) {
        Text("Text Button")
    }
}

@Preview
@Composable
fun NTextButtonExample_Preview() {
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        NTextButtonExample()
    }
}

@Composable
fun NIconButtonExample() {
    IconButton(onClick = { /* Action to perform when clicked */ }) {
        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
    }
}


@Preview
@Composable
fun NIconButtonExample_Preview() {
    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        NIconButtonExample()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NMultiChoiceSegmentedButtonExample() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
   val selectedIndex = remember {
       mutableStateListOf<Int>()
   }

    MultiChoiceSegmentedButtonRow(
        modifier = Modifier.padding(16.dp)
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                checked = selectedIndex.contains(index),
                onCheckedChange = { isChecked ->
                    if(isChecked){
                        selectedIndex.add(index)
                    } else {
                        selectedIndex.remove(index)
                    }
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(option)
            }
        }
    }
}

@Preview
@Composable
fun NMultiChoiceSegmentedButtonExample_Preview() {
    NMultiChoiceSegmentedButtonExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NSingleChoiceSegmentedButtonRowExample() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.padding(16.dp)
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(option)
            }
        }
    }
}

@Preview
@Composable
fun NSingleChoiceSegmentedButtonRowExample_Preview() {
    NSingleChoiceSegmentedButtonRowExample()
}

@Composable
fun NFloatingActionButtonExample() {
    FloatingActionButton(
        onClick = { /* Action to perform when clicked */ },containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}

@Preview
@Composable
fun NFloatingActionButtonExample_Preview() {
    NFloatingActionButtonExample()
}

@Composable
fun NExtendedFloatingActionButtonExample() {
    ExtendedFloatingActionButton(
        onClick = { /* Action to perform when clicked */ },text = { Text("Extended FAB") },
        icon = { Icon(Icons.Filled.Add, contentDescription = "Add") }
    )
}

@Preview
@Composable
fun NExtendedFloatingActionButtonExample_Preview() {
    NExtendedFloatingActionButtonExample()
}


