package dev.dizzy1021.core.data.source.remote.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestAddReview (
    val user: String,
    val desc: String,
    val rating: Double,
    val date: String,
): Parcelable