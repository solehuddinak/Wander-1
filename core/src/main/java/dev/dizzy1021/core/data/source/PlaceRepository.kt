package dev.dizzy1021.core.data.source

import dev.dizzy1021.core.data.source.local.LocalDataSource
import dev.dizzy1021.core.data.source.remote.RemoteDataSource
import dev.dizzy1021.core.domain.model.Place
import dev.dizzy1021.core.domain.repository.IPlaceRepository
import dev.dizzy1021.core.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaceRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPlaceRepository {

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