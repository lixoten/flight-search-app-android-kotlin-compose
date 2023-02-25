package com.lixoten.flightsearch.data

import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

class OfflineFlightRepository(private val airportDao: FlightDao) : FlightRepository {
    override fun getAllAirportsFlow(): Flow<List<Airport>> {
        return airportDao.getAllAirportsFlow()
    }
    override fun getAllAirportsFlow(query: String): Flow<List<Airport>> {
        return airportDao.getAllAirportsFlow(query)
    }
    override fun getAirportByCodeFlow(code: String): Flow<Airport> {
        return airportDao.getAirportByCodeFlow(code)
    }
    override suspend fun getAllAirports(): List<Airport> {
        return airportDao.getAllAirports()
    }
    override suspend fun getAllAirports(query: String): List<Airport> {
        return airportDao.getAllAirports(query)
    }
    override suspend fun getAirportByCode(code: String): Airport {
        return airportDao.getAirportByCode(code)
    }

    override suspend fun getAirportById(id: Int): Airport {
        return airportDao.getAirportById(id)
    }

    override fun getAllFavoritesFlightsFlow(): Flow<List<Favorite>> {
        return airportDao.getAllFavoritesFlow()
    }


    override suspend fun getAllFavoritesFlights(): List<Favorite> {
        return airportDao.getAllFavorites()
    }
    override suspend fun deleteFavoriteFlight(flight: Favorite) {
        return airportDao.deleteFavoriteFlight(flight)
    }


    override suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite {
        return airportDao.getSingleFavorite(departureCode, destinationCode)
    }

    override suspend fun insertFavoriteFlight(flight: Favorite) = airportDao.insertFavoriteFlight(flight)
}