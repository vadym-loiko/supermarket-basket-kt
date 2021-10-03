package com.supermarket.shoppingbasket.basket

import com.fasterxml.jackson.annotation.JsonBackReference
import com.supermarket.shoppingbasket.product.ProductEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "T_BASKET_ITEM")
class BasketItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_basket_item_id")
    @Column(name = "basket_item_id")
    var basketItemId: Long? = null

    @ManyToOne
    @JoinColumn(name = "basket_id")
    @JsonBackReference
    var basket: BasketEntity? = null

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: ProductEntity? = null

    @Column(name = "basket_item_amount")
    var amount: Int? = null

    override fun hashCode(): Int {
        return Objects.hash(basket, product)
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj !is BasketItemEntity) {
            return false
        }
        val basketItemEntity = obj
        return basketItemEntity.product == product &&
                basketItemEntity.basket == basket
    }
}
