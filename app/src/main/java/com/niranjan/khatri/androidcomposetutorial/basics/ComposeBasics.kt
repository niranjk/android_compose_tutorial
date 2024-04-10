package com.niranjan.khatri.androidcomposetutorial.basics

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.niranjan.khatri.androidcomposetutorial.Greeting
import java.time.LocalDate

/**
 * @author NIRANJAN KHATRI
 * @since 20/03/24
 * @version 1
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {
    Column {
        Greeting("World")
        Text(text = "Today is: ${LocalDate.now()}")
    }
}

data class User(
    val name: String,
    val pwd: String
)
@Composable
fun WelcomeMessage(user: User?) {
    if (user != null) {
        Text(text = "Welcome back, ${user.name}!")
    } else {
        Text(text = "Please sign in.")
    }
}


// DECLARATIVE EXAMPLE :
// DESCRIBE What not How !!!
data class Event(
    val guests: List<Int> = listOf(),
    val isRoomSelected : Boolean,
    val room: String
)
@Composable
fun Room(
     name: String
){
    // Room Composable functions
}
@Composable
fun Guests(
    collapsed: Boolean,
    onGuestsCliked: () -> Unit
){
    // Your Guest composable function
}
@Composable
fun EventCard(event: Event) {
    // ... code to display title, owner, call link
    if (event.guests.size > 0) {
        Guests(collapsed = event.guests.size > 5) {
            // ... code for guest list badge
        }
    }
    if (event.isRoomSelected) {
        Room(event.room)
    }
}

// Encapsulation Example:
data class Product(
    val name: String,
    val price: String
)
// Instead of having everything scattered around...
@Composable
fun ProductScreen(product: Product) {
    // Image(// ... product image logic)
        Text(text = product.name)
                Text(product.price)
                Button(onClick = { /* Add to cart logic */ }){
                    Text(text = "Button Screen")
                }
}

// Encapsulate the product card logic!
@Composable
fun ProductCard(product: Product) {
    // Image(// ... product image logic)
        Text(text = product.name)
                Text(text = product.price)
                Button(onClick = { /* Add to cart logic */ }){
                    Text(text = "Button Card")
                }
}
// Now you can reuse the ProductCard composable anywhere!
/*
@Composable
fun ProductScreen(product: Product) {
    ProductCard(product)
}

 */