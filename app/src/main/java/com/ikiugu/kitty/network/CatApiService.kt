package com.ikiugu.kitty.network

import com.ikiugu.kitty.models.SimpleCatResponse
import retrofit2.http.GET

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCat() : SimpleCatResponse
}