package com.niranjan.khatri.androidcomposetutorial.ds.sheets

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardDrawerSheetExample() {
    var showSheet by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Main screen content
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Main Standard Screen",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("This is the main standard content area.")
            Button(onClick = { showSheet = true }) {
                Text("Open Standard Sheet")
            }
        }

        // Drawer sheet that slides in from the start
        if (showSheet) {
            ModalDrawerSheet(
                drawerContainerColor= Color.Cyan,
                drawerShape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                modifier = Modifier.align(Alignment.TopStart) // Align to the start (left)
            ) {
                // Sheet content
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(300.dp)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "My Standard Drawer Sheet",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("This is some content inside the sheet.")
                    Button(onClick = { showSheet = false }) {
                        Text("Close Standard Sheet")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StandardDrawerSheetExample_Preview(){
    StandardDrawerSheetExample()
}

