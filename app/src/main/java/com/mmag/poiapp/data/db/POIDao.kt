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
    fun insertAllPOI(poiList: List<POIEntity>): List<Long>

    @Query("SELECT * FROM poi")
    fun getAllPOIs(): Flow<List<POIEntity>>

    @Query("SELECT * FROM poi WHERE title  LIKE '%' || :searchText || '%'")
    fun getPOIsBySearchText(searchText:String): Flow<List<POIEntity>>

    @Query("SELECT * FROM poi WHERE id=:poiID")
    fun getPOIById(poiID: Int): Flow<POIEntity>
}