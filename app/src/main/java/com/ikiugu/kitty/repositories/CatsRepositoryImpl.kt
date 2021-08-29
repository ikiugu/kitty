package com.ikiugu.kitty.repositories

import com.ikiugu.kitty.models.Breed
import com.ikiugu.kitty.models.Categories
import com.ikiugu.kitty.models.SimpleCatResponse
import com.ikiugu.kitty.network.CatApiService
import javax.inject.Inject

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

class CatsRepositoryImpl @Inject constructor(private val catApiService: CatApiService) : CatsRepository {
    override suspend fun getRandomCat(): SimpleCatResponse {
        return catApiService.getRandomCat()
    }

    override suspend fun getCatBreeds(): Breed {
        return catApiService.getBreeds()
    }

    override suspend fun getCatBreedsById(breedId: String): SimpleCatResponse {
        return catApiService.getBreedById(breedId)
    }

    override suspend fun getCategories(): Categories {
        return catApiService.getCategories()
    }

}