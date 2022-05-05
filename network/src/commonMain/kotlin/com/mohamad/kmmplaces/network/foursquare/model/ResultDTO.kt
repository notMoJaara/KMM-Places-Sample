package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDTO(
    @SerialName("fsq_id")
    val fsqId: String,
    @SerialName("categories")
    val categories: List<CategoryDTO>,
    @SerialName("chains")
    val chains: List<ChainsDTO>,
    @SerialName("distance")
    val distance: Long,
    @SerialName("geocodes")
    val geocodes: GeocodesDTO,
    @SerialName("location")
    val location: LocationDTO,
    @SerialName("name")
    val name: String,
    @SerialName("related_places")
    val relatedPlaces: RelatedPlacesDTO,
    @SerialName("timezone")
    val timezone: String
)