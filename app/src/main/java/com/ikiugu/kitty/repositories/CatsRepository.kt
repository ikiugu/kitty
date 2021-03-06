package com.ikiugu.kitty.repositories

import com.ikiugu.kitty.models.Breed
import com.ikiugu.kitty.models.Categories
import com.ikiugu.kitty.models.SearchByCategory
import com.ikiugu.kitty.models.SimpleCatResponse
import com.ikiugu.kitty.models.favorites.Favorites
import com.ikiugu.kitty.models.favorites.SaveFavoriteRequestBody
import com.ikiugu.kitty.models.favorites.SaveFavoriteResponse

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

interface CatsRepository {
    suspend fun getRandomCat(mimeType: String): SimpleCatResponse
    suspend fun getCatBreeds(): Breed
    suspend fun getCatBreedsById(breedId: String): SimpleCatResponse
    suspend fun getCategories(): Categories
    suspend fun getImagesByCategory(categoryId: String, limit: String, mimeType: String): SearchByCategory
    suspend fun saveFavoriteImage(saveFavoriteRequestBody: SaveFavoriteRequestBody): SaveFavoriteResponse
    suspend fun getFavoriteImages(subId: String): Favorites
}