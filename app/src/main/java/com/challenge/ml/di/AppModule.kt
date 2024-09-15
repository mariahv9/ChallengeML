package com.challenge.ml.di

import com.challenge.domain.di.DomainBindsModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DomainBindsModule::class
    ]
)

@InstallIn(SingletonComponent::class)
object AppModule
