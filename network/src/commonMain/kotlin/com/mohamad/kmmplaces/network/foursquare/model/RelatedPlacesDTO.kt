package com.mohamad.kmmplaces.network.foursquare.model


import com.mohamad.kmmplaces.network.foursquare.model.ChildrenDTO
import com.mohamad.kmmplaces.network.foursquare.model.ParentDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RelatedPlacesDTO(
    @SerialName("children")
    val children: List<ChildrenDTO>?,
    @SerialName("parent")
    val parent: ParentDTO?
)