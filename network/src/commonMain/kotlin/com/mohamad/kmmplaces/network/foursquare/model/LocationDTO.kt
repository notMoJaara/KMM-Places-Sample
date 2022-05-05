package com.mohamad.kmmplaces.network.foursquare.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    @SerialName("address")
    val address: String?,
    @SerialName("address_extended")
    val addressExtended: String?,
    @SerialName("census_block")
    val censusBlock: String?,
    @SerialName("country")
    val country: String,
    @SerialName("cross_street")
    val crossStreet: String?,
    @SerialName("dma")
    val dma: String?,
    @SerialName("formatted_address")
    val formattedAddress: String,
    @SerialName("locality")
    val locality: String,
    @SerialName("neighborhood")
    val neighborhood: List<String>?,
    @SerialName("postcode")
    val postcode: String,
    @SerialName("region")
    val region: String
)