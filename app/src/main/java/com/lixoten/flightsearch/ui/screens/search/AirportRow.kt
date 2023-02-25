package com.lixoten.flightsearch.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun AirportRow(
    modifier: Modifier = Modifier,
    //airport: Airport,
    //airport: Air,
    code: String,
    name: String,
    onSelectCode: (String) -> Unit = { },
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickable(
                    onClick = {
                        if (code != "") {
                            onSelectCode(code)
                        }
                    },
                )
        ) {
                Spacer(
                    modifier = Modifier.width(24.dp)
                )
            Text(
                text = code,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
    }
}



//@Preview
//@Composable
//fun AirportRowPreview() {
//    AirportRow(
//        airport = Airport(1, "JFK", "John F. Kennedy International Airport", 1111),
//        onSelectCode = {},
//    )
//}
//

