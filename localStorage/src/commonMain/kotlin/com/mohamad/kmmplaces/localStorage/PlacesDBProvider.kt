package com.mohamad.kmmplaces.localStorage

import com.mohamad.kmmplaces.localStorage.dao.PoiDAO

expect class PlacesDBProvider {
    val poiDAO: PoiDAO
}
