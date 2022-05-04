package com.mohamad.kmmplaces.di

import com.mohamad.kmmplaces.data.AddressMapper
import com.mohamad.kmmplaces.data.LocationMapper
import com.mohamad.kmmplaces.data.PoiMapper

object MapperProvider {
    val addressMapper: AddressMapper get() = AddressMapper()
    val locationMapper: LocationMapper get() = LocationMapper()
    val  poiMapper: PoiMapper get() = PoiMapper(locationMapper, addressMapper)
}