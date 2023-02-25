package com.lixoten.flightsearch.data

data class Flight(
    val id: Int = 0,
    val departureCode: String ="",
    val departureName: String="",
    val destinationCode: String="",
    val destinationName: String="",
    val isFavorite: Boolean=false
)
