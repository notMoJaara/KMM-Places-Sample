package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IconDTO(
    @SerialName("prefix")
    val prefix: String,
    @SerialName("suffix")
    val suffix: String
)