package com.niranjan.khatri.androidcomposetutorial.chatapp.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class ChatRepository @Inject internal constructor(
    @ApplicationContext private val appContext: Context,
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val enableChatbotKey = booleanPreferencesKey("enable_chatbot")
    val isBotEnabled = appContext.dataStore.data.map {
            preference ->
        preference[enableChatbotKey] ?: false
    }

    private var currentChat: Long = 0L

    fun getChats(): Flow<List<ChatDetail>> {
        return flow {
            ChatDetail()
        }
    }
}

data class ChatDetail(
    val id : Int = Random.nextInt(),
    val message : String = ""
)