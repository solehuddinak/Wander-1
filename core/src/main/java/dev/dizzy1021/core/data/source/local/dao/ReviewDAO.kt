package dev.dizzy1021.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dizzy1021.core.data.source.local.entity.ReviewEntity
import dev.dizzy1021.core.utils.REVIEW_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDAO {
    @Query("SELECT * FROM $REVIEW_TABLE")
    fun selectAllReviews(): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM $REVIEW_TABLE where id = :id")
    fun selectReviewById(id: Int): Flow<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReview(places: ReviewEntity)
}