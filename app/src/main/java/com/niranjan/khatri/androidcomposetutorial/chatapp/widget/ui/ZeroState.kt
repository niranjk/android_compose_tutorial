package com.niranjan.khatri.androidcomposetutorial.chatapp.widget.ui

import com.niranjan.khatri.androidcomposetutorial.MainActivity
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.ChatAppWidgetConfigActivity


import android.appwidget.AppWidgetManager
import androidx.compose.runtime.Composable
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize

@Composable
fun ZeroState(modifier: GlanceModifier = GlanceModifier, widgetId: Int) {
    val widgetIdKey = ActionParameters.Key<Int>(AppWidgetManager.EXTRA_APPWIDGET_ID)
    Scaffold(
        titleBar = {
            TitleBar(
                modifier = GlanceModifier.clickable(actionStartActivity(MainActivity::class.java)),
                textColor = GlanceTheme.colors.onSurface,
                startIcon = ImageProvider(R.drawable.ic_launcher_foreground),
                title = "SociaLite",
            )
        },
        backgroundColor = GlanceTheme.colors.widgetBackground,
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = GlanceModifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(
                text = "Select Favorite Contact",
                onClick = actionStartActivity<ChatAppWidgetConfigActivity>(
                    parameters = actionParametersOf(widgetIdKey to widgetId),
                ),
            )
        }
    }
}