package com.mohamad.kmmplaces.data

data class Poi(
    val id: String,
    val name: String,
    val location: Location,
    val distance: Long,
    val address: Address
)

data class Location(
    val lat: Double,
    val long: Double
)

data class Address(
    val address: String,
    val country: String,
    val formattedAddress: String,
    val locality: String,
    val postcode: String,
    val region: String
)