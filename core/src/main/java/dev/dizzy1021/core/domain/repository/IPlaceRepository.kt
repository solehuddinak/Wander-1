package dev.dizzy1021.core.domain.repository

import androidx.paging.PagingData
import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface IPlaceRepository {

    fun fetchHome(user: String): Flow<PagingData<Place>>

    fun getWishlist(page: Int, user: String): Flow<ResourceWrapper<List<Place>>>

    fun searchPlaces(user: String, q: String?, image: InputStream?): Flow<PagingData<Place>>

    fun fetchPlace(id: String, user: String): Flow<ResourceWrapper<Place>>

    fun addWishlist(id: Int, user: String, place: Place)
}