package com.niranjan.khatri.androidcomposetutorial.chatapp.ui.home

import androidx.lifecycle.ViewModel
import com.niranjan.khatri.androidcomposetutorial.chatapp.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: ChatRepository,
) : ViewModel() {

    val chats = repository
        .getChats()
}