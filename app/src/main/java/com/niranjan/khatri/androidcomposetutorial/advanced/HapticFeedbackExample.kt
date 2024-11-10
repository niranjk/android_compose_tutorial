package com.niranjan.khatri.androidcomposetutorial.advanced

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
fun HaptickFeedbackExample() {
    val hapticFeedback = LocalHapticFeedback.current

    Button(onClick = {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
    }) {
        Text("Trigger Haptic Feedback")
    }
}
