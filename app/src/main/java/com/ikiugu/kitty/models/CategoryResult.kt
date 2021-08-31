package com.ikiugu.kitty.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Alfred Ikiugu on 30/08/2021
 */

@Parcelize
data class CategoryResult(
    val id: String,
    val url: String,
    val width: Long,
    val height: Long
) : Parcelable {

}