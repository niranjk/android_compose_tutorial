package com.niranjan.khatri.androidcomposetutorial.jetfundamentals.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
fun AlertDialogScreen(){
    MyAlertDialog()
    BackButtonNavigator {
        ComposeFundamentalRouter.navigateTo(Screen.Navigation)
    }
}

@Preview
@Composable
fun MyAlertDialog(){
    val shouldShowDialog = remember {
        mutableStateOf(true)
    }
    if (shouldShowDialog.value){
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
                ComposeFundamentalRouter.navigateTo(Screen.Navigation)
            },
            title = { Text(text = stringResource(id = R.string.alert_dialog_title))},
            text = { Text(text = stringResource(id = R.string.alert_dialog_message))},
            confirmButton = {
                Button(onClick = {
                    shouldShowDialog.value = false
                    ComposeFundamentalRouter.navigateTo(Screen.Navigation)
                }) {
                    Text(
                        text = stringResource(id = R.string.compose),
                        fontSize = 22.sp,
                    )
                }
            })
    }
}