package com.ikiugu.kitty.di

import com.google.gson.GsonBuilder
import com.ikiugu.kitty.network.CatApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // 'provide' the cat api service to the entire app
    @Provides
    @Singleton
    fun provideCatApiService(): CatApiService =
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CatApiService::class.java)

}