package dev.dizzy1021.core.domain.repository

import androidx.paging.PagingData
import dev.dizzy1021.core.domain.model.Review
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface IReviewRepository {

    fun getReviews(page: Int, user: String): Flow<ResourceWrapper<List<Review>>>

    fun fetchReviewPlace(id: String): Flow<PagingData<Review>>

    fun addReview(id: String, String: List<InputStream?>, user: String, desc: String, rating: Int): Flow<ResourceWrapper<String?>>
}