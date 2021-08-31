package com.ikiugu.kitty.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikiugu.kitty.models.SimpleCat
import com.ikiugu.kitty.models.favorites.SaveFavoriteRequestBody
import com.ikiugu.kitty.repositories.CatsRepository
import com.ikiugu.kitty.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    preferenceManager: PreferenceManager,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    private lateinit var userImageType: String
    private val userImageTypeFlow = preferenceManager.imageTypeFlow
    private val usernameSelected = preferenceManager.usernameFlow

    private var _cat = MutableLiveData<SimpleCat>()
    val cat: LiveData<SimpleCat>
        get() = _cat

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private var _userProfileName = MutableLiveData<String>()
    val userProfileName: LiveData<String>
        get() = _userProfileName

    fun setLoading(loading: Boolean) {
        _loading.value = loading
    }

    init {
        Timber.i("Cat view model initialized")
        viewModelScope.launch {
            userImageTypeFlow.collect { imageType ->
                Timber.i("Image type selected is $imageType")
                userImageType = imageType
                getRandomKitties()
            }
        }

        viewModelScope.launch {
            usernameSelected.collect { username ->
                Timber.i("Username selected is $username")
                _userProfileName.value = username
            }
        }
    }

    fun getRandomKitties() {
        Timber.i("Getting random cats")
        _loading.value = true
        viewModelScope.launch {
            val res = catsRepository.getRandomCat(userImageType)
            Timber.i(res[0].url)
            _cat.value = res[0]
            _loading.value = false
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