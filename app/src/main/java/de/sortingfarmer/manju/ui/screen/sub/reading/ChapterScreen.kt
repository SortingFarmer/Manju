package de.sortingfarmer.manju.ui.screen.sub.reading

import DisplayImage
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.AtHomeApi
import de.sortingfarmer.manju.openapi.models.GetAtHomeServerChapterId200Response
import java.util.UUID
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.ui.theme.ManjuThemeExtended

@Composable
fun ChapterScreen(
    navController: NavHostController,
    mangaId: String,
    chapterId: String
) {
    val chapterResponse by produceState<GetAtHomeServerChapterId200Response?>(initialValue = null, key1 = chapterId) {
        value = RetrofitClient.instance
            .create(AtHomeApi::class.java)
            .getAtHomeServerChapterId(chapterId = UUID.fromString(chapterId))
            .body()
    }

    val baseUrl = chapterResponse?.baseUrl.orEmpty()
    val hash = chapterResponse?.chapter?.hash.orEmpty()
    val imageUrls = chapterResponse?.chapter?.data?.map { filename ->
        "$baseUrl/data/$hash/$filename"
    } ?: emptyList()

    LazyColumn {
        item {
            HorizontalDivider(
                thickness = 5.dp,
                color = ManjuThemeExtended.extendedColors.statusGray.color
            )
        }

        imageUrls.forEach { imageUrl ->
            item {
            DisplayImage(imageUrl = imageUrl, modifier = Modifier.fillMaxWidth())
            HorizontalDivider(
                thickness = 5.dp,
                color = ManjuThemeExtended.extendedColors.statusGray.color
            )
                }
        }
    }
}