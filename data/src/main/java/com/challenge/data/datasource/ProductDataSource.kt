package com.challenge.data.datasource

import com.challenge.common.Resource
import com.challenge.data.model.ProductDetailResponse
import com.challenge.data.model.ProductResponse

interface ProductDataSource {
    suspend fun searchProduct(query: String): Resource<ProductResponse>
    suspend fun detailProduct(productId: String): Resource<ProductDetailResponse>
}
