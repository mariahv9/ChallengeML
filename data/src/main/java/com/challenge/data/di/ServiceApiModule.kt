package com.challenge.data.di

import com.challenge.common.Constants.BASE_URL
import com.challenge.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ServiceApiModule {
    @Provides
    fun provideApiService(): ApiService {
        return ApiClient.RequestBuilder<ApiService>()
            .setBaseUrl(BASE_URL)
            .setApiService(ApiService::class.java)
            .build()
    }
}
