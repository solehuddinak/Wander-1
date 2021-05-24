package dev.dizzy1021.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.dizzy1021.core.utils.REVIEW_TABLE

@Entity(tableName = REVIEW_TABLE)
data class ReviewEntity(
    @PrimaryKey
    @NonNull
    val id: String,
    val username: String,
    val rating: Double,
    val desc: String,
    val date: String,
    val placeId: String,
)