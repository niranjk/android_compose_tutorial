package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.ComposeFundamentalRouter
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.Screen

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

@Composable
fun NavigationButton(text: String, screen: Screen){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp
            ),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(),
        onClick = {
            ComposeFundamentalRouter.navigateTo(screen)
        }) {
        Text(text = text,
            color = Color.White
        )
    }
}

@Composable
fun NavigationScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            NavigationButton(text = stringResource(id = R.string.text), screen = Screen.Text)
            NavigationButton(text = stringResource(id = R.string.text_field), screen = Screen.TextField)
            NavigationButton(text = stringResource(id = R.string.buttons), screen = Screen.Buttons)
            NavigationButton(text = stringResource(id = R.string.progress_indicators), screen = Screen.ProgressIndicator)
            NavigationButton(text = stringResource(id = R.string.text_field), screen = Screen.TextField)
            NavigationButton(text = stringResource(id = R.string.alert_dialog), screen = Screen.AlertDialog)
        }

    }
}