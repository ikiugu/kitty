package com.ikiugu.kitty.network

import com.ikiugu.kitty.models.Breed
import com.ikiugu.kitty.models.Categories
import com.ikiugu.kitty.models.SearchByCategory
import com.ikiugu.kitty.models.SimpleCatResponse
import com.ikiugu.kitty.models.favorites.SaveFavoriteRequestBody
import com.ikiugu.kitty.models.favorites.SaveFavoriteResponse
import retrofit2.http.*

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

    @POST("favourites")
    suspend fun saveImageAsFavorite(
        @Body favoriteRequestBody: SaveFavoriteRequestBody,
        @Header("x-api-key") apiKey: String
    ): SaveFavoriteResponse
}