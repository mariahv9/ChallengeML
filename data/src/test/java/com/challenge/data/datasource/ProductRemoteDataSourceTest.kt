package com.challenge.data.datasource

import com.challenge.common.Resource
import com.challenge.data.model.ProductDetailResponse
import com.challenge.data.network.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductRemoteDataSourceTest {

    @MockK
    private lateinit var apiService: ApiService

    private lateinit var productRemoteDataSource: ProductRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        productRemoteDataSource = ProductRemoteDataSource(apiService)
    }

    @Test
    fun `searchProduct handles failure`() = runBlocking {
        val exception = Exception("Test exception")
        coEvery { apiService.searchProduct("celular") } throws exception

        val result = productRemoteDataSource.searchProduct("celular")

        Assert.assertTrue(result is Resource.Failure)
        val failure = result as Resource.Failure
        Assert.assertTrue(failure.exception is Exception)
        Assert.assertEquals("Test exception", (failure.exception as Exception).message)
    }
}
