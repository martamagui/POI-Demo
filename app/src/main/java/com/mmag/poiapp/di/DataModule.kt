package com.mmag.poiapp.di

import android.content.Context
import com.mmag.poiapp.data.network.POIService
import com.mmag.poiapp.data.network.repository.NetworkRepository
import com.mmag.poiapp.data.network.repository.NetworkRepositoryDefault
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNetworkRepository(service: POIService): NetworkRepository =
        NetworkRepositoryDefault(service)
}