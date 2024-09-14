package com.niranjan.khatri.androidcomposetutorial.concepts.widgets_glance

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.Text


val smallSize = DpSize(180.dp, 110.dp) // Example size for Small
val mediumSize = DpSize(270.dp, 110.dp) // Example size for Medium
val largeSize = DpSize(270.dp, 280.dp) // Example size for Large

val dpSizes = setOf(smallSize, mediumSize, largeSize)

// Define WidgetData as a String key
private val WidgetData = intPreferencesKey("widget_data")

class MyWidget : GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Responsive(dpSizes)

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val prefs = context.getSharedPreferences("widget_prefs", Context.MODE_PRIVATE)
        updateAppWidgetState(context, id) { state ->
            state[WidgetData] = prefs.getInt("widget_data", 0)
        }
    }
}

class MyWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyWidget()
}

@Composable
fun MyWidgetContent() {
    val data = currentState<Int>()
    GlanceTheme {
        Column(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = data.toString())
        }
    }
}

@Preview
@Composable
fun MyWidgetContentPreview() {
    MyWidgetContent() // Use LocalContext.current for preview
}