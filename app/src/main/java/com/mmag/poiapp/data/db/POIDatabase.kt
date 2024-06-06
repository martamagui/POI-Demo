package com.mmag.poiapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmag.poiapp.data.db.model.POIEntity

@Database(
    entities = [POIEntity::class],
    version = 1,
    exportSchema = false
)
abstract class POIDatabase : RoomDatabase() {
    abstract fun poiDao(): POIDao
}