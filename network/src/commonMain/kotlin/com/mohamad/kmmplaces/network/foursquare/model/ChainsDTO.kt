package com.mohamad.kmmplaces.network.foursquare.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChainsDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
)