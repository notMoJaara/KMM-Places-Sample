package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentDTO(
    @SerialName("fsq_id")
    val fsqId: String,
    @SerialName("name")
    val name: String
)