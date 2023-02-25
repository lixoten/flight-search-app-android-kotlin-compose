package com.lixoten.flightsearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class FlightDatabase: RoomDatabase() {

    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var Instance: FlightDatabase? = null

        fun getDatabase(context: Context): FlightDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FlightDatabase::class.java,
                    "flight_database"
                )
                    .createFromAsset("database/flight_search.db")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}