package com.supermarket.shoppingbasket.basket

import com.supermarket.shoppingbasket.product.ProductRepository
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.function.Function
import java.util.stream.Collectors

@Service
class BasketServiceImpl(
    private val basketRepository: BasketRepository,
    private val productRepository: ProductRepository
) :
    BasketService {
    override fun getAllBaskets(): List<Basket> {
        val basketEntityList = basketRepository.findAll()
        return basketEntityList.stream().map { entity: BasketEntity ->
            copyBasketToDto(
                entity
            )
        }.collect(Collectors.toList())
    }

    override fun addNewBasket(): Basket {
        val basketEntity = basketRepository.saveAndFlush(BasketEntity())
        return copyBasketToDto(basketEntity)
    }

    @Throws(BasketNotFoundException::class)
    override fun getBasketById(basketId: Long?): Basket {
        val entity = basketRepository.findById(basketId!!).orElseThrow { BasketNotFoundException() }
        return copyBasketToDto(entity)
    }

    @Throws(BasketNotFoundException::class)
    override fun addNewItemIntoBasket(basketID: Long, itemDto: BasketItem) {
        val basketEntity = basketRepository.findById(basketID).orElseThrow { BasketNotFoundException() }
        val basketItemEntity = BasketItemEntity()
        basketItemEntity.amount = itemDto.amount
        if (itemDto.productId == null) {
            throw IllegalArgumentException()
        }
        basketItemEntity.product = productRepository.getById(itemDto.productId)
        basketEntity.addItem(basketItemEntity)
        basketRepository.saveAndFlush(basketEntity)
    }

    @Throws(BasketNotFoundException::class)
    override fun deleteItemFromBasket(basketId: Long?, basketItemId: Long?) {
        val basketEntity = basketRepository.findById(basketId!!).orElseThrow { BasketNotFoundException() }
        basketEntity.items!!.removeIf { item: BasketItemEntity -> item.basketItemId == basketItemId }
        basketRepository.saveAndFlush(basketEntity)
    }

    @Throws(BasketNotFoundException::class)
    override fun calculateTotalPrice(basketId: Long?): Int? {
        val basketEntity = basketRepository.findById(basketId!!).orElseThrow { BasketNotFoundException() }
        return basketEntity.items!!.stream().map { item: BasketItemEntity ->
            calculateItemPrice(
                item
            )
        }.mapToInt { obj: Int -> obj }.sum()
    }

    private fun calculateItemPrice(item: BasketItemEntity): Int {
        val product = item.product
        return if (product!!.promotionAmount != null) {
            val sumWithPromotion = item.amount!! % product.promotionAmount!! * product.productPrice!!
            val sumWithoutPromotion = item.amount!! / product.promotionAmount!! * product.promotionPriceForAmount!!
            sumWithPromotion + sumWithoutPromotion
        } else {
            product.productPrice!! * item.amount!!
        }
    }

    private fun copyBasketToDto(entity: BasketEntity): Basket {
        val dto = Basket(entity.basketId)
        BeanUtils.copyProperties(entity, dto, "items")
        dto.items = entity.items!!.stream().map(Function{ entity: BasketItemEntity ->
            copyBasketItemToDto(
                entity
            )
        }).collect(Collectors.toList())
        return dto
    }

    private fun copyBasketItemToDto(entity: BasketItemEntity): BasketItem {
        return BasketItem(entity.basketItemId, entity.basket?.basketId, entity.product?.productId, entity.amount)
    }
}
