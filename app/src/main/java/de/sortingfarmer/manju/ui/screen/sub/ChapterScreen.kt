package de.sortingfarmer.manju.ui.screen.sub

import DisplayImage
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.AtHomeApi
import de.sortingfarmer.manju.openapi.models.GetAtHomeServerChapterId200Response
import java.util.UUID
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier

@Composable
fun ChapterScreen(
    navController: NavHostController,
    chapterId: String
) {
    val chapterResponse by produceState<GetAtHomeServerChapterId200Response?>(initialValue = null, key1 = chapterId) {
        value = RetrofitClient.instance
            .create(AtHomeApi::class.java)
            .getAtHomeServerChapterId(chapterId = UUID.fromString(chapterId))
            .body()
    }

    val baseUrl = chapterResponse?.baseUrl
    val hash = chapterResponse?.chapter?.hash
    val imageUrls = chapterResponse?.chapter?.data?.map { filename ->
        "$baseUrl/data/$hash/$filename"
    }

    LazyColumn {
        imageUrls?.forEach {
            item {
                DisplayImage(it, modifier = Modifier.fillMaxSize())
            }
        }
    }
}