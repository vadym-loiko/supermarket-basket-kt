package com.supermarket.shoppingbasket.basket

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/basket")
class BasketController(private val basketService: BasketService) {
    @get:GetMapping("/all")
    val allBaskets: List<Any>
        get() = basketService.getAllBaskets()

    @PostMapping("/new")
    fun addNewBasket(): Basket {
        return basketService.addNewBasket()
    }

    @GetMapping("/{basketId}")
    @Throws(BasketNotFoundException::class)
    fun getBasketById(@PathVariable basketId: Long?): Basket {
        return basketService.getBasketById(basketId)
    }

    @PostMapping("/{basketId}/addItem")
    @Throws(BasketNotFoundException::class)
    fun addNewItemIntoBasket(@PathVariable basketId: Long?, @RequestBody itemDto: BasketItem) {
        basketService.addNewItemIntoBasket(basketId!!, itemDto)
    }

    @DeleteMapping("/{basketId}/{basketItemId}")
    @Throws(BasketNotFoundException::class)
    fun deleteItemFromBasket(@PathVariable basketId: Long?, @PathVariable basketItemId: Long?) {
        basketService.deleteItemFromBasket(basketId, basketItemId)
    }

    @GetMapping("/{basketId}/totalPrice")
    @Throws(BasketNotFoundException::class)
    fun calculateTotalPrice(@PathVariable basketId: Long?): Int? {
        return basketService.calculateTotalPrice(basketId)
    }
}
