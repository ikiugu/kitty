package com.ikiugu.kitty.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikiugu.kitty.network.CatApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@HiltViewModel
class CatViewModel @Inject constructor(private val catApiService: CatApiService) :
    ViewModel() {

    fun getRandomKitties() {
        Timber.i("Getting random cats")
        viewModelScope.launch {
            val res = catApiService.getRandomCat()
            Timber.i(res[0].url)
        }
    }

    fun getCatBreeds() {
        Timber.i("Getting cat breeds")
        viewModelScope.launch {
            val res = catApiService.getBreeds()
            Timber.i(res[0].name)
        }
    }

    fun getCatBreedsById(breedId: String) {
        Timber.i("Getting cat breeds by id")
        viewModelScope.launch {
            val res = catApiService.getBreedById(breedId)
            Timber.i(res[0].breeds[0].name)
        }
    }
}