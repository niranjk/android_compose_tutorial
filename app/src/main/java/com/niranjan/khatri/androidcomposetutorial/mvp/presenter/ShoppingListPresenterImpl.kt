package com.niranjan.khatri.androidcomposetutorial.mvp.presenter

import com.niranjan.khatri.androidcomposetutorial.mvp.model.ShoppingItem
import com.niranjan.khatri.androidcomposetutorial.mvp.model.ShoppingListModel

/**
 * @author NIRANJAN KHATRI
 * @since 16/02/24
 * @version 1
 */
class ShoppingListPresenterImpl(private val model: ShoppingListModel) : ShoppingListPresenter{
    override val shoppingItems: MutableList<ShoppingItem>
        get() = model.toShopList
    private val listeners = mutableListOf<(List<ShoppingItem>) -> Unit>()

    override fun addShoppingItem(item: String) {
        model.addToShop(item)
        notifyShoppingListChanged()
    }

    override fun removeShoppingItem(id: Int) {
        val index = model.toShopList.indexOfFirst { it.id == id }
        if (index != -1) {
            model.toShopList.removeAt(index)
            notifyShoppingListChanged()
        }
    }

    override fun addShoppingListChangeListener(listener: (List<ShoppingItem>) -> Unit) {
        listeners.add(listener)
    }

    override fun removeShoppingListChangeListener(listener: (List<ShoppingItem>) -> Unit) {
        listeners.remove(listener)
    }

    private fun notifyShoppingListChanged() {
        listeners.forEach { it(shoppingItems) }
    }

}