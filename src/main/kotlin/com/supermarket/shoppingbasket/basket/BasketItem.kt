package com.supermarket.shoppingbasket.basket

data class BasketItem(
    val basketItemId: Long?,
    val basketId: Long?,
    val productId: Long?,
    val amount: Int?
)
