package com.erolgizlice.satellite.core.network.di

import android.content.Context
import com.erolgizlice.satellite.core.network.AssetManagerInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesAssetManagerInterface(
        @ApplicationContext context: Context
    ): AssetManagerInterface = AssetManagerInterface(context.assets::open)
}