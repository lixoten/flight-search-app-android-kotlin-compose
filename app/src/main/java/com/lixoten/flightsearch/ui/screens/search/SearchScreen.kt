package com.lixoten.flightsearch.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lixoten.flightsearch.NavigationDestination
import com.lixoten.flightsearch.R
import com.lixoten.flightsearch.model.Favorite

object SearchDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSelectCode: (String) -> Unit
) {
    val viewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
    val uiState = viewModel.uiState.collectAsState().value

    Column( modifier = modifier) {
        SearchTextField(
            uiState.searchQuery,
            onQueryChange = {
                viewModel.updateQuery(it)
                viewModel.updateSelectedCode("")
                viewModel.onQueryChange(it)
            }
        )

        if (uiState.searchQuery.isEmpty()) {

            val favoriteList = uiState.favoriteList
            val airportList = uiState.airportList

            if (favoriteList.isNotEmpty()){
                FavoriteResult(
                    airportList = airportList,
                    favoriteList = favoriteList,
                    onFavoriteClick = { departureCode: String, destinationCode: String ->
                        val tmp = Favorite(
                            id = favoriteList.filter {
                                    xxx -> (xxx.departureCode == departureCode && xxx.destinationCode == destinationCode)
                            }.first().id,
                            departureCode = departureCode,
                            destinationCode = destinationCode,
                        )
                        viewModel.removeDbFavorite(tmp)
                    },
                )
            } else {
                Text(text = "No Favorites yet")
            }
        } else {

            val airports = uiState.airportList

            SearchResults(
                airports = airports,
                onSelectCode = onSelectCode
            )
        }
    }
}