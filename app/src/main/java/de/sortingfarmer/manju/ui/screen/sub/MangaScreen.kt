package de.sortingfarmer.manju.ui.screen.sub

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import de.sortingfarmer.manju.apiCalls.ApiClient
import de.sortingfarmer.manju.dataClass.apiResult.GET.GetManga
import de.sortingfarmer.manju.ui.components.MangaTextCard

@Composable
fun MangaScreen(navController: NavHostController, id: String) {
    var manga by remember { mutableStateOf<GetManga?>(null) }

    LaunchedEffect(key1 = Unit) {
        manga = ApiClient.api.getMangaById(id)
    }

    if (manga != null) {
        MangaTextCard(manga!!.data) {}
    } else {
        Text("Loading...")
    }
}