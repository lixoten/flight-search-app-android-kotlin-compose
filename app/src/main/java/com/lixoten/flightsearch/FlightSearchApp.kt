package com.lixoten.flightsearch


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lixoten.flightsearch.ui.screens.flight_screen.FlightScreen
import com.lixoten.flightsearch.ui.screens.flight_screen.FlightScreenDestination
//import com.lixoten.flightsearch.ui.screens.flight_screen.FlightScreen
//import com.lixoten.flightsearch.ui.screens.flight_screen.FlightScreenDestination
import com.lixoten.flightsearch.ui.screens.search.SearchDestination
import com.lixoten.flightsearch.ui.screens.search.SearchScreen


@Composable
fun FlightSearchApp() {
    val navController = rememberNavController()
    Scaffold() { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = SearchDestination.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = SearchDestination.route) {
                SearchScreen(
                    modifier = Modifier,
                    onSelectCode = {
                        navController.navigate("${FlightScreenDestination.route}/${it}")
                    }
                )
            }
            composable(
                route = FlightScreenDestination.routeWithArgs,
                arguments = listOf(navArgument(FlightScreenDestination.codeArg) {
                    type = NavType.StringType
                }
                )
            ) { navBackStackEntry ->
                // Retrieve the passed argument
                //val code =
                //    navBackStackEntry.arguments?.getString(FlightScreenDestination.codeArg)
                FlightScreen()

            }
        }
    }
}