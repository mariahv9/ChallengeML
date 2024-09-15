package com.challenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    val title: String?,
    val shipping: Shipping?,
    val condition: String?,
    val pictures: List<Picture?>?,

    @SerializedName("original_price")
    val originalPrice: Number?,
    val price: Number?,
) {
    data class Shipping(
        @SerializedName("free_shipping")
        val freeShipping: Boolean?,
    )

    data class Picture(
        @SerializedName("secure_url")
        val secureUrl: String?,
    )
}
