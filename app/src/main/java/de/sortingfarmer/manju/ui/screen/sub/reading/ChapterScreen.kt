package de.sortingfarmer.manju.ui.screen.sub.reading

import DisplayImage
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.AtHomeApi
import de.sortingfarmer.manju.openapi.models.GetAtHomeServerChapterId200Response
import de.sortingfarmer.manju.ui.theme.ManjuThemeExtended
import java.util.UUID

@Composable
fun ChapterScreen(
    navController: NavHostController,
    mangaId: String,
    chapterId: String,
) {
    val chapterResponse by produceState<GetAtHomeServerChapterId200Response?>(
        initialValue = null,
        key1 = chapterId
    ) {
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

    var grid by remember { mutableStateOf(true) }


    LazyColumn {
        item {
            Row {
                Text("Toggle devider lines")
                Switch(
                    checked = grid,
                    onCheckedChange = { isChecked -> grid = isChecked },
                    modifier = Modifier.padding(1.dp).size(5.dp)
                )
            }
        }
        item {
            if (grid) {
                HorizontalDivider(
                    thickness = 5.dp,
                    color = ManjuThemeExtended.extendedColors.statusGray.color
                )
            }
        }

        imageUrls.forEach { imageUrl ->
            item {
                DisplayImage(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .width(IntrinsicSize.Max)
                )
                if (grid) {
                    HorizontalDivider(
                        thickness = 5.dp,
                        color = ManjuThemeExtended.extendedColors.statusGray.color
                    )
                }
            }
        }
    }
}