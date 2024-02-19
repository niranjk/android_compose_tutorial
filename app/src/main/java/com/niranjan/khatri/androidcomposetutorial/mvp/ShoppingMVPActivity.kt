package com.niranjan.khatri.androidcomposetutorial.mvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.niranjan.khatri.androidcomposetutorial.mvp.model.InMemeoryToShopListModel
import com.niranjan.khatri.androidcomposetutorial.mvp.presenter.ShoppingListPresenterImpl
import com.niranjan.khatri.androidcomposetutorial.mvp.view.ShoppingListView

/**
 * @author NIRANJAN KHATRI
 * @since 16/02/24
 * @version 1
 */
class ShoppingMVPActivity : ComponentActivity() {

    val model = InMemeoryToShopListModel()
    val presenter = ShoppingListPresenterImpl(model)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListView(presenter = presenter)
        }
    }
}