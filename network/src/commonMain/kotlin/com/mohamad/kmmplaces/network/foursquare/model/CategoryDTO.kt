package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    @SerialName("icon")
    val icon: IconDTO,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)