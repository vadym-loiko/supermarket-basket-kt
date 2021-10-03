package com.supermarket.shoppingbasket.product

import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private var productRepository: ProductRepository
) : ProductService {

    override fun getAllProducts(): List<Product> {
        val productDtoList: MutableList<Product> = ArrayList()
        productRepository.findAll().forEach { entity: ProductEntity? ->
            val productDto = Product(
                entity?.productId,
                entity?.productName,
                entity?.productPrice,
                entity?.promotionAmount,
                entity?.promotionPriceForAmount
            )
            productDtoList.add(productDto)
        }

        return productDtoList
    }
}
