package de.sortingfarmer.manju.ui.components

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.apiCalls.ApiClient
import de.sortingfarmer.manju.dataClass.manga.Manga
import de.sortingfarmer.manju.dataClass.manga.Tag

@Composable
fun MangaImageCard(
    manga: Manga,
    onClick: () -> Unit,
) {
    val url = manga.relationships.find {
        it.type == "cover_art"
    }?.attributes?.fileName?.let {
        ApiClient.IMAGE_URL + "/covers/${manga.id}/$it"
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = { onClick() })
    ) {
        DisplayImage(
            imageUrl = url.toString(),
            modifier = Modifier
                .width(((200 / 3) * 2).dp)
                .height(200.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentDescription = manga.attributes.title.en
                ?: stringResource(R.string.no_title_available)
        )
        Text(
            text = manga.attributes.title.en ?: stringResource(R.string.no_title_available),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
fun MangaTextCard(
    manga: Manga,
    onClick: () -> Unit,
) {

    val url = manga.relationships.find {
        it.type == "cover_art"
    }?.attributes?.fileName?.let {
        ApiClient.IMAGE_URL + "/covers/${manga.id}/$it"
    }

    val iconSize = 20
    val imageSize = 125
    Card(
        modifier = Modifier
            .padding(16.dp)
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
                contentDescription = manga.attributes.title.en
                    ?: stringResource(R.string.no_title_available)
            )
            Column {
                Text(
                    text = manga.attributes.title.en ?: stringResource(R.string.no_title_available),
                    modifier = Modifier
                        .padding(5.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
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
                            contentDescription = "Rating",
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
                            contentDescription = "Follows",
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
                            contentDescription = "Views",
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
                            contentDescription = "Comments",
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
                Row {
                    TagRow(manga = manga)
                }
            }
        }
        Text(
            text = manga.attributes.description?.en
                ?: stringResource(R.string.no_description_available),
            modifier = Modifier.padding(5.dp),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagRow(manga: Manga) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .horizontalScroll(rememberScrollState()), // Add horizontal scroll
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        manga.attributes.tags.forEach { tag ->
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
            text = tag.attributes.name.en,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MangaImageCardPreview() {
    MangaImageCard(shotgunBoy) {}
}

@Preview(showBackground = true)
@Composable
fun MangaTextCardPreview() {
    MangaTextCard(shotgunBoy) {}
}

@Preview(showBackground = true)
@Composable
fun TagCardPreview() {
    TagCard(shotgunBoy.attributes.tags[0])
}