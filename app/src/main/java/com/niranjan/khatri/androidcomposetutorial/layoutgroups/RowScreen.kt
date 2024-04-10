package com.niranjan.khatri.androidcomposetutorial.layoutgroups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.BackButtonNavigator
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.ComposeFundamentalRouter
import com.niranjan.khatri.androidcomposetutorial.jetfundamentals.router.Screen

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

@Composable
fun RowScreen() {
    MyRow()
    BackButtonNavigator {
        ComposeFundamentalRouter.navigateTo(Screen.Navigation)
    }
}

@Preview(showBackground = true)
@Composable
fun MyRow(){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "id1", fontSize = 16.sp)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "MyButton")
        }
        Icon( Icons.Filled.Face, contentDescription = "Column Face")
    }
}