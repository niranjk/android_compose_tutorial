package com.niranjan.khatri.androidcomposetutorial.concepts

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

// Basic FlowRow example
@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicFlowRowExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Arrangement modifier
    ) {
        for (i in 1..10) {
            Card(colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
                Text(
                    text = "Item $i",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

// FlowColumn
@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowColumnExample() {
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).horizontalScroll(rememberScrollState())
    ) {
        for (i in 1..15) {
            Card(
                modifier = Modifier.height(300.dp), // Items take full height of the column
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Text(
                    text = "Item $i",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

// Grid-like layout with FlowRow and weights
@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridLikeLayoutExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        maxItemsInEachRow = 3 // Limit items per row
    ) {
        for (i in 1..10) {
            if (i % 2 == 1) {
                Card(
                    modifier = Modifier.width(50.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Green)
                ) {
                    Text(
                        text = "Item $i",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            } else {
                Card(
                    modifier = Modifier.weight(0.5f),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Text(
                        text = "Item $i",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

// FlowRow with fillMaxWidth modifier
@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowFillMaxWidthExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.7f), // Takes 70% of container width
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {
            Text(
                text = "Item 1",
                modifier = Modifier.padding(8.dp)
            )
        }
        Card(colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {
            Text(
                text = "Item 2",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// widthIn modifier example
@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WidthInExample() {
    FlowRow(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Card(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 200.dp), // Preferred width range
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {
            Text(
                text = "Item with widthIn",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}