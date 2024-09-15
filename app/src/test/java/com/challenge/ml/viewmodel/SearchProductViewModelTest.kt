package com.challenge.ml.viewmodel

import com.challenge.common.Resource
import com.challenge.domain.model.Products
import com.challenge.domain.usecase.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchProductViewModelTest {

    @MockK
    private lateinit var searchProductUseCase: SearchUseCase

    @InjectMockKs
    private lateinit var searchProductViewModel: SearchProductViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `fetchSearchProduct returns success`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        val mockProducts = Products(emptyList())
        coEvery { searchProductUseCase("product") } returns Resource.Success(mockProducts)

        searchProductViewModel.fetchSearchProduct("product")

        val result = (searchProductViewModel.product.value as Resource.Success).data

        Assert.assertEquals(result, mockProducts)
    }
}
