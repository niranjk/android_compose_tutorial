package com.niranjan.khatri.androidcomposetutorial.chatapp.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.updateAll
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.niranjan.khatri.androidcomposetutorial.chatapp.model.Contact
import com.niranjan.khatri.androidcomposetutorial.chatapp.repository.ChatRepository
import com.niranjan.khatri.androidcomposetutorial.chatapp.ui.home.HomeAppBar
import com.niranjan.khatri.androidcomposetutorial.chatapp.ui.home.HomeBackground
import com.niranjan.khatri.androidcomposetutorial.chatapp.ui.home.HomeViewModel
import com.niranjan.khatri.androidcomposetutorial.chatapp.widget.model.WidgetModelRepository
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.NAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class ChatAppWidgetConfigActivity : ComponentActivity() {
    @Inject
    lateinit var widgetModelRepository: WidgetModelRepository

    @Inject
    lateinit var chatRepository: ChatRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modifier = Modifier.fillMaxSize()

        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_CANCELED, resultValue)

        enableEdgeToEdge()
        setContent {
            NAppTheme {
                Scaffold(
                    modifier = modifier,
                    topBar = { HomeAppBar(title = "Select a favorite contact") },
                ) { innerPadding ->

                    HomeBackground()
                    val viewModel: HomeViewModel = hiltViewModel()
                    LazyColumn(
                        modifier = modifier,
                        contentPadding = innerPadding,
                    ) {
                        items(items = Contact.CONTACTS) { contact ->

                            ContactRow(
                                contact = contact,
                                onClick = {
                                    runBlocking {
                                        ChatAppWidget().updateAll(this@ChatAppWidgetConfigActivity)
                                        val resultValue = Intent().putExtra(
                                            AppWidgetManager.EXTRA_APPWIDGET_ID,
                                            appWidgetId,
                                        )
                                        setResult(RESULT_OK, resultValue)
                                        finish()
                                    }
                                },
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ContactRow(
        contact: Contact,
        onClick: (() -> Unit)?,
        modifier: Modifier = Modifier,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .then(
                    if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier,
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // This only supports DM for now.
            Image(
                painter = rememberAsyncImagePainter(contact.iconUri),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                )
            }
        }
    }
}