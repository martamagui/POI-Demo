package com.mmag.poiapp.di

import android.content.Context
import androidx.room.Room
import com.mmag.poiapp.data.db.POIDao
import com.mmag.poiapp.data.db.POIDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): POIDatabase {
        return Room.databaseBuilder(context, POIDatabase::class.java, "POIDatabase").build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: POIDatabase): POIDao {
        return database.poiDao()
    }
}
