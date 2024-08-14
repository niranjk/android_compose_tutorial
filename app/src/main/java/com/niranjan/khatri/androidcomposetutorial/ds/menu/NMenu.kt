package com.niranjan.khatri.androidcomposetutorial.ds.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun NMenu() {
    var expanded by remember {
        mutableStateOf(false)
    }
    DropdownMenu(
        expanded = expanded, // Controls visibility
        onDismissRequest = { expanded = true } // Called when menu should close
    ) {
        // Menu items go here
        DropdownMenuItem(text = {
            Text("Menu Item 1")
        }, onClick = { /* Handle item click */ })

        DropdownMenuItem(text = {
            Text("Menu Item 2")
        }, onClick = { /* Handle item click */ })
        HorizontalDivider()
        DropdownMenuItem(text = {
            Text("Menu Item 3")
        }, onClick = { /* Handle item click */ })
    }
}

@Composable
fun MenuExample(){
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "More")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Menu items go here
            DropdownMenuItem(text = {
                Text("Settings")
            }, onClick = { /* Handle item click */ })

            DropdownMenuItem(text = {
                Text("Profile")
            }, onClick = { /* Handle item click */ })
            HorizontalDivider()
            DropdownMenuItem(text = {
                Text("Home")
            }, onClick = { /* Handle item click */ })
        }
    }
}
@Preview
@Composable
fun Menu_Preview(){
    MenuExample()
}