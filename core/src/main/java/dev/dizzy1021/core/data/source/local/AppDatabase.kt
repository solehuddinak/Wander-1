package dev.dizzy1021.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dizzy1021.core.data.source.local.dao.PlaceDAO
import dev.dizzy1021.core.data.source.local.dao.ReviewDAO
import dev.dizzy1021.core.data.source.local.entity.PlaceEntity
import dev.dizzy1021.core.data.source.local.entity.ReviewEntity

@Database(
    version = 2,
    entities = [PlaceEntity::class, ReviewEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDAO(): PlaceDAO
    abstract fun reviewDAO(): ReviewDAO
}