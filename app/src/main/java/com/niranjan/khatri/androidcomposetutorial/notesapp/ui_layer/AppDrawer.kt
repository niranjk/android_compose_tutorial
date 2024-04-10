package com.niranjan.khatri.androidcomposetutorial.notesapp.ui_layer

/**
 * @author NIRANJAN KHATRI
 * @since 27/03/24
 * @version 1
 */


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AndroidComposeTutorialTheme
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AppThemeSettings


@Composable
fun AppDrawer(
    currentRouteScreen: RouteScreen,
    onRouteScreenSelected: (RouteScreen) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppDrawerHeader()
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(
                alpha =
                .2f
            )
        )
        RouteScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Notes",
            isSelected = currentRouteScreen == RouteScreen.Notes,
            onClick = {
                onRouteScreenSelected.invoke(RouteScreen.Notes)
            }
        )
        RouteScreenNavigationButton(
            icon = Icons.Filled.Delete,
            label = "Trash",
            isSelected = currentRouteScreen == RouteScreen.Trash,
            onClick = {
                onRouteScreenSelected.invoke(RouteScreen.Trash)
            }
        )
        LightDarkThemeItem()
    }
}

@Composable
private fun AppDrawerHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter
                .tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "JetNotes",
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Composable
private fun RouteScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    // Define alphas for the image for two different states
    // of the button: selected/unselected
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }
    // Define color for the text for two different states
    // of the button: selected/unselected
    val textColor = if (isSelected) {
        colorScheme.primary
    } else {
        colorScheme.onSurface.copy(alpha = 0.6f)
    }
    // Define color for the background for two different states
    // of the button: selected/unselected
    val backgroundColor = if (isSelected) {
        colorScheme.primary.copy(alpha = 0.12f)
    } else {
        colorScheme.surface
    }

    Surface( // 1
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Row( // 2
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = "RouteScreen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(Modifier.width(16.dp)) // 3
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LightDarkThemeItem() {
    Row(
        Modifier
            .padding(8.dp)
    ) {
        Text(
            text = "Turn on dark theme",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = 8.dp, top = 8.dp, end = 8.dp, bottom =
                    8.dp
                )
                .align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = AppThemeSettings.isDarkThemeEnabled,
            onCheckedChange =
            { AppThemeSettings.isDarkThemeEnabled = it },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun AppDrawerPreview() {
    AndroidComposeTutorialTheme {
        AppDrawer(RouteScreen.Notes, {})
    }
}

@Preview
@Composable
fun AppDrawerHeaderPreview() {
    AndroidComposeTutorialTheme {
        AppDrawerHeader()
    }
}

@Preview
@Composable
fun RouteScreenNavigationButtonPreview() {
    AndroidComposeTutorialTheme {
        RouteScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Notes",
            isSelected = true,
            onClick = { }
        )
    }
}

@Preview
@Composable
fun LightDarkThemeItemPreview() {
    AndroidComposeTutorialTheme {
        LightDarkThemeItem()
    }
}