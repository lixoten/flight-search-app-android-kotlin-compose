package com.lixoten.flightsearch.ui.screens.search

import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite

data class SearchUiState(
    val searchQuery: String = "",
    val selectedCode: String = "",
    val airportList: List<Airport> = emptyList(),
    val favoriteList: List<Favorite> = emptyList(),
)