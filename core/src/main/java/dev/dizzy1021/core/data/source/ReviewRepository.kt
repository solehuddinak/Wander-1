package dev.dizzy1021.core.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.dizzy1021.core.data.source.local.LocalDataSource
import dev.dizzy1021.core.data.source.remote.RemoteDataSource
import dev.dizzy1021.core.domain.model.Review
import dev.dizzy1021.core.domain.repository.IReviewRepository
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
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

    override fun fetchReviewPlace(id: Int): Flow<PagingData<Review>> =
        Pager(
            PagingConfig(pageSize = 14, enablePlaceholders = false)
        ) {
            remoteDataSource.fetchReviewPlace(id)

        }.flow.flowOn(Dispatchers.IO)

    override fun addReview(id: Int, request: Review) {
        TODO("Not yet implemented")
    }

}