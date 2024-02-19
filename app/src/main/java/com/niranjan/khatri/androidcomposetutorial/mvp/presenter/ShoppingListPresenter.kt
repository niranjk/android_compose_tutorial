package com.niranjan.khatri.androidcomposetutorial.mvp.presenter

import com.niranjan.khatri.androidcomposetutorial.mvp.model.ShoppingItem

/**
 * Interface for Shopping List Presenter
 * @author NIRANJAN KHATRI
 * @since 16/02/24
 * @version 1
 */
interface ShoppingListPresenter {
    val shoppingItems: List<ShoppingItem>
    fun addShoppingItem(item: String)
    fun removeShoppingItem(id: Int)
    fun addShoppingListChangeListener(listener: (List<ShoppingItem>) -> Unit)
    fun removeShoppingListChangeListener(listener: (List<ShoppingItem>) -> Unit)
}