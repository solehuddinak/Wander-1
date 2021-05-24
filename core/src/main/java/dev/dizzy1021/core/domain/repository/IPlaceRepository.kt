package dev.dizzy1021.core.domain.repository

import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow

interface IPlaceRepository {

    fun fetchHome(page: Int, user: String): Flow<ResourceWrapper<List<Place>>>

    fun getWishlist(page: Int, user: String): Flow<ResourceWrapper<List<Place>>>

    fun searchPlaces(page: Int, user: String, q: String?, image: Any?): Flow<ResourceWrapper<List<Place>>>

    fun fetchPlace(id: Int, user: String): Flow<ResourceWrapper<Place>>

    fun addWishlist(id: Int, user: String, place: Place)
}