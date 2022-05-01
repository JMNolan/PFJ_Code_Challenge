package com.pilotflyingj.codechallenge.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.pilotflyingj.codechallenge.network.LocationService
import com.pilotflyingj.codechallenge.repository.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //url used in retrofit instance
    private const val baseUrl = "https://raw.githubusercontent.com/"

    //create service that will be used to make the api call for all locations
    @Provides
    @Singleton
    fun provideLocationService (retrofit: Retrofit) : LocationService {
        return retrofit.create(LocationService::class.java)
    }

    //create instance of retrofit object to be passed into the service creation
    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(httpClient.build())
            .build()
    }

    //create instance of repository using the created service. This will be injected into the viewModel
    @Provides
    @Singleton
    fun provideRepositoryInstance(locationService: LocationService): MapRepository{
        return MapRepository(locationService = locationService)
    }
}