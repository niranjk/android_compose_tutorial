package com.niranjan.khatri.androidcomposetutorial.mvp.model

/**
 * @author NIRANJAN KHATRI
 * @since 16/02/24
 * @version 1
 */
interface ShoppingListModel {
    val toShopList : MutableList<ShoppingItem>
    fun addToShop(item: String)
    fun toggleToShop(id: Int)

    fun addToShopListChangeListener(listener: (List<ShoppingItem>) -> Unit)
    fun removeToShopListChangeListener(listener: (List<ShoppingItem>) -> Unit)
}

class InMemeoryToShopListModel : ShoppingListModel {
    private val _toshop = mutableListOf<ShoppingItem>()
    private val listeners = mutableListOf<(List<ShoppingItem>) -> Unit>()

    override val toShopList: MutableList<ShoppingItem>
        get() = _toshop

    override fun addToShop(item: String) {
        _toshop.add(
            ShoppingItem(
            _toshop.lastIndex + 1,
            item,
            false
        ))
    }

    override fun toggleToShop(id: Int) {
        val index = _toshop.indexOfFirst { it.id == id }
        if (index != -1){
            _toshop[index] = _toshop[index].copy(bought = !_toshop[index].bought)
        }
    }

    override fun addToShopListChangeListener(listener: (List<ShoppingItem>) -> Unit) {
        listeners.add(listener)
    }

    override fun removeToShopListChangeListener(listener: (List<ShoppingItem>) -> Unit) {
        listeners.forEach { it(toShopList) }
    }

}