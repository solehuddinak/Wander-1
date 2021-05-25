package dev.dizzy1021.core.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.utils.PREF_NAME
import dev.dizzy1021.core.utils.SharedPreferenceUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext ctx: Context): SharedPreferences =
        ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPreferenceUtil(preferences: SharedPreferences): SharedPreferenceUtil =
        SharedPreferenceUtil(preferences)

}