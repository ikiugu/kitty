package com.ikiugu.kitty.models

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

typealias SearchByCategory = ArrayList<CategoryResult>

data class CategoryResult (
    val breeds: List<CatBreed>,
    val categories: List<Category>,
    val id: String,
    val url: String,
    val width: Long,
    val height: Long
)
