package com.mohamad.kmmplaces.data

import com.mohamad.kmmplaces.localStorage.PoiEntity
import com.mohamad.kmmplaces.network.foursquare.model.GeocodesDTO
import com.mohamad.kmmplaces.network.foursquare.model.LocationDTO
import com.mohamad.kmmplaces.network.foursquare.model.ResultDTO


class PoiMapper(
    private val locationMapper: LocationMapper,
    private val addressMapper: AddressMapper
) {
    fun fromEntity(poiEntity: PoiEntity): Poi = with(poiEntity) {
        Poi(
            id,
            name,
            locationMapper.fromString(location),
            distance,
            Address(address, country, formattedAddress, locality, postcode, region)
        )
    }

    fun toEntity(poi: Poi): PoiEntity = with(poi) {
        PoiEntity(
            id = id,
            name = name,
            location = locationMapper.toString(location),
            distance = distance,
            address = address.address,
            postcode = address.postcode,
            country = address.country,
            formattedAddress = address.formattedAddress,
            locality = address.locality,
            region = address.region
        )
    }


    fun fromDTO(resultDTO: ResultDTO): Poi = with(resultDTO) {
        Poi(fsqId, name, locationMapper.fromDTO(geocodes), distance, addressMapper.fromDTO(location))
    }
}

class AddressMapper {
    fun fromDTO(locationDTO: LocationDTO): Address = with(locationDTO) {
        Address(address, country, formattedAddress, locality, postcode, region)
    }
}

class LocationMapper {
    fun toString(location: Location): String = "${location.lat}$SEPRATOR${location.long}"

    // TODO: this fun can be improved to not crash in case of wrong usage
    fun fromString(locationStr: String): Location = locationStr.split(SEPRATOR).let {
        Location(it[0].toDouble(), it[1].toDouble())
    }

    fun fromDTO(geocodesDTO: GeocodesDTO): Location = with(geocodesDTO) {
        Location(lat = main.latitude, long = main.longitude)
    }

    private companion object {
        const val SEPRATOR = '@'
    }
}


