package com.challenge.domain.repository

import com.challenge.common.Resource
import com.challenge.domain.model.Products
import com.challenge.domain.model.ProductDetail

interface ProductRepository {
    suspend fun searchProduct(query: String): Resource<Products>
    suspend fun productDetails(productId: String): Resource<ProductDetail>
}
