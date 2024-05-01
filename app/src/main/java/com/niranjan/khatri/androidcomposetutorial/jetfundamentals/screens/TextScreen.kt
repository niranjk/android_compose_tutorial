package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
fun TextScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyText()
    }
    BackButtonNavigator {
        ComposeFundamentalRouter.navigateTo(Screen.Navigation)
    }
}

@Preview(showBackground = true)
@Composable
fun MyText(){
    Text(
        text = stringResource(id = R.string.compose),
        fontStyle = FontStyle.Italic,
        color = colorResource(id = R.color.purple_200),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun MyFormattedText(){
    val name = "Ninja"
    val formattedString = stringResource(id = R.string.compose_name, name)
    // LocalContext.current.resources.getString(R.string.compose) // Dynamic Resource Id
    Text(
        text = formattedString,
        fontStyle = FontStyle.Italic,
        color = colorResource(id = R.color.purple_200),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun MyPluralText(){
    val itemCountOne = 1
    val itemCountFive = 5
    val message = pluralStringResource(id = R.plurals.items_found, count = itemCountFive)
    Text(
        text = message,
        fontStyle = FontStyle.Italic,
        color = colorResource(id = R.color.purple_200),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}