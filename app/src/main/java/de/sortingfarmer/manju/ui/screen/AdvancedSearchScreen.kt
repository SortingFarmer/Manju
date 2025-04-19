package de.sortingfarmer.manju.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.MangaApi
import de.sortingfarmer.manju.openapi.models.MangaList
import de.sortingfarmer.manju.ui.components.MangaImageCard
import de.sortingfarmer.manju.ui.components.MangaTextCard

@Composable
fun AdvancedSearchScreen(navController: NavController) {
    var manga by remember { mutableStateOf<MangaList?>(null) }
    var gridState by remember { mutableStateOf(LazyGridState()) }
    var listState by remember { mutableStateOf(LazyListState()) }
    var grid by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = Unit) {
        manga = RetrofitClient.instance.create(MangaApi::class.java)
            .getSearchManga(
                includes = listOf("cover_art", "tags")
            ).body()
    }

    Column {
        if (manga == null) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row (
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.grid_enabled, grid))
            Switch(
                checked = grid,
                onCheckedChange = { isChecked -> grid = isChecked },
            )
        }
        if (grid) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                state = gridState,
            ) {
                manga?.data?.forEach { manga ->
                    item {
                        MangaImageCard(manga) {
                            navController.navigate("title/${manga.id}")
                        }
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                state = listState,
            ) {
                manga?.data?.forEach { manga ->
                    item {
                        MangaTextCard(
                            manga,
                        ) {
                            navController.navigate("title/${manga.id}")
                        }
                    }
                }
            }
        }
    }
}