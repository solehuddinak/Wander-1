package dev.dizzy1021.core.domain

import dev.dizzy1021.core.domain.model.Review
import dev.dizzy1021.core.domain.repository.IReviewRepository
import dev.dizzy1021.core.domain.usecase.ReviewUseCase
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplReviewRepository @Inject constructor(
    private val reviewRepository: IReviewRepository
) : ReviewUseCase{
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