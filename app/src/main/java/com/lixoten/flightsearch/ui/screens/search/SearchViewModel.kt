package com.lixoten.flightsearch.ui.screens.search

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lixoten.flightsearch.FlightApplication
import com.lixoten.flightsearch.data.FlightRepository
import com.lixoten.flightsearch.data.UserPreferencesRepository
import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    val flightRepository: FlightRepository,
    private val userPreferencesRepository: UserPreferencesRepository

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private var deletedRecord: Favorite? = null

    private var getAirportsJob: Job? = null
    // private var getFavoritesJob: Job? = null

    // Airports from the Airport Table
    private var airportList = mutableListOf<Airport>()// MutableState<List<Favorite>>(emptyList())
    // Favorites from the Favorite Table
    private var favoriteList = mutableListOf<Favorite>()// MutableState<List<Favorite>>(emptyList())

    // Tmp List used to
    // var flightList = mutableListOf<Flight>()// MutableState<List<Favorite>>(emptyList())

    init {
        viewModelScope.launch {
            userPreferencesRepository.userPreferencesFlow.collect {
//                _uiState.value = uiState.value.copy(
//                    searchQuery = it.searchValue,
//                )
                processSearchQueryFlow(it.searchValue)
            }
        }
    }


    fun onQueryChange(query: String) {
        //job?.cancel()
        //job = viewModelScope.launch {
        //    appRepository.saveSearchQuery(query)
        //}
        processSearchQueryFlow(query)
    }

    private fun processSearchQueryFlow(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

        if (query.isEmpty()) {
            viewModelScope.launch {
                airportList = flightRepository.getAllAirports().toMutableStateList()
                favoriteList= flightRepository.getAllFavoritesFlights().toMutableStateList()
                _uiState.update {
                    uiState.value.copy(
                        airportList = airportList,
                        favoriteList = favoriteList
                    )
                }
            }
        } else {
            getAirportsJob?.cancel()

            getAirportsJob = flightRepository.getAllAirportsFlow(query)
                // on each emission
                .onEach { result ->
                    _uiState.update {
                        uiState.value.copy(
                            airportList = result,
                        )
                    }
                }.launchIn(viewModelScope)
        }
    }

    fun updateQuery(searchQuery: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = searchQuery,
            )
        }
        updatePreferenceSearchValue(searchQuery)
    }

    fun updateSelectedCode(selectedCode: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedCode = selectedCode,
            )
        }
    }

    fun removeDbFavorite(record: Favorite) {
        viewModelScope.launch {
            deletedRecord = record

            flightRepository.deleteFavoriteFlight(record)


            // Cheating, I am forcing a Recomposition
            // I should be using Flow but am not sure how to atm
            //val play = flightRepository.getAllFavoritesFlights()
            val xx = uiState.value.favoriteList.toMutableStateList()
            //val index = xx.indexOf(record)
            xx.remove(record)
            _uiState.update {
                uiState.value.copy(
                    favoriteList = xx,
                )
            }

        }
    }
//    fun restoreDbRecord() {
//        viewModelScope.launch {
//            notesRepository.insertNote(deletedNote ?: return@launch)
//            deletedNote = null
//        }
//    }
//    _uiState.update {
//        uiState.value.copy(
//            searchQuery = ""
//        )
//    }

    fun updatePreferenceSearchValue(newValue: String) {
        viewModelScope.launch {
            userPreferencesRepository.updateUserPreferences(searchValue = newValue)
        }
    }


    // Notes: Question: At moment this is chuck of code is repeated in two files
    //  in QueryViewModel and in DetailsViewModel.
    //  what can I do/ place it so as not to have repeat code? I tried but I got a bunch of errors
    /**
     * Factory for BookshelfViewModel] that takes BookshelfRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                val flightRepository = application.container.flightRepository
                val preferencesRepository = application.userPreferencesRepository
                SearchViewModel(
                    flightRepository = flightRepository,
                    userPreferencesRepository = preferencesRepository
                )
            }
        }
    }
}