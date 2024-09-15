package com.challenge.domain.di

import com.challenge.common.Transform
import com.challenge.data.model.ProductDetailResponse
import com.challenge.data.model.ProductResponse
import com.challenge.domain.mapper.ProductDetailToProductDetailMapper
import com.challenge.domain.mapper.ProductResponseToProductMapper
import com.challenge.domain.model.ProductDetail
import com.challenge.domain.model.Products
import com.challenge.domain.repository.ProductDataRepository
import com.challenge.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainBindsModule {

    @Binds
    abstract fun bindProductRepository(impl: ProductDataRepository): ProductRepository

    @Binds
    abstract fun bindProductResponseToProductMapper(impl: ProductResponseToProductMapper): Transform<ProductResponse, Products>

    @Binds
    abstract fun bindProductDetailToProductDetailMapper(impl: ProductDetailToProductDetailMapper): Transform<ProductDetailResponse, ProductDetail>
}
