package com.niranjan.khatri.androidcomposetutorial.chatapp.widget.ui


import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.appwidget.ImageProvider
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.wrapContentHeight
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.model.WidgetModel

@Composable
fun FavoriteContact(modifier: GlanceModifier = GlanceModifier, model: WidgetModel, onClick: Action) {
    Column(
        modifier = modifier.fillMaxSize().clickable(onClick)
            .background(GlanceTheme.colors.widgetBackground).appWidgetBackground()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.Vertical.Bottom,
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
    ) {
        Image(
            modifier = GlanceModifier.fillMaxWidth().wrapContentHeight().defaultWeight()
                .cornerRadius(16.dp),
            provider = ImageProvider(model.photo.toUri()),
            contentScale = ContentScale.Crop,
            contentDescription = model.displayName,
        )
        Column(
            modifier = GlanceModifier.fillMaxWidth().wrapContentHeight().padding(top = 4.dp),
            verticalAlignment = Alignment.Vertical.Bottom,
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        ) {
            Text(
                text = model.displayName,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = (GlanceTheme.colors.onSurface),
                ),
            )

            Text(
                text = if (model.unreadMessages) "New Message!" else "No messages",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = (GlanceTheme.colors.onSurface),
                ),
            )
        }
    }
}