package com.supermarket.shoppingbasket.basket

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BasketRepository: JpaRepository<BasketEntity, Long>
