package com.supermarket.shoppingbasket.basket

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "T_BASKET")
class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_basket_id")
    @Column(name = "basket_id")
    var basketId: Long? = null

    @OneToMany(mappedBy = "basket", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var items: MutableSet<BasketItemEntity>? = null
        get() {
            if (field == null) {
                field = HashSet<BasketItemEntity>()
            }
            return field
        }

    fun addItem(item: BasketItemEntity) {
        item.basket = this
        items!!.add(item)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(basketId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is BasketEntity) {
            return false
        }
        return other.basketId == basketId
    }
}
