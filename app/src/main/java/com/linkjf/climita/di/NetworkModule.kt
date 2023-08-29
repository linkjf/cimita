package com.linkjf.climita.di

import com.linkjf.climita.BuildConfig
import com.linkjf.climita.data.repository.ForecastRemote
import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.api.API_KEY_REQUEST_PARAM
import com.linkjf.climita.remote.api.ForecastService
import com.linkjf.climita.remote.api.LocationSearchService
import com.linkjf.climita.remote.repository.ForecastRemoteImp
import com.linkjf.climita.remote.repository.LocationSearchRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newUrl = originalRequest.url.newBuilder()
                    .addQueryParameter(API_KEY_REQUEST_PARAM, BuildConfig.WEATHER_API_KEY)
                    .build()
                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideLocationSearchService(retrofit: Retrofit): LocationSearchService {
        return retrofit.create(LocationSearchService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationSearchRemote(
        locationSearchRemote: LocationSearchRemoteImp
    ): LocationSearchRemote =
        locationSearchRemote

    @Provides
    @Singleton
    fun provideForecastService(retrofit: Retrofit): ForecastService {
        return retrofit.create(ForecastService::class.java)
    }

    @Provides
    @Singleton
    fun provideForecastRemote(
        forecastRemoteImp: ForecastRemoteImp
    ): ForecastRemote =
        forecastRemoteImp
}
