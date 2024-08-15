package com.niranjan.khatri.androidcomposetutorial.ds.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawerSheetExample() {
    var showSheet by remember { mutableStateOf(false) }
    if (showSheet) {
        ModalDrawerSheet(
            drawerContainerColor = Color.LightGray,
            drawerShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            // Sheet content (using ColumnScope)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text= "My Side Sheet",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Add your sheet content here
                Button(onClick = { showSheet = false }) {
                    Text("Close Sheet")
                }
            }
        }
    } else {
        // Display main content without the sheet
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Main Screen",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("This is the main content area.")
            Button(onClick = { showSheet = true }) {
                Text("Open Sheet")
            }
        }
    }
}

@Preview
@Composable
fun ModalDrawerSheetExample_Preview(){
    ModalDrawerSheetExample()
}