package com.niranjan.khatri.androidcomposetutorial.layoutgroups

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun BoxScreen() {
    MyBox()
    BackButtonNavigator {
        ComposeFundamentalRouter.navigateTo(Screen.Navigation)
    }
}

@Preview(showBackground = true)
@Composable
fun MyBox(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier
){
    Box(modifier = modifier.fillMaxSize()){
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

