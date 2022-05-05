package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeocodesDTO(
    @SerialName("main")
    val main: MainDTO
)