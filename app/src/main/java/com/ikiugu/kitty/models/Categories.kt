package com.ikiugu.kitty.models

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

typealias Categories = ArrayList<Category>

data class Category (
    val id: Long,
    val name: String
)
