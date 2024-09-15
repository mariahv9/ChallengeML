package com.challenge.data.datasource

import android.util.Log
import com.challenge.common.Resource
import com.challenge.data.model.ProductDetailResponse
import com.challenge.data.model.ProductResponse
import com.challenge.data.network.ApiService
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : ProductDataSource {
    override suspend fun searchProduct(query: String): Resource<ProductResponse> {
        return try {
            val response = apiService.searchProduct(query)
            Log.d("API", "Response Code: ${response.code()}")
            Log.d("API", "Response Body: ${response.body()}")
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }


    override suspend fun detailProduct(productId: String): Resource<ProductDetailResponse> {
        return try {
            val response = apiService.getProductDetails(productId)
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Failure(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}
