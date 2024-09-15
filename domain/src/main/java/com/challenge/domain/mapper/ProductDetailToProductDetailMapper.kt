package com.challenge.domain.mapper

import com.challenge.common.Transform
import com.challenge.common.toMoneyFormat
import com.challenge.data.model.ProductDetailResponse
import com.challenge.domain.model.ProductDetail
import javax.inject.Inject

class ProductDetailToProductDetailMapper @Inject constructor() :
    Transform<ProductDetailResponse, ProductDetail> {
    override fun map(value: ProductDetailResponse): ProductDetail {
        return ProductDetail(
            title = value.title ?: "",
            freeShipping = value.shipping?.freeShipping == true,
            condition = value.condition ?: "",
            price = value.price?.toInt()?.toMoneyFormat() ?: "",
            originalPrice = value.originalPrice?.toInt()?.toMoneyFormat(),
            picture = value.pictures?.mapNotNull { it?.secureUrl } ?: emptyList(),
        )
    }
}
