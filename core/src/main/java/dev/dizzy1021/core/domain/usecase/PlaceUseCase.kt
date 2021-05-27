package dev.dizzy1021.core.domain.usecase

import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface PlaceUseCase {

    fun fetchHome(page: Int, user: String): Flow<ResourceWrapper<List<Place>>>

    fun getWishlist(page: Int, user: String): Flow<ResourceWrapper<List<Place>>>

    fun searchPlaces(page: Int, user: String, q: String?, image: InputStream?): Flow<ResourceWrapper<List<Place>>>

    fun fetchPlace(id: Int, user: String): Flow<ResourceWrapper<Place>>

    fun addWishlist(id: Int, user: String, place: Place)
}