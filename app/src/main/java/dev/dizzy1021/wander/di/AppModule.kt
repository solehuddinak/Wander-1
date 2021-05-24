package dev.dizzy1021.wander.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.domain.ImplPlaceRepository
import dev.dizzy1021.core.domain.ImplReviewRepository
import dev.dizzy1021.core.domain.usecase.PlaceUseCase
import dev.dizzy1021.core.domain.usecase.ReviewUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providePlaceUseCase(impl: ImplPlaceRepository): PlaceUseCase

    @Binds
    abstract fun provideReviewUseCase(impl: ImplReviewRepository): ReviewUseCase

}