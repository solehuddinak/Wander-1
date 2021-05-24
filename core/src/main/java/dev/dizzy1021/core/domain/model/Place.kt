package dev.dizzy1021.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    val id: String,
    val name: String,
    val desc: String?,
    val rating: Double,
    val location: String?,
    val longitude: String?,
    val latitude: String?,
    val date: String?,
    val poster: String?,
    val gallery: String?,
    var isFavorite: Boolean = false,
): Parcelable