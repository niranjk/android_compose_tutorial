package com.niranjan.khatri.androidcomposetutorial.chatapp.widget


import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.model.WidgetModelRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ChatAppWidget()

    @Inject
    lateinit var repository: WidgetModelRepository

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
    }
}