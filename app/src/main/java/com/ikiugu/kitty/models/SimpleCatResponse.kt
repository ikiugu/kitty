package com.ikiugu.kitty.models

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

typealias SimpleCatResponse = ArrayList<SimpleCat>

data class SimpleCat (
    val breeds: List<Any?>,
    val id: String,
    val url: String,
    val width: Long,
    val height: Long
)