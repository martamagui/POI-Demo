package com.mmag.poiapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "poi")
data class POIEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name ="geocoordinates")
    val geocoordinates: String,
    @ColumnInfo(name ="image")
    val image: String,
    @ColumnInfo(name ="title")
    val title: String
)