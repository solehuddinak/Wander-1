package dev.dizzy1021.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseHome(

	@field:SerializedName("is_favorite")
	val isFavorite: Boolean,

	@field:SerializedName("latt")
	val latitude: String,

	@field:SerializedName("open_link")
	val openLink: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("review_link")
	val reviewLink: String,

	@field:SerializedName("long")
	val longitude: String,

	@field:SerializedName("image_path")
	val imagePath: List<ImagePathItem>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("top_reviews")
	val responseReviews: List<ResponseReviews>,

	@field:SerializedName("create_review_link")
	val createReviewLink: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class ImagePathItem(

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("content_description")
	val contentDescription: String?
) : Parcelable
