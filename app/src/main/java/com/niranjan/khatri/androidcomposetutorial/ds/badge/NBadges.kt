package com.niranjan.khatri.androidcomposetutorial.ds.badge

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NBadgesNoContent(){
    BadgedBox(
        badge = { Badge { /* No content for small badge */ } }
    ) {
        Icon(imageVector = Icons.Filled.Home,
            contentDescription = "Home"
        )
    }
}

@Composable
fun NBadgesWithContent(){
    BadgedBox(
        badge = { Badge { Text(text = "99+") } }
    ) {
        Icon(imageVector = Icons.Filled.Notifications,
            contentDescription = "Notifications"
        )
    }
}