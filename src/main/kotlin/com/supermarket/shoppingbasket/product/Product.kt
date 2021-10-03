package com.supermarket.shoppingbasket.product

data class Product(
    val productId: Long?,
    val productName: String?,
    val productPrice: Int?,
    val promotionAmount: Int?,
    val promotionPriceForAmount: Int?
)

