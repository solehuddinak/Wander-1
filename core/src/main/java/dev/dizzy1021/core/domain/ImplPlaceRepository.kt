package dev.dizzy1021.core.domain

import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.domain.repository.IPlaceRepository
import dev.dizzy1021.core.domain.usecase.PlaceUseCase
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplPlaceRepository @Inject constructor(
    private val placeRepository: IPlaceRepository
) : PlaceUseCase {

    override fun fetchHome(page: Int, user: String): Flow<ResourceWrapper<List<Place>>> {
        TODO("Not yet implemented")
    }

    override fun getWishlist(page: Int, user: String): Flow<ResourceWrapper<List<Place>>> {
        TODO("Not yet implemented")
    }

    override fun searchPlaces(
        page: Int,
        user: String,
        q: String?,
        image: Any?
    ): Flow<ResourceWrapper<List<Place>>> {
        TODO("Not yet implemented")
    }

    override fun fetchPlace(id: Int, user: String): Flow<ResourceWrapper<Place>> {
        TODO("Not yet implemented")
    }

    override fun addWishlist(id: Int, user: String, place: Place) {
        TODO("Not yet implemented")
    }
}