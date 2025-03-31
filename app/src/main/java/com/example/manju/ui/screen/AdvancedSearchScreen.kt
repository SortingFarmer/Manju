package com.example.manju.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdvancedSearchScreen(navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(text = "Advanced Search")
        }
        items(
            count = 50,
        ) { index ->
            Button(onClick = { navController.navigate("title/$index") }) {
                Text(text = "Title $index")
            }
        }
    }
}