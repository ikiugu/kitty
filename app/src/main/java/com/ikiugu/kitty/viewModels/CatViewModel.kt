package com.ikiugu.kitty.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikiugu.kitty.models.SimpleCat
import com.ikiugu.kitty.models.favorites.SaveFavoriteRequestBody
import com.ikiugu.kitty.repositories.CatsRepository
import com.ikiugu.kitty.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@HiltViewModel
class CatViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    preferenceManager: PreferenceManager
) :
    ViewModel() {

    private lateinit var userImageType: String
    private val userImageTypeFlow = preferenceManager.imageTypeFlow

    private var _cat = MutableLiveData<SimpleCat>()
    val cat: LiveData<SimpleCat>
        get() = _cat

    init {
        Timber.i("Cat view model initialized")
        viewModelScope.launch {
            userImageTypeFlow.collect { imageType ->
                userImageType = imageType
                getRandomKitties()
            }
        }
    }

    fun getRandomKitties() {
        Timber.i("Getting random cats")
        viewModelScope.launch {
            val res = catsRepository.getRandomCat(userImageType)
            Timber.i(res[0].url)
            _cat.value = res[0]
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
        Timber.i("Searching all images by category")
        viewModelScope.launch {
            val res = catsRepository.getImagesByCategory(categoryId)
            Timber.i(res[0].url)
        }
    }

    fun saveFavoriteImage() {
        Timber.i("Saving favorite images")
        viewModelScope.launch {
            val requestBody = SaveFavoriteRequestBody("d19", "ikiugu-123456789")
            val res = catsRepository.saveFavoriteImage(requestBody)
            Timber.i(res.message)
        }
    }

    fun getFavoriteImages() {
        Timber.i("Getting all favorite images")
        viewModelScope.launch {
            val res = catsRepository.getFavoriteImages()
            Timber.i(res.size.toString())
        }
    }
}