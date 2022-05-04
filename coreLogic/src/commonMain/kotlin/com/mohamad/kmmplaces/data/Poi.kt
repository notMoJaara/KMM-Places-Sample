package com.mohamad.kmmplaces.data

data class Poi(
    val id: String,
    val name: String,
    val location: Location,
    val distance: Int,
    val address: Address
)

data class Location(
    val lat: Float,
    val long: Float
)

data class Address(
    val address: String,
    val country: String,
    val formattedAddress: String,
    val locality: String,
    val postcode: String,
    val region: String
)