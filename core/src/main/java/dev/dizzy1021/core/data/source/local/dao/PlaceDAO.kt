package dev.dizzy1021.core.data.source.local.dao

import androidx.room.*
import dev.dizzy1021.core.data.source.local.entity.PlaceEntity
import dev.dizzy1021.core.utils.PLACE_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDAO {
    @Query("SELECT * FROM $PLACE_TABLE")
    fun selectAllPlaces(): Flow<List<PlaceEntity>>

    @Query("SELECT * FROM $PLACE_TABLE where isFavorite = 1")
    fun selectFavoritePlaces(): Flow<List<PlaceEntity>>

    @Query("SELECT * FROM $PLACE_TABLE where id = :id")
    fun selectPlaceById(id: Int): Flow<PlaceEntity>

    @Query("DELETE FROM $PLACE_TABLE where isFavorite = 0")
    fun deletePlaces()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBatchPlaces(places: List<PlaceEntity>)

    @Update
    fun updatePlace(place: PlaceEntity)
}