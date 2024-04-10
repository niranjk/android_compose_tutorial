package com.niranjan.khatri.androidcomposetutorial.layoutgroups

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
fun SurfaceScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()){
        MySurface(modifier = modifier.align(Alignment.Center))
    }

    BackButtonNavigator {
        ComposeFundamentalRouter.navigateTo(Screen.Navigation)
    }
}

@Preview(showBackground = true)
@Composable
fun MySurface(modifier: Modifier = Modifier){
    Surface(
        modifier = modifier.size(150.dp),
        color = Color.LightGray,
        contentColor = colorResource(id = R.color.colorPrimary),
        shadowElevation = 2.dp,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        MyRow()
        MyColumn()
    }
}