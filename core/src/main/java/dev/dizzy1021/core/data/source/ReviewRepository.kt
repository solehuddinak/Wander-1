package dev.dizzy1021.core.data.source

import dev.dizzy1021.core.data.source.local.LocalDataSource
import dev.dizzy1021.core.data.source.remote.RemoteDataSource
import dev.dizzy1021.core.domain.model.Review
import dev.dizzy1021.core.domain.repository.IReviewRepository
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IReviewRepository {

    override fun getReviews(page: Int, user: String): Flow<ResourceWrapper<List<Review>>> {
        TODO("Not yet implemented")
    }

    override fun fetchReviewPlace(id: Int, page: Int): Flow<ResourceWrapper<List<Review>>> {
        TODO("Not yet implemented")
    }

    override fun addReview(id: Int, request: Review) {
        TODO("Not yet implemented")
    }

}