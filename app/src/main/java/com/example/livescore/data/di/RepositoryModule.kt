package com.example.livescore.data.di

import com.example.livescore.data.remote.CricRadioApi
import com.example.livescore.data.repository.CricRadioRepositoryImpl
import com.example.livescore.domain.repository.CricRadioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCricRadioRepository(
        api: CricRadioApi
    ): CricRadioRepository {
        return CricRadioRepositoryImpl(api)
    }
}
