package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.BackButtonNavigator
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.ComposeFundamentalRouter
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.Screen

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */


@Composable
fun ButtonScreen (){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        MyButton()
        MyRadioButton()
        MyFloatingActionButton()
        BackButtonNavigator {
            ComposeFundamentalRouter.navigateTo(Screen.Navigation)
        }
    }
}

@Preview
@Composable
fun MyFloatingActionButton(){
    FloatingActionButton(
        onClick = {},
        containerColor = Color.White) {
        Icon( Icons.Filled.Face, contentDescription = "FAB")
    }
}

@Preview
@Composable
fun MyButton(){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(), 
        border = BorderStroke(
            1.dp, 
            color = colorResource(id = R.color.black)
        )
    ) {
        Text(
            text = stringResource(id = R.string.compose),
            fontSize = 22.sp,
        )
    }
}

@Preview
@Composable
fun MyRadioButton(){
    val radioButtons = listOf(1, 2, 3)
    val selectedButtons = remember{ mutableStateOf(radioButtons.first()) }
    Column {
        radioButtons.forEach { index -> 
            val isSelected = index == selectedButtons.value
            val colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(id = R.color.colorPrimary),
                unselectedColor = colorResource(id = R.color.colorPrimaryDark),
                disabledSelectedColor = Color.LightGray
            )
            RadioButton(
                colors = colors,
                selected = isSelected,
                onClick = { selectedButtons.value == index })
        }
    }
}

