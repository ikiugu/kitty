package com.ikiugu.kitty.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikiugu.kitty.models.favorites.SaveFavoriteRequestBody
import com.ikiugu.kitty.repositories.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@HiltViewModel
class CatViewModel @Inject constructor(private val catsRepository: CatsRepository) :
    ViewModel() {

    fun getRandomKitties() {
        Timber.i("Getting random cats")
        viewModelScope.launch {
            val res = catsRepository.getRandomCat()
            Timber.i(res[0].url)
        }
    }

    fun getCatBreeds() {
        Timber.i("Getting cat breeds")
        viewModelScope.launch {
            val res = catsRepository.getCatBreeds()
            Timber.i(res[0].name)
        }
    }

    fun getCatBreedsById(breedId: String) {
        Timber.i("Getting cat breeds by id")
        viewModelScope.launch {
            val res = catsRepository.getCatBreedsById(breedId)
            Timber.i(res[0].breeds[0].name)
        }
    }

    fun getCategories() {
        Timber.i("Getting all search categories")
        viewModelScope.launch {
            val res = catsRepository.getCategories()
            Timber.i(res[0].name)
        }
    }

    fun getImagesByCategories(categoryId: String) {
        Timber.i("Searching by category")
        viewModelScope.launch {
            val res = catsRepository.getImagesByCategory(categoryId)
            Timber.i(res[0].url)
        }
    }

    fun saveFavoriteImage() {
        Timber.i("Searching by category")
        viewModelScope.launch {
            val requestBody = SaveFavoriteRequestBody("qIbc94meU", "ikiugu-123456789")
            val res = catsRepository.saveFavoriteImage(requestBody)
            Timber.i(res.message)
        }
    }
}