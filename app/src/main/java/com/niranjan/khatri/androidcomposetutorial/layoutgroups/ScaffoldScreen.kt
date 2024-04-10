package com.niranjan.khatri.androidcomposetutorial.layoutgroups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.niranjan.khatri.androidcomposetutorial.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author NIRANJAN KHATRI
 * @since 21/03/24
 * @version 1
 */

@Composable
fun ScaffoldScreen() {
    MyScaffoldScreen()
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldScreen() {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    Scaffold(
      containerColor = colorResource(id = R.color.colorPrimary),
        topBar = {
            MyTopAppBar(coroutineScope = coroutineScope)
        },
        bottomBar = {
            MyBottomAppBar()
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
            MyColumn()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    coroutineScope: CoroutineScope
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.compose),
                fontStyle = FontStyle.Normal,
                color = colorResource(id = R.color.purple_200),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    // Launch your task here
                }
            }) {
                Icon(Icons.Default.AccountBox, contentDescription = "Account Scaffold")
            }
        }
    )
}

@Composable
fun MyBottomAppBar(){
    BottomAppBar{
        MyRow()
    }
}