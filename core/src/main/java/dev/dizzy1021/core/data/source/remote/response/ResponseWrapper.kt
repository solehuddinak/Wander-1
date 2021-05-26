package dev.dizzy1021.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseWrapper<out Q>(

    @field:SerializedName("status")
    val status: String?,

    @field:SerializedName("message")
    val message: String?,

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: Q?,

    @field:SerializedName("links")
    val links: ResponseLinks,
)

@Parcelize
data class ResponseLinks(

    @field:SerializedName("self")
    val self: String?,

    @field:SerializedName("next")
    val next: String?,

    @field:SerializedName("prev")
    val prev: String?,

): Parcelable