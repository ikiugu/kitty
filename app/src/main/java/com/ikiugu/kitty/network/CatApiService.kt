package com.ikiugu.kitty.network

import com.ikiugu.kitty.models.Breed
import com.ikiugu.kitty.models.Categories
import com.ikiugu.kitty.models.SearchByCategory
import com.ikiugu.kitty.models.SimpleCatResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCat(): SimpleCatResponse

    @GET("breeds")
    suspend fun getBreeds(): Breed

    @GET("images/search")
    suspend fun getBreedById(@Query("breed_ids") breedId: String): SimpleCatResponse

    @GET("categories")
    suspend fun getCategories(): Categories

    @GET("images/search")
    suspend fun searchImagesByCategory(@Query("category_ids") categoryId: String): SearchByCategory
}