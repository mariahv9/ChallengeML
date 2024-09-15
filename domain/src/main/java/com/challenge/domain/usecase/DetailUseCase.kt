package com.challenge.domain.usecase

import com.challenge.common.Resource
import com.challenge.domain.model.ProductDetail
import com.challenge.domain.repository.ProductRepository
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Resource<ProductDetail> {
        return repository.productDetails(productId)
    }
}
