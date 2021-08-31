package com.ikiugu.kitty.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikiugu.kitty.models.CatBreed
import com.ikiugu.kitty.models.Category
import com.ikiugu.kitty.models.CategoryResult
import com.ikiugu.kitty.models.SimpleCat
import com.ikiugu.kitty.models.favorites.FavoriteItem
import com.ikiugu.kitty.models.favorites.SaveFavoriteRequestBody
import com.ikiugu.kitty.repositories.CatsRepository
import com.ikiugu.kitty.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
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

    private var _categories = MutableLiveData<ArrayList<Category>>()
    val categories: LiveData<ArrayList<Category>>
        get() = _categories

    private var _categoriesSearchResult = MutableLiveData<ArrayList<CategoryResult>>()
    val categorySearchResult: LiveData<ArrayList<CategoryResult>>
        get() = _categoriesSearchResult

    private var _favoriteImages = MutableLiveData<ArrayList<FavoriteItem>>()
    val favoriteImages: LiveData<ArrayList<FavoriteItem>>
        get() = _favoriteImages

    private var _catBreeds = MutableLiveData<ArrayList<CatBreed>>()
    val catBreeds: LiveData<ArrayList<CatBreed>>
        get() = _catBreeds

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private var _userProfileName = MutableLiveData<String>()
    val userProfileName: LiveData<String>
        get() = _userProfileName

    fun setLoading(loading: Boolean) {
        _loading.value = loading
    }

    private val catsEventChannel = Channel<CatsEvent>()
    val catsEvent = catsEventChannel.receiveAsFlow()

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
            Timber.i("Fetched ${res.size} cat breeds")
            _catBreeds.value = res
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
            Timber.i("Fetched ${res.size} categories")
            _categories.value = res
        }
    }

    private fun displaySearchResultsByCategory(images: Array<CategoryResult>) =
        viewModelScope.launch {
            catsEventChannel.send(CatsEvent.NavigateToDisplayCatsScreen(images))
        }

    private fun favoriteImageAdded() =
        viewModelScope.launch {
            catsEventChannel.send(CatsEvent.FavoriteImageAdded)
        }

    fun getImagesByCategories(categoryId: String) {
        Timber.i("Searching all images by category")

        viewModelScope.launch {
            val res = catsRepository.getImagesByCategory(categoryId, "50", userImageType)
            Timber.i("Fetched ${res.size} images using categories")
            _categoriesSearchResult.value = res

            displaySearchResultsByCategory(res.toTypedArray())
        }
    }

    fun saveFavoriteImage(id: String) {
        Timber.i("Saving favorite images")
        viewModelScope.launch {
            //ikiugu-123456789 test id
            val requestBody = SaveFavoriteRequestBody(id, userProfileName.value.toString())
            val res = catsRepository.saveFavoriteImage(requestBody)
            Timber.i(res.message)

            favoriteImageAdded()
        }
    }

    fun getFavoriteImages() {
        Timber.i("Getting all favorite images")
        viewModelScope.launch {
            val res = catsRepository.getFavoriteImages(userProfileName.value.toString())
            Timber.i("Fetched ${res.size} favorite images")
            _favoriteImages.value = res
        }
    }


    sealed class CatsEvent {
        data class NavigateToDisplayCatsScreen(val images: Array<CategoryResult>) : CatsEvent() {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as NavigateToDisplayCatsScreen

                if (!images.contentEquals(other.images)) return false

                return true
            }

            override fun hashCode(): Int {
                return images.contentHashCode()
            }
        }

        object FavoriteImageAdded : CatsEvent()
    }
}