package dev.dizzy1021.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.dizzy1021.core.utils.PLACE_TABLE

@Entity(tableName = PLACE_TABLE)
data class PlaceEntity(
    @PrimaryKey
    @NonNull
    val id: String,
    val name: String,
    val desc: String?,
    val rating: Double,
    val location: String?,
    val longitude: String?,
    val latitude: String?,
    val date: String?,
    val poster: String?,
    val gallery: String?,
    var isFavorite: Boolean = false,
)