package com.niranjan.khatri.androidcomposetutorial.ds.tooltip


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TooltipBoxExample() {
    val tooltipState = rememberTooltipState()
    val tooltipPositionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider()
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        TooltipBox(
            positionProvider = remember { tooltipPositionProvider },
            tooltip = {
                // Tooltip content
                Icon(Icons.Filled.Favorite, contentDescription = "Tooltip Content")
            },
            state = tooltipState
        ) {
            // Content that triggers the tooltip
            Text("Long press me to see Tooltip.", style = LocalTypography.current.bodyLarge)
        }
    }
}

@Preview
@Composable
fun TooltipBox_Preview(){
    TooltipBoxExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RichTooltipBoxExample() {
    val tooltipState = rememberTooltipState()
    val tooltipPositionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TooltipBox(
            positionProvider = remember { tooltipPositionProvider },
            tooltip = {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = "Thumps Up Icon",
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Like this item",
                            style = LocalTextStyle.current.copy(
                                color = Color.Red
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "This is a rich tooltip with an icon, title, and a button.",
                        style = LocalTextStyle.current.copy(
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Learn More")
                    }
                }
            },
            state = tooltipState
        ) {
            Text(
                "Long press me to see Rich Tooltip.",
                style = LocalTypography.current.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun RichTooltipBox_Preview(){
    RichTooltipBoxExample()
}
