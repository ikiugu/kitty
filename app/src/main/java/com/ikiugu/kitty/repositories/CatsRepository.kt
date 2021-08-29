package com.ikiugu.kitty.repositories

import com.ikiugu.kitty.models.SimpleCatResponse

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

interface CatsRepository {
    suspend fun getRandomCat(): SimpleCatResponse
}