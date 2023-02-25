package com.lixoten.flightsearch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lixoten.flightsearch.data.Flight

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("departure_code")
    val departureCode: String,
    @ColumnInfo(name = "destination_code")
    val destinationCode: String,
)
//{
//    fun toFlight(departureName: String, destinationName: String): Flight {
//        return Flight(
//            id = id,
//            departureCode = departureCode,
//            departureName = departureName,
//            destinationCode = destinationCode,
//            destinationName = destinationName,
//            isFavorite = false
//        )
//    }
//}

