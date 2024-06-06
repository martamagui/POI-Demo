package com.mmag.poiapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mmag.poiapp.data.db.model.POIEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface POIDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPOI(poiList: List<POIEntity>)

    @Query("SELECT * FROM poi")
    fun getAllPOIs(): Flow<List<POIEntity>>

    @Query("SELECT * FROM poi WHERE id=:poiID")
    fun getPOIById(poiID: String): Flow<List<POIEntity>>
}