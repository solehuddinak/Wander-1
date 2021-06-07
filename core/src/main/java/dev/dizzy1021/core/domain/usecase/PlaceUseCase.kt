package dev.dizzy1021.core.domain.usecase

import androidx.paging.PagingData
import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface PlaceUseCase {

    fun fetchHome(user: String): Flow<PagingData<Place>>

    fun getWishlist(user: String): Flow<PagingData<Place>>

    fun searchPlaces(user: String, q: String?, image: InputStream?): Flow<PagingData<Place>>

    fun fetchPlace(id: String, user: String): Flow<ResourceWrapper<Place>>

    fun addWishlist(id: Int, user: String, place: Place)
}