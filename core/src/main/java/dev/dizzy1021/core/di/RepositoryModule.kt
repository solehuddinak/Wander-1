package dev.dizzy1021.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.data.source.PlaceRepository
import dev.dizzy1021.core.data.source.ReviewRepository
import dev.dizzy1021.core.domain.repository.IPlaceRepository
import dev.dizzy1021.core.domain.repository.IReviewRepository

@Module(includes = [ServiceModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePlaceRepository(repository: PlaceRepository): IPlaceRepository

    @Binds
    abstract fun provideReviewRepository(repository: ReviewRepository): IReviewRepository
}