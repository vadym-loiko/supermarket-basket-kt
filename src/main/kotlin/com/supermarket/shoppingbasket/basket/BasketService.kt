package com.supermarket.shoppingbasket.basket

interface BasketService {

    fun getAllBaskets(): List<Basket>

    fun addNewBasket(): Basket

    @Throws(BasketNotFoundException::class)
    fun getBasketById(basketId: Long?): Basket

    @Throws(BasketNotFoundException::class)
    fun addNewItemIntoBasket(basketID: Long, itemDto: BasketItem)

    @Throws(BasketNotFoundException::class)
    fun deleteItemFromBasket(basketId: Long?, basketItemId: Long?)

    @Throws(BasketNotFoundException::class)
    fun calculateTotalPrice(basketId: Long?): Int?
}
