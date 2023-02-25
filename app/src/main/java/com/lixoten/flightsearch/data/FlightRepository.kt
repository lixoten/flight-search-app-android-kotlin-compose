package com.lixoten.flightsearch.data

import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow


interface FlightRepository {

    fun getAllAirportsFlow(): Flow<List<Airport>>
    fun getAllAirportsFlow(query: String): Flow<List<Airport>>
    fun getAirportByCodeFlow(code: String): Flow<Airport>

    suspend fun getAllAirports(): List<Airport>
    suspend fun getAllAirports(query: String): List<Airport>
    suspend fun getAirportByCode(code: String): Airport

    suspend fun getAirportById(id: Int): Airport

    fun getAllFavoritesFlightsFlow():  Flow<List<Favorite>>
    suspend fun getAllFavoritesFlights(): List<Favorite>
    suspend fun insertFavoriteFlight(flight: Favorite)
    suspend fun deleteFavoriteFlight(flight: Favorite)

    suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite
}