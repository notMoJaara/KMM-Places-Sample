package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlacesListDTO(
    @SerialName("results")
    val results: List<ResultDTO>
)