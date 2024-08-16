package com.niranjan.khatri.androidcomposetutorial.ds.sliders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySlider() {
    var sliderPosition by remember { mutableStateOf(0f) }
    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 0f..100f,
        steps = 10, // For discrete slider
        modifier = Modifier.semantics {
            contentDescription = "Volume control"
        },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.secondary,
            disabledThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            // ... customize other colors
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscreteSliderExample() {
    var sliderPosition by remember {mutableStateOf(5f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text= "Discrete Slider Example",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..10f,
            steps = 10,
            onValueChangeFinished = {
                // this is called when the user completed selecting a new value for the Slider
            },
            modifier = Modifier.semantics {
                contentDescription = "Discrete slider with10 steps"
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected value: ${sliderPosition.toInt()}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun DiscreteSliderExample_Preview(){
    DiscreteSliderExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContinuousSliderExample() {
    var sliderPosition by remember {mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text= "Continuous Slider Example",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            modifier = Modifier.semantics {
                contentDescription = "Continuous slider"
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Selected value: ${sliderPosition.toInt()}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ContinuousSliderExample_Preview(){
    ContinuousSliderExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RangeSliderExample() {
    var sliderPosition by remember {mutableStateOf(0f..100f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {Text(
        text = "Range Slider Example",
        style = MaterialTheme.typography.headlineSmall
    )
        Spacer(modifier = Modifier.height(16.dp))

        RangeSlider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            modifier = Modifier.semantics {
                contentDescription = "Range slider"
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Selected range:${sliderPosition.start.toInt()} - ${sliderPosition.endInclusive.toInt()}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun RangeSliderExample_Preview(){
    RangeSliderExample()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscreteSliderExample_Customized() {
    var sliderPosition by remember {mutableStateOf(25f) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Discrete Slider Example Custom",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..50f, // Changed valueRange
            steps = 25, // Changed steps
            onValueChangeFinished = {
                // Action to perform when value selection is finished
                println("Slidervalue changed: ${sliderPosition.toInt()}")
            },
            thumb = { sliderDragState ->
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Custom Thumb",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Red
                )
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.primary
            ), // Customized colors
            modifier = Modifier.semantics {
                contentDescription = "Discrete slider with 25 steps" // Updated content description
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected value: ${sliderPosition.toInt()}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun RangeSliderExampleCustom_Preview(){
    DiscreteSliderExample_Customized()
}