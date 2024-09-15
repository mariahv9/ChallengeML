package com.challenge.domain.mapper

import com.challenge.common.Transform
import com.challenge.common.toMoneyFormat
import com.challenge.data.model.ProductResponse
import com.challenge.domain.model.Products
import javax.inject.Inject

class ProductResponseToProductMapper @Inject constructor() : Transform<ProductResponse, Products> {
    override fun map(value: ProductResponse): Products {
        return Products(
            products = checkNotNull(value.results).mapNotNull {
                it?.let { product ->
                    val originalPrice = product.originalPrice?.toInt()
                    val price = product.price?.toInt()
                    Products.Product(
                        id = product.id,
                        title = product.title ?: "",
                        pictureUrl = product.thumbnail ?: "",
                        originalPrice = originalPrice?.toMoneyFormat(),
                        price = price?.toMoneyFormat() ?: "",
                        officialStoreName = product.officialStoreName ?: "",
                    )
                }
            }
        )
    }
}
