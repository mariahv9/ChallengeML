package com.challenge.domain.repository

import android.util.Log
import com.challenge.common.Resource
import com.challenge.common.Transform
import com.challenge.data.datasource.ProductDataSource
import com.challenge.data.model.ProductDetailResponse
import com.challenge.data.model.ProductResponse
import com.challenge.domain.model.ProductDetail
import com.challenge.domain.model.Products
import javax.inject.Inject

class ProductDataRepository @Inject constructor(
    private val searchProductMapper: Transform<ProductResponse, Products>,
    private val productDetailMapper: Transform<ProductDetailResponse, ProductDetail>,
    private val productDataSource: ProductDataSource
) : ProductRepository {
    override suspend fun searchProduct(query: String): Resource<Products> {
        return when (val resource = productDataSource.searchProduct(query)) {
            is Resource.Success -> {
                resource.data.let {
                    try {
                        val products = searchProductMapper.map(it)
                        Resource.Success(products)
                    } catch (e: Exception) {
                        Log.e("Search error", "Error: ${e.message}")
                        Resource.Failure(Exception("Null body in response"))
                    }
                }
            }

            is Resource.Failure -> resource
            is Resource.Loading -> TODO()
        }
    }

    override suspend fun productDetails(productId: String): Resource<ProductDetail> {
        return when (val resource = productDataSource.detailProduct(productId)) {
            is Resource.Success -> {
                resource.data.let {
                    try {
                        val detail = productDetailMapper.map(it)
                        Resource.Success(detail)
                    } catch (e: Exception) {
                        Log.e("Search error", "Error: ${e.message}")
                        Resource.Failure(Exception("Null body in response"))
                    }
                }
            }

            is Resource.Failure -> resource
            is Resource.Loading -> TODO()
        }
    }
}
