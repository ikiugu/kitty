package com.ikiugu.kitty.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.ikiugu.kitty.network.CatApiService
import com.ikiugu.kitty.repositories.CatsRepository
import com.ikiugu.kitty.repositories.CatsRepositoryImpl
import com.ikiugu.kitty.util.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    // provide the cat api service to the entire app
    @Provides
    @Singleton
    fun provideCatApiService(): CatApiService =
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CatApiService::class.java)

    // provide the cat repository to be injected in the view model(s)
    @Provides
    @Singleton
    fun provideCatsRepository(catApiService: CatApiService): CatsRepository =
        CatsRepositoryImpl(catApiService)

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
    }

}