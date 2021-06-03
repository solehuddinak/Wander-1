package dev.dizzy1021.core.domain.usecase

import androidx.paging.PagingData
import dev.dizzy1021.core.domain.model.Review
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface ReviewUseCase {

    fun getReviews(page: Int, user: String): Flow<ResourceWrapper<List<Review>>>

    fun fetchReviewPlace(id: String): Flow<PagingData<Review>>

    fun addReview(id: String, images: List<InputStream?>, user: String, desc: String, rating: Int): Flow<ResourceWrapper<String?>>

}