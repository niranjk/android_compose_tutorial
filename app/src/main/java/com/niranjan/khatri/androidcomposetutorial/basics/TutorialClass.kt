package com.niranjan.khatri.androidcomposetutorial.basics

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.layout.layout
import androidx.room.Database

/**
 * @author NIRANJAN KHATRI
 * @since 08/05/24
 * @version 1
 */

class MyActivity : ComponentActivity() {

    private var database: Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
        }
        // Initialize the database
        // database = Database.getInstance(this)
    }

    override fun onStart() {
        super.onStart()
        // Open the database connection
        // database?.open()
    }

    override fun onStop() {
        super.onStop()
        // Close the database connection
        // database?.close()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Release any other resources that the app is using
    }
}