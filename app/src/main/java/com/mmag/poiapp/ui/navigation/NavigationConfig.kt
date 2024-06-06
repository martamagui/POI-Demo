package com.mmag.poiapp.ui.navigation



object NavigationConfig {
   const val HOME_ROUTE = "home"
   const val DETAIL_ROUTE = "detail/${Args.DETAIL_ARG}"

    object Builder {
        fun poiDetail(id: String) =  DETAIL_ROUTE.replace("{${Args.DETAIL_ARG}}", id)
    }

    object Args{
        const val DETAIL_ARG = "poi_id"
    }
}