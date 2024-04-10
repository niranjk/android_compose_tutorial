package com.niranjan.khatri.androidcomposetutorial.list

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.layoutgroups.MyScaffoldScreen

/**
 * @author NIRANJAN KHATRI
 * @since 22/03/24
 * @version 1
 */

@Composable
fun ScrollingScreen() {
    MyScaffoldScreen()
}

@Preview(showBackground = true)
@Composable
fun MyScrollingScreen(modifier: Modifier = Modifier){
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState())
    ) {
        MyIcon(icon = Icons.Filled.Notifications, desc = "Note")
        MyIcon(icon = Icons.Filled.Person, desc = "Person")
        MyIcon(icon = Icons.Filled.MailOutline, desc = "Mail")
        MyIcon(icon = Icons.Filled.Lock, desc = "Lock")
        MyIcon(icon = Icons.Filled.Info, desc = "Info")
    }
}

@Preview(showBackground = true)
@Composable
fun MyVerticalScrollingScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        MyIcon(icon = Icons.Filled.Notifications, desc = "Note")
        MyIcon(icon = Icons.Filled.Person, desc = "Person")
        MyIcon(icon = Icons.Filled.MailOutline, desc = "Mail")
        MyIcon(icon = Icons.Filled.Lock, desc = "Lock")
        MyIcon(icon = Icons.Filled.Info, desc = "Info")
    }
}




@Composable
fun MyIcon(
    icon: ImageVector, desc: String
){
    Icon(icon,
        contentDescription = desc,
        modifier = Modifier.size(50.dp, 50.dp))
}