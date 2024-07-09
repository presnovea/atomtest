package com.atom.example.data.di

import android.app.Application
import android.content.res.AssetManager
import com.atom.example.data.ChargersRepository
import com.atom.example.data.remote.AtomTestApi
import com.atom.example.domain.ChargerInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DI модуль хилта
 */
@InstallIn(SingletonComponent::class)
@Module
class AtomTestModule {

    @Singleton
    @Provides
    fun providesAtomTestApi(): AtomTestApi {
        return AtomTestApi.create()
    }

    @Singleton
    @Provides
    fun providesChargersRepository(
        api: AtomTestApi,
        assetManager: AssetManager
    ): ChargersRepository {
        return ChargersRepository(api, assetManager)
    }

    @Singleton
    @Provides
    fun providesChargerInteractor(chargerRepo: ChargersRepository): ChargerInteractor {
        return ChargerInteractor(chargerRepo)
    }

    @Provides
    fun provideAssetManager(application: Application): AssetManager {
        return  application.assets
    }
}