package com.niranjan.khatri.androidcomposetutorial.chatapp.widget.model


import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.glance.appwidget.GlanceAppWidgetManager
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.ChatAppWidget
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetModelRepository @Inject internal constructor(
    @ApplicationContext private val appContext: Context,
) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface WidgetModelRepositoryEntryoint {
        fun widgetModelRepository(): WidgetModelRepository
    }

    companion object {
        fun get(applicationContext: Context): WidgetModelRepository {
            var widgetModelRepositoryEntryoint: WidgetModelRepositoryEntryoint = EntryPoints.get(
                applicationContext,
                WidgetModelRepositoryEntryoint::class.java,
            )
            return widgetModelRepositoryEntryoint.widgetModelRepository()
        }
    }

    suspend fun cleanupWidgetModels(context: Context) {
        coroutineScope {
            val widgetManager = GlanceAppWidgetManager(context)
            val widgetIds =
                widgetManager.getGlanceIds(ChatAppWidget::class.java).map { glanceId ->
                    widgetManager.getAppWidgetId(glanceId)
                }.toList()
        }
    }
}