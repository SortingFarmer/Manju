package de.sortingfarmer.manju.ui.components

import DisplayImage
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.openapi.models.Manga
import de.sortingfarmer.manju.openapi.models.Tag
import testManga

@Composable
fun MangaImageCard(
    manga: Manga,
    onClick: () -> Unit,
) {
    val url = manga.relationships?.find {
        it.type == "cover_art"
    }?.attributes?.fileName.let {
        "https://uploads.mangadex.org/covers/${manga.id}/${it}"
    }

    Card(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clickable(onClick = { onClick() })
    ) {
        DisplayImage(
            imageUrl = url.toString(),
            modifier = Modifier
                .width(((200 / 3) * 2).dp)
                .height(200.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentDescription = manga.attributes?.title?.get("en")
                ?: stringResource(R.string.no_title_available),
            onError = {
                Image(
                    painter = painterResource(R.drawable.coverplaceholder),
                    contentDescription = stringResource(R.string.no_image_available),
                    modifier = Modifier
                        .width(((200 / 3) * 2).dp)
                        .height(200.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp)),
                )
            }
        )
        Text(
            text = manga.attributes?.title?.get("en")
                ?: stringResource(R.string.no_title_available),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun MangaTextCard(
    manga: Manga,
    onClick: () -> Unit,
) {

    val url = manga.relationships?.find {
        it.type == "cover_art"
    }?.attributes?.fileName.let {
        "https://uploads.mangadex.org/covers/${manga.id}/${it}"
    }
    Log.d("MangaTextCard url", url.toString())

    val iconSize = 20
    val imageSize = 125
    Card(
        modifier = Modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clickable(onClick = { onClick() })
    ) {
        Row {
            DisplayImage(
                imageUrl = url.toString(),
                modifier = Modifier
                    .width(((imageSize / 3) * 2).dp)
                    .height(imageSize.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentDescription = manga.attributes?.title?.get("en")
                    ?: stringResource(R.string.no_title_available),
                onError = {
                    Image(
                        painter = painterResource(R.drawable.coverplaceholder),
                        contentDescription = stringResource(R.string.no_image_available),
                        modifier = Modifier
                            .width(((imageSize / 3) * 2).dp)
                            .height(imageSize.dp)
                            .padding(5.dp)
                            .clip(RoundedCornerShape(5.dp)),
                    )
                }
            )
            Column {
                Text(
                    text = manga.attributes?.title?.get("en")
                        ?: stringResource(R.string.no_title_available),
                    modifier = Modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.star_outline),
                            contentDescription = stringResource(R.string.rating),
                            modifier = Modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Ratings
                            text = "10.0",
                            modifier = Modifier.padding(2.dp),
                        )

                    }
                    VerticalDivider(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.height(iconSize.dp)
                    )
                    Row {
                        Image(
                            painter = painterResource(R.drawable.bookmark_outline),
                            contentDescription = stringResource(R.string.follows),
                            modifier = Modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Follows
                            text = "0",
                            modifier = Modifier.padding(2.dp),
                        )

                    }
                    VerticalDivider(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.height(iconSize.dp)
                    )
                    Row {
                        Image(
                            painter = painterResource(R.drawable.eye_outline),
                            contentDescription = stringResource(R.string.views),
                            modifier = Modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Views
                            text = "N/A",
                            modifier = Modifier.padding(2.dp),
                        )
                    }
                    VerticalDivider(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.height(iconSize.dp)
                    )
                    Row {
                        Image(
                            painter = painterResource(R.drawable.chatbox_outline),
                            contentDescription = stringResource(R.string.comments),
                            modifier = Modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Comments
                            text = "0",
                            modifier = Modifier.padding(2.dp),
                        )
                    }
                }
                TagRow(tags = manga.attributes?.tags!!)
            }
        }
        Text(
            text = manga.attributes?.description?.get("en")
                ?: stringResource(R.string.no_description_available),
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagRow(tags: List<Tag>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        tags.forEach { tag ->
            TagCard(tag = tag)
        }
    }
}

@Composable
fun TagCard(
    tag: Tag,
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        ),
        modifier = Modifier.clickable(onClick = { onClick() })
    ) {
        Text(
            text = tag.attributes?.name?.get("en") ?: "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MangaImageCardPreview() {
    MangaImageCard(testManga) {}
}

@Preview(showBackground = true)
@Composable
fun MangaTextCardPreview() {
    MangaTextCard(testManga) {}
}

@Preview(showBackground = true)
@Composable
fun TagCardPreview() {
    TagCard(testManga.attributes!!.tags!![0])
}