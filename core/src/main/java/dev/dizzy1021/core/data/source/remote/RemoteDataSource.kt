package dev.dizzy1021.core.data.source.remote

import dev.dizzy1021.core.data.source.remote.request.RequestAddReview
import dev.dizzy1021.core.data.source.remote.response.*
import dev.dizzy1021.core.data.source.remote.service.Services
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val services: Services) {

    suspend fun fetchHome(page: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<List<ResponseHome>>>> =
        flow {
            services.callHome(
                page = page,
                user = user
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun fetchWishlist(page: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponseWishlist>>> =
         flow {
            services.callWishlist(
                page = page,
                user = user
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun fetchReview(page: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponseReviews>>> =
        flow {
            services.callReview(
                page = page,
                user = user
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun findPlaces(page: Int, user: String, q: String?, image: MultipartBody.Part?): Flow<ResourceWrapper<ResponseWrapper<List<ResponseHome>>>> =
         flow {
            services.searchPlaces(
                page = page,
                user = user,
                q = q,
                image = image
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun findPlaces(page: Int, user: String, q: String?): Flow<ResourceWrapper<ResponseWrapper<List<ResponseHome>>>> =
        flow {
            services.searchPlaces(
                page = page,
                user = user,
                q = q,
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun findPlaceByID(id: Int, user: String): Flow<ResourceWrapper<ResponseWrapper<ResponseHome>>> =
         flow {
            services.callPlaceById(
                id = id,
                user = user,
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun fetchReviewPlace(id: Int, page: Int): Flow<ResourceWrapper<ResponseWrapper<ResponseReviewsPlace>>> =
         flow {
            services.callReviewPlace(
                id = id,
                page = page,
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

    suspend fun addReview(id: Int, request: RequestAddReview): Flow<ResourceWrapper<ResponseWrapper<Any?>>> =
         flow {
            services.createReview(
                id = id,
                formRequest = request
            ).let {
                if (it.isSuccessful) {
                    emit(ResourceWrapper.success(it.body()))
                } else {
                    emit(ResourceWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)

}
