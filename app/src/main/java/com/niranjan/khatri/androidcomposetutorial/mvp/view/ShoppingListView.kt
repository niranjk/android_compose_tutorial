package com.niranjan.khatri.androidcomposetutorial.mvp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.niranjan.khatri.androidcomposetutorial.mvp.model.ShoppingItem
import com.niranjan.khatri.androidcomposetutorial.mvp.presenter.ShoppingListPresenter

/**
 * @author NIRANJAN KHATRI
 * @since 16/02/24
 * @version 1
 */
@Composable
fun ShoppingListView(presenter: ShoppingListPresenter){
    val items = remember { mutableStateListOf<ShoppingItem>() } // Use mutable state
    LaunchedEffect(Unit) {
        presenter.addShoppingListChangeListener { newList ->
            items.clear()
            items.addAll(newList)
        }
    }

    Column {
        Text(text = "Shopping List")
        Spacer(modifier = Modifier.height(8.dp))
        ShoppingItemList(items = items.toList(), presenter = presenter)
        Button(onClick = { presenter.addShoppingItem("New Item ") }) {
            Text("Add Item")
        }
    }
}


@Composable
fun ShoppingItemList(items: List<ShoppingItem>, presenter: ShoppingListPresenter){
    items.forEach { item ->
        ShoppingItemRow(item, presenter::removeShoppingItem)
    }
}

@Composable
fun ShoppingItemRow(
    item: ShoppingItem, 
    onItemClicked: (Int) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(item.id) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.shoppingItem,
            modifier = Modifier.weight(1f)
            )
    }
}