package com.niranjan.khatri.androidcomposetutorial.chatapp.widget

import android.content.Context
import android.content.Intent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import com.niranjan.khatri.androidcomposetutorial.MainActivity
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.model.WidgetModel
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.model.WidgetModelRepository
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.ui.FavoriteContact

class ChatAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val widgetId = GlanceAppWidgetManager(context).getAppWidgetId(id)
        val repository = WidgetModelRepository.get(context)

        provideContent {
            GlanceTheme {
                Text("Hello Jetpack Glance")
            }
        }
    }

    @Composable
    private fun Content(repository: WidgetModelRepository, widgetId: Int) {
        val model = WidgetModel(
            widgetId = 123,
            contactId = 123L,
            displayName = "Niran",
            photo = ""
        )
        val context = LocalContext.current
        FavoriteContact(
            model = model,
            onClick = actionStartActivity(
                Intent(context.applicationContext, MainActivity::class.java)
                    .setAction(Intent.ACTION_VIEW)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .setData("https://socialite.google.com/chat/${model.contactId}".toUri()),
            ),
        )
    }
}