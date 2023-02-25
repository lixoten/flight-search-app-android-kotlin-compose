package com.lixoten.flightsearch

import android.app.Application
import com.lixoten.flightsearch.di.AppContainer
import com.lixoten.flightsearch.di.AppDataContainer

class FlightApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

// notes: code was done differently then the way we leannred in inventory codelab
//class BusScheduleApplication: Application() {
//    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
//}
