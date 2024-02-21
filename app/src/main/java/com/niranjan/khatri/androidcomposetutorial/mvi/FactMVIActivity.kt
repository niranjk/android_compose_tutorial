package com.niranjan.khatri.androidcomposetutorial.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.niranjan.khatri.androidcomposetutorial.mvi.domain.KtorFactRepository
import com.niranjan.khatri.androidcomposetutorial.mvi.intent.FactViewModel
import com.niranjan.khatri.androidcomposetutorial.mvi.view.FactScreen

/**
 * @author NIRANJAN KHATRI
 * @since 21/02/24
 * @version 1
 */
class FactMVIActivity : ComponentActivity() {

    val repository = KtorFactRepository()
    val viewmodel = FactViewModel(repository)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FactScreen(viewModel = viewmodel)
        }
    }
}