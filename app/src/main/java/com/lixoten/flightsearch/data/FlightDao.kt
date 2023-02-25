package com.lixoten.flightsearch.data

import androidx.room.*
import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query(
        """
        Select * from favorite
        ORDER BY id ASC
        """
    )
    //fun getAllAirports(): Flow<List<Airport>>
    suspend fun getAllFavorites(): List<Favorite>
    @Query(
        """
        Select * from favorite
        ORDER BY id ASC
        """
    )
    fun getAllFavoritesFlow(): Flow<List<Favorite>>

    @Query(
        """
        SELECT * FROM favorite
        WHERE departure_code = :departureCode
          AND destination_code = :destinationCode
        """
    )
    suspend fun getSingleFavorite(departureCode: String, destinationCode: String): Favorite



    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteFlight(flight: Favorite)
    @Delete
    suspend fun deleteFavoriteFlight(flight: Favorite)



//    Select * from airport
//    WHERE name LIKE :name
//    ORDER BY name ASC
    @Query(
        """
        Select * from airport 
        ORDER BY id ASC 
        """
    )
    fun getAllAirportsFlow(): Flow<List<Airport>>
    @Query(
        """
        Select * from airport 
        ORDER BY id ASC 
        """
    )
    //fun getAllAirports(): Flow<List<Airport>>
    suspend fun getAllAirports(): List<Airport>



    @Query(
        """
    Select * from airport
    WHERE iata_code = :query OR name LIKE '%' || :query || '%'        
    ORDER BY name ASC
        """
    )
    fun getAllAirportsFlow(query: String): Flow<List<Airport>>



    //"SELECT * from airport WHERE iata_code= :query OR name LIKE '%' || :query || '%' ")

    //WHERE name LIKE 'Fr%'
    @Query(
        """
    Select * from airport
    WHERE iata_code = :query OR name LIKE '%' || :query || '%'        
    ORDER BY name ASC
        """
    )
    //fun getAllAirports(query: String): Flow<List<Airport>>
    suspend fun getAllAirports(query: String): List<Airport>

    @Query(
        """
    Select * from airport
    WHERE iata_code = :code
        """
    )
    //fun getAirportByCode(code: String): Flow<Airport>
    suspend fun getAirportByCode(code: String): Airport
    @Query(
        """
    Select * from airport
    WHERE iata_code = :code
        """
    )
    fun getAirportByCodeFlow(code: String): Flow<Airport>





    @Query(
        """
    Select * from airport
    WHERE id = :id
        """
    )
    //fun getAirportById(id: Int): Flow<Airport>
    suspend fun getAirportById(id: Int): Airport


    //
    //@Query("SELECT * FROM airport ORDER BY passengers DESC")
    //suspend fun getAirports(): List<Airport>
    //

//    @Query(
//        """
//    Select * from airport
//    WHERE iata_code = :ito
//    ORDER BY name ASC
//        """
//    )
//    fun getAllFlights(ito: String): Flow<List<Airport>>



    //@Query("SELECT * FROM favorite")
    //fun getFavoriteFlightsStream(): Flow<List<FavoriteFlight>>

    //@Query("SELECT * FROM favorite WHERE id = :id")
    //suspend fun getFavoriteFlight(id: Int): FavoriteFlight?



}