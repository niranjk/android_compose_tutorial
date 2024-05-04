package com.niranjan.khatri.androidcomposetutorial.bootcamp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AndroidComposeTutorialTheme

/**
 * @author NIRANJAN KHATRI
 * @since 03/05/24
 * @version 1
 */

@Composable
fun ComposeBootcampScreen(
    onClicked: () -> Unit,
    modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Compose Bootcamp #1!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onClicked
        ) {
            Text("Continue")
        }
    }
}


@Composable
fun ComposeColumnElement(){
    Column (verticalArrangement = Arrangement.SpaceBetween){
        Text(text = "Column Item 1", modifier = Modifier.padding(8.dp))
        Text(text = "Column Item 2", modifier = Modifier.padding(8.dp))
        Text(text = "Column Item 3", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ComposeUsingConditionalsColumnElement(
    modifier: Modifier = Modifier,
    list : List<String> = listOf("Apple", "Banana", "Carrot")
){
    Column (verticalArrangement = Arrangement.SpaceBetween){
        list.forEach {// Using Kotlin forEach loop to update the UI
            Text(text = it)
        }
    }
}

@Composable
fun ComposeRowElement(){
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Row Item Text", modifier = Modifier.align(Alignment.CenterVertically))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(12.dp)) {
            Text(text = "Show More")
        }
    }
}

/** Button Types:
 * 1. ElevatedButton
 * 2. TextButton
 * 3. OutlinedButton
 * 4. FilledTonalButton
 */
@Composable
fun MyButton(modifier: Modifier = Modifier){
    Surface(modifier = modifier.padding(16.dp), color = MaterialTheme.colorScheme.primary){
        ElevatedButton(onClick = { /*TODO*/ }, modifier = modifier.padding(8.dp)) {
            Text(text = "Elevated Button")
        }
    }
}

@Composable
fun MyBox(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier
){
    Box(modifier = modifier.fillMaxWidth()){
        Text(
            text = stringResource(id = R.string.compose),
            fontSize = 22.sp,
            modifier = contentModifier.align(Alignment.TopEnd)
        )
        Button(onClick = { /*TODO*/ },modifier = contentModifier.align(Alignment.TopCenter)) {
            Text(text = "My Box Button")
        }
        Icon( Icons.Filled.AccountBox, contentDescription = "Account Box Face")
    }
}

/**
 * Here we will resize the item when the button is clicked.
 * We will learn to manage the state of the item : indicating if
 * item is expanded or not
 */
@Composable
fun NotWorkingVersionStateManagementCompose(name: String = "Ninja"){
    var expanded = false // Not Working
    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)) {
            Column (modifier = Modifier.weight(1f)){
                Text(text = "Name: $name")
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Expanded..." else "Collapsed")
            }
        }
    }
}



@Composable
fun ComposableExpandables(
    modifier: Modifier = Modifier, 
    names: List<String> = listOf("Barney Ross", "Lee Christmas", "Galgo", "Yin Yang", "Doc", "Gunner Jensen", "Toll Road", "Hale Caesar", "The Kid","John Smilee", "Luna", "Thorn", "Mars", "Hammer", "Woodsman", "Tool")
){
    LazyColumn {
        items(names) {
            Card (
                modifier = modifier,
                colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary)
            ){
                ComposeExpandableScreen(name = it)
            }
        }
    }
}


@Composable
fun ComposeExpandableScreen(name: String = "Ninja"){
    var expanded by rememberSaveable {
        mutableStateOf(false)
    } // Now it works -> the composable will automatically subscribed to the state
    // if the state changes, composable will read the parameter and recompose the UI
    val extraSpace by animateDpAsState(
        targetValue = if (expanded) 50.dp else 0.dp, label = "",
        /**
         * animationSpec is an optional parameter to customize the animation
         */
        animationSpec = tween(
            durationMillis = 100
        )
    )
    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)) {
            Column (modifier = Modifier
                .weight(1f)
                .padding(bottom = extraSpace.coerceAtLeast(0.dp))){
                Text(text = "Name: $name", fontWeight = FontWeight.ExtraBold)
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Expanded..." else "Collapsed")
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 200)
@Composable
fun ModifierComposables(){
    var name by rememberSaveable {
        mutableStateOf("")
    }
    Box(modifier = Modifier.size(150.dp)){
        Text(text = name,
            modifier = Modifier
                .background(Color.Green)
                .size(200.dp, 40.dp)
                .padding(5.dp)
                .alpha(0.5f)
                .align(Alignment.BottomCenter)
                .clickable {
                    name = "ninja"
                }
        )
    }
}


@Preview(
    showBackground = true,
    heightDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, heightDp = 320)
@Composable
fun PreviewExpandables(){
    AndroidComposeTutorialTheme {
        ComposableExpandables()
    }
}


