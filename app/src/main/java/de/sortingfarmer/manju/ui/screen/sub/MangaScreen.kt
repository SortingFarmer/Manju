package de.sortingfarmer.manju.ui.screen.sub

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.MangaApi
import de.sortingfarmer.manju.openapi.models.MangaResponse
import de.sortingfarmer.manju.ui.components.MangaTextCard
import java.util.UUID

@Composable
fun MangaScreen(navController: NavHostController, id: String) {
    var manga by remember { mutableStateOf<MangaResponse?>(null) }

    LaunchedEffect(key1 = Unit) {
        manga = RetrofitClient.instance.create(MangaApi::class.java)
            .getMangaId(
                id = UUID.fromString(id),
                includes = listOf("cover_art", "tags")
            ).body()
    }

    manga?.let { MangaTextCard(manga = it.data!!) {} }
}