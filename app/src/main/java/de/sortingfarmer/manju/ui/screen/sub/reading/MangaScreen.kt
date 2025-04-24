package de.sortingfarmer.manju.ui.screen.sub.reading

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.ChapterApi
import de.sortingfarmer.manju.openapi.apis.MangaApi
import de.sortingfarmer.manju.openapi.apis.StatisticsApi
import de.sortingfarmer.manju.openapi.models.ChapterList
import de.sortingfarmer.manju.openapi.models.GetStatisticsMangaUuid200Response
import de.sortingfarmer.manju.openapi.models.MangaResponse
import de.sortingfarmer.manju.ui.components.ChapterCard
import de.sortingfarmer.manju.ui.components.MangaTextCard
import java.util.UUID

@Composable
fun MangaScreen(navController: NavHostController, id: String) {
    var manga by remember { mutableStateOf<MangaResponse?>(null) }
    var statistics by remember { mutableStateOf<GetStatisticsMangaUuid200Response?>(null) }
    var chapters by remember { mutableStateOf<ChapterList?>(null) }

    LaunchedEffect(key1 = Unit) {
        manga = RetrofitClient.instance
            .create(MangaApi::class.java)
            .getMangaId(
                id = UUID.fromString(id),
                includes = listOf("cover_art", "tags")
            ).body()

        statistics = RetrofitClient.instance
            .create(StatisticsApi::class.java)
            .getStatisticsMangaUuid(
                uuid = UUID.fromString(id)
            ).body()

        chapters = RetrofitClient.instance
            .create(ChapterApi::class.java)
            .getChapter(
                manga = UUID.fromString(id),
                includes = listOf("scanlation_group", "user"),
                limit = 100
            ).body()
    }

    LazyColumn {
        manga?.let {
            item {
                MangaTextCard(
                    manga = it.data!!,
                ) {}
            }
        }
        chapters?.data?.forEach {
            item {
                ChapterCard(
                    chapter = it,
                    read = true,
                    onClick = {
                        navController.navigate("chapter/${id}/${it.id}")
                    },
                    onReadMarkerClick = {},
                    onGroupClick = {},
                    onUserClick = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}