package com.challenge.domain.model

class Products(
    val products: List<Product>,
) {
    data class Product(
        val id: String,
        val title: String,
        val pictureUrl: String,
        val originalPrice: String?,
        val price: String,
        val officialStoreName: String?
    )
}
