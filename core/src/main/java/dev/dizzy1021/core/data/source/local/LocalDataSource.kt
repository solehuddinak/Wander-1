package dev.dizzy1021.core.data.source.local

import dev.dizzy1021.core.data.source.local.dao.PlaceDAO
import dev.dizzy1021.core.data.source.local.dao.ReviewDAO
import dev.dizzy1021.core.data.source.local.entity.PlaceEntity
import dev.dizzy1021.core.data.source.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val placeDAO: PlaceDAO,
    private val reviewDAO: ReviewDAO
    )
{
    fun getPlaces(): Flow<List<PlaceEntity>> = placeDAO.selectAllPlaces()

    fun getFavoritePlaces(): Flow<List<PlaceEntity>> = placeDAO.selectFavoritePlaces()

    fun findPlaceById(id: Int): Flow<PlaceEntity> = placeDAO.selectPlaceById(id)

    suspend fun addBatchPlace(places: List<PlaceEntity>) = placeDAO.insertBatchPlaces(places)

    fun updatePlace(place: PlaceEntity) = placeDAO.updatePlace(place)

    fun removePlaces() = placeDAO.deletePlaces()

    fun getReviews(): Flow<List<ReviewEntity>> = reviewDAO.selectAllReviews()

    fun findReviewById(id: Int): Flow<ReviewEntity> = reviewDAO.selectReviewById(id)

    suspend fun addReview(review: ReviewEntity) = reviewDAO.insertReview(review)

}