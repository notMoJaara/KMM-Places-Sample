package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedPlacesDTO(
    @SerialName("children")
    val children: List<ChildrenDTO>?,
    @SerialName("parent")
    val parent: ParentDTO?
)