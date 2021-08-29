package com.ikiugu.kitty.models.favorites

import com.google.gson.annotations.SerializedName

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

data class SaveFavoriteRequestBody (
    @SerializedName("image_id")
    val imageID: String,
    @SerializedName("sub_id")
    val subID: String
)

data class SaveFavoriteResponse (
    val id: Long,
    val message: String
)