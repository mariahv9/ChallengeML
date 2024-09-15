package com.challenge.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val results: List<Product?>? = null,
) {
    data class Product(
        val id: String,
        val title: String? = null,
        val thumbnail: String? = null,
        @SerializedName("original_price")
        val originalPrice: Number? = null,
        val price: Number? = null,
        @SerializedName("official_store_name")
        val officialStoreName: String? = null
    )
}
