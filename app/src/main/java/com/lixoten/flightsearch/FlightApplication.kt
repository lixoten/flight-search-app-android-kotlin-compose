package com.lixoten.flightsearch

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.lixoten.flightsearch.data.UserPreferencesRepository
import com.lixoten.flightsearch.di.AppContainer
import com.lixoten.flightsearch.di.AppDataContainer


private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)

class FlightApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}

// notes: code was done differently then the way we leannred in inventory codelab
//class BusScheduleApplication: Application() {
//    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
//}
