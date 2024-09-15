package com.challenge.domain.model

class ProductDetail(
    val title: String,
    val freeShipping: Boolean,
    val condition: String,
    val originalPrice: String?,
    val price: String,
    val picture: List<String>
)
