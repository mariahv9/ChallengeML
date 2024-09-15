package com.challenge.data.network

import com.challenge.common.Constants.DETAIL_PRODUCT
import com.challenge.common.Constants.SEARCH_PRODUCT
import com.challenge.data.model.ProductDetailResponse
import com.challenge.data.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(SEARCH_PRODUCT)
    suspend fun searchProduct(@Query("q") query: String): Response<ProductResponse?>

    @GET(DETAIL_PRODUCT)
    suspend fun getProductDetails(@Path("productId") productId: String): Response<ProductDetailResponse?>
}
