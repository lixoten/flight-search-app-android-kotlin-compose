package com.lixoten.flightsearch.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.lixoten.flightsearch.data.UserPreferencesKeys.SEARCH_VALUE
import kotlinx.coroutines.flow.*
import java.io.IOException

// Define your data class
data class UserPreferences(
    val searchValue: String = "",
)

// Define your preference keys
object UserPreferencesKeys {
    val SEARCH_VALUE = stringPreferencesKey("search_value")
}

// Define a DataStore class to store and retrieve your data
class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    // Update the user preferences
    suspend fun updateUserPreferences(
        searchValue: String,
    ) {
        dataStore.edit { preferences ->
            preferences[SEARCH_VALUE] = searchValue
        }
    }

    // Read the user preferences as a Flow
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                // Handle the exception
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                searchValue = preferences[SEARCH_VALUE] ?: "",
            )
        }
}