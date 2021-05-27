package dev.dizzy1021.core.data.source.remote.service

import dev.dizzy1021.core.BuildConfig
import dev.dizzy1021.core.data.source.remote.request.RequestAddReview
import dev.dizzy1021.core.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Services {

    @GET("home")
    suspend fun callHome(
        @Query("key") key: String = BuildConfig.API_KEY_SERVER,
        @Query("page") page: Int,
        @Query("user") user: String,
    ): Response<ResponseWrapper<List<ResponseHome>>>

    @GET("wishlist")
    suspend fun callWishlist(
        @Query("key") key: String = BuildConfig.API_KEY_SERVER,
        @Query("page") page: Int,
        @Query("user") user: String,
    ): Response<ResponseWrapper<ResponseWishlist>>

    @GET("review")
    suspend fun callReview(
        @Query("key") key: String = BuildConfig.API_KEY_SERVER,
        @Query("page") page: Int,
        @Query("user") user: String,
    ): Response<ResponseWrapper<ResponseReviews>>

    @Multipart
    @POST("search")
    suspend fun searchPlaces(
        @Query("key") key: String = BuildConfig.API_KEY_SERVER,
        @Query("q") q: String?,
        @Query("page") page: Int,
        @Query("user") user: String,
        @Part image: MultipartBody.Part?,
    ): Response<ResponseWrapper<List<ResponseHome>>>

    @POST("search")
    suspend fun searchPlaces(
        @Query("key") key: String = BuildConfig.API_KEY_SERVER,
        @Query("q") q: String?,
        @Query("page") page: Int,
        @Query("user") user: String,
    ): Response<ResponseWrapper<List<ResponseHome>>>

    @GET("place/{id}")
    suspend fun callPlaceById(
        @Path("id") id: Int,
        @Query("user") user: String,
        @Query("key") key: String = BuildConfig.API_KEY_SERVER
    ): Response<ResponseWrapper<ResponseHome>>

    @GET("place/{id}/review")
    suspend fun callReviewPlace(
        @Path("id") id: Int,
        @Query("key") key: String = BuildConfig.API_KEY_SERVER,
        @Query("page") page: Int,
    ): Response<ResponseWrapper<ResponseReviewsPlace>>

    @POST("place/{id}/review")
    suspend fun createReview(
        @Path("id") id: Int,
        @Body formRequest : RequestAddReview,
        @Query("key") key: String = BuildConfig.API_KEY_SERVER
    ): Response<ResponseWrapper<Any?>>

}