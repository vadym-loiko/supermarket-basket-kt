package com.supermarket.shoppingbasket.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {

    @get:GetMapping("/all")
    val allProducts: List<Any>
        get() = productService.getAllProducts()

}
