package com.mmag.poiapp.data.model

import com.mmag.poiapp.data.db.model.POIEntity
import com.mmag.poiapp.data.network.model.POIDetailResponse

data class POIDetail(
    val id: Int,
    val geocoordinates: String,
    val image: String,
    val title: String,
)

fun POIDetail.toEntity():POIEntity = POIEntity(id, geocoordinates, image, title)
fun POIEntity.toPoiDetail() : POIDetail = POIDetail(id, geocoordinates, image, title)
fun POIDetailResponse.toPoiDetail() : POIDetail = POIDetail(id, geocoordinates, image, title)