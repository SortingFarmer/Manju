package com.example.manju.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.manju.dataClass.ApiClient
import com.example.manju.dataClass.apiResult.GET.GetMangas
import com.example.manju.ui.components.MangaImageCard

@Composable
fun AdvancedSearchScreen(navController: NavController) {
    var manga by remember { mutableStateOf<GetMangas?>(null) }
    var state by remember { mutableStateOf(LazyGridState()) }

    LaunchedEffect(key1 = Unit) {
        manga = ApiClient.api.getManga()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        state = state,
    ) {
        manga?.data?.forEach { manga ->
            item {
                MangaImageCard(manga) {
                    navController.navigate("title/${manga.id}")
                }
            }
        }
        if (manga == null) {
            item(
                span = { GridItemSpan(2) }
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(25.dp)
                        .padding(start = 10.dp, end = 10.dp)
                )
            }
        }
    }
}