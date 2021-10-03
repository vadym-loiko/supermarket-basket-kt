package com.supermarket.shoppingbasket.product

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "T_PRODUCT")
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_id")
    @Column(name = "product_id")
    var productId: Long? = null

    @Column(name = "product_name")
    var productName: String? = null

    @Column(name = "product_price")
    var productPrice: Int? = null

    @Column(name = "promotion_amount")
    var promotionAmount: Int? = null

    @Column(name = "promotion_price_for_amount")
    var promotionPriceForAmount: Int? = null

    override fun hashCode(): Int {
        return Objects.hashCode(productName)
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj !is ProductEntity) {
            return false
        }
        return obj.productName == productName
    }
}

