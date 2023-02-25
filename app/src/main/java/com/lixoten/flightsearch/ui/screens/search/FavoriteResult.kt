package com.lixoten.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lixoten.flightsearch.model.Airport
import com.lixoten.flightsearch.model.Favorite
import com.lixoten.flightsearch.ui.screens.flight_screen.FlightRow

@Composable
fun FavoriteResult(
    modifier: Modifier = Modifier,
    airportList: List<Airport>,
    favoriteList: List<Favorite>,
    onFavoriteClick: (String, String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(favoriteList, key = { it.id }) { item ->
            val departAirport = airportList.first { airport -> airport.code == item.departureCode }
            val destinationAirport =
                airportList.first { airport -> airport.code == item.destinationCode }
            FlightRow(
                isFavorite = true,
                departureAirportCode = departAirport.code,
                departureAirportName = departAirport.name,
                destinationAirportCode = destinationAirport.code,
                destinationAirportName = destinationAirport.name,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}