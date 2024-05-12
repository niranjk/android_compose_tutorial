package com.niranjan.khatri.androidcomposetutorial.basics

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.niranjan.khatri.androidcomposetutorial.Greeting
import com.niranjan.khatri.androidcomposetutorial.R
import com.niranjan.khatri.androidcomposetutorial.list.MyItem
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
    val isRoomSelected: Boolean,
    val room: String
)

@Composable
fun Room(
    name: String
) {
    // Room Composable functions
}

@Composable
fun Guests(
    collapsed: Boolean,
    onGuestsCliked: () -> Unit
) {
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
    Button(onClick = { /* Add to cart logic */ }) {
        Text(text = "Button Screen")
    }
}

// Encapsulate the product card logic!
@Composable
fun ProductCard(
    product: Product = Product("Car", "  Rs. 12,000"),
    isProductSelected: Boolean = false,
    onSelected: (product: Product) -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(18.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_car),
            contentDescription = "Girl",
            modifier = Modifier.size(50.dp)
        )
        Text(text = product.name, modifier = Modifier.align(Alignment.CenterVertically))
        Text(text = product.price, modifier = Modifier.align(Alignment.CenterVertically))
        RadioButton(selected = isProductSelected, onClick = { onSelected(product) })
    }
}


// Now you can reuse the ProductCard composable anywhere!

@Composable
fun ProductScreenList(
    productList: List<Product> = listOf(
        Product(name = "Maruti", "  Rs. 1,000"),
        Product(name = "Suzuki", "  Rs. 12,000"),
        Product(name = "BMW", "  Rs. 25,000"),
    )
) {
    // variable that holds the selected product
    // we use kotlin's delegated property syntax using by keyword
    // doing so changes the type of our variable to an optional answer
    // this syntax will let us work directly with the value of the underlying state.
    // we don't need to call the value property of the mutable state
    var selectedProduct: Product? by rememberSaveable {
        mutableStateOf(null) // UI will survive recomposition + config changes
    }

    // we need to tell composable that when this variable changes
    // recomposition should be done to do that we need a mutable state object
    Column {
        if (productList.isEmpty()) {
            Text(text = "There is no product list to choose from!")
        } else {
            productList.forEach { product ->
                ProductCard(product = product,
                    isProductSelected = (selectedProduct == product),
                    onSelected = { selected ->
                        selectedProduct = selected
                    }
                )
            }
        }
    }
}



@Composable
fun MyComposeBehaviourOrder(){
    MyTopScreen() // This will be executed first ??? No It can happen in any order
    MyMiddleScreen("Ninja")
    MyBottomScreen()
}

@Composable
fun MyTopScreen(){
    Text(text = "Top")
}

@Composable
fun MyMiddleScreen(name: String){
    Text(text = name)
}

@Composable
fun MyBottomScreen(){
    Text(text = "Bottom")
}


/***
 * This code runs side-effect free and transforms the input list to UI
 */
@Composable
fun SideEffectFreeComposables(myInput: List<String>){
    Row (horizontalArrangement =  Arrangement.SpaceBetween){
        Column {
            for (input in myInput){
                Text(text = "My Input: $input")
            }
        }
        Text(text = "Input Count: ${myInput.size}")
    }
}


/***
 * This code runs with side-effect and effect the behaviour of compose recomposition
 */
@Composable
fun SideEffectComposables(myInput: List<String> = listOf("A", "B", "C")) {
    var items = 0
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            for (input in myInput) {
                Text(text = "My Input: $input")
                items++ // !! Avoid this side effect behaviour of the compose recomposition
            }
        }
        Text(text = "Input Count: ${myInput.size}")
    }
}


@Composable
fun RecompositionSkipping(name: String = "Compose"){
    Column {
        MyTopScreen()  // skipped recomposition
        MyMiddleScreen(name = name) // recomposition occurs here
        MyBottomScreen()  // skipped recomposition
    }
}

@Composable
fun MyComposableTestable(){
    Text(text = "Namaste, Compose!")
}