package dev.dizzy1021.core.domain

import androidx.paging.PagingData
import dev.dizzy1021.core.domain.model.Review
import dev.dizzy1021.core.domain.repository.IReviewRepository
import dev.dizzy1021.core.domain.usecase.ReviewUseCase
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import java.io.InputStream
import javax.inject.Inject

class ImplReviewRepository @Inject constructor(
    private val repository: IReviewRepository
) : ReviewUseCase{
    override fun getReviews(page: Int, user: String): Flow<ResourceWrapper<List<Review>>> {
        TODO("Not yet implemented")
    }

    override fun fetchReviewPlace(id: String): Flow<PagingData<Review>> = repository.fetchReviewPlace(id)

    override fun addReview(id: String, images: List<InputStream?>, user: String, desc: String, rating: Int) =
        repository.addReview(
        id,
        images,
        user,
        desc,
        rating
    )
}