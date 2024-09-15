package com.challenge.domain.usecase

import com.challenge.common.Resource
import com.challenge.domain.model.Products
import com.challenge.domain.repository.ProductRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(query: String): Resource<Products> {
        return repository.searchProduct(query)
    }
}
