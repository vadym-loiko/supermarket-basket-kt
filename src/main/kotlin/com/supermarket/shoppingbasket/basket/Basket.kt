package com.supermarket.shoppingbasket.basket

data class Basket(var basketId: Long?) {
    var items: List<BasketItem> = ArrayList()
}
