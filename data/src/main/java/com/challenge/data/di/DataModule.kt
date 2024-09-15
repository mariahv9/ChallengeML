package com.challenge.data.di

import com.challenge.data.datasource.ProductDataSource
import com.challenge.data.datasource.ProductRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun provideDataSource(impl: ProductRemoteDataSource): ProductDataSource
}
