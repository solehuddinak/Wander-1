package dev.dizzy1021.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.data.source.local.AppDatabase
import dev.dizzy1021.core.data.source.local.dao.PlaceDAO
import dev.dizzy1021.core.data.source.local.dao.ReviewDAO
import dev.dizzy1021.core.utils.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    fun providePlaceDao(db: AppDatabase): PlaceDAO = db.placeDAO()

    @Provides
    fun provideReviewDao(db: AppDatabase): ReviewDAO = db.reviewDAO()
}