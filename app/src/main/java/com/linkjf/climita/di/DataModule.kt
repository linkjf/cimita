package com.linkjf.climita.di

import com.linkjf.climita.data.repository.ForecastRepositoryImp
import com.linkjf.climita.data.repository.LocationSearchRepositoryImp
import com.linkjf.climita.domain.repository.ForecastRepository
import com.linkjf.climita.domain.repository.LocationSearchRepository
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
    fun provideLocationSearchRepository(
        locationSearchRepository: LocationSearchRepositoryImp
    ): LocationSearchRepository =
        locationSearchRepository

    @Provides
    @Singleton
    fun provideForecastRepository(
        forecastRepository: ForecastRepositoryImp
    ): ForecastRepository =
        forecastRepository

}
