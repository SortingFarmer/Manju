package de.sortingfarmer.manju.ui.components

import DisplayImage
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.StatisticsApi
import de.sortingfarmer.manju.openapi.models.GetStatisticsMangaUuid200Response
import de.sortingfarmer.manju.openapi.models.Manga
import de.sortingfarmer.manju.openapi.models.Tag
import de.sortingfarmer.manju.openapi.models.TagAttributes
import formatNumber
import testManga
import java.math.RoundingMode

@Composable
fun MangaImageCard(
    manga: Manga,
    onClick: () -> Unit,
) {
    val coverFileName = manga.relationships?.firstOrNull {
        it.type == "cover_art"
    }?.attributes?.fileName
    val imageUrl = coverFileName?.let {
        "https://uploads.mangadex.org/covers/${manga.id}/$it"
    }

    val imageModifier = Modifier
        .fillMaxWidth()
        .aspectRatio(2f / 3f)
        .padding(5.dp)
        .clip(RoundedCornerShape(5.dp))

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (imageUrl != null) {
                DisplayImage(
                    imageUrl = imageUrl,
                    modifier = imageModifier,
                    contentDescription = manga.attributes?.title?.get("en")
                        ?: stringResource(R.string.no_title_available),
                    onError = {
                        Image(
                            painter = painterResource(id = R.drawable.coverplaceholder),
                            contentDescription = stringResource(R.string.no_image_available),
                            modifier = imageModifier
                        )
                    }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.coverplaceholder),
                    contentDescription = stringResource(R.string.no_image_available),
                    modifier = imageModifier
                )
            }
            Text(
                text = manga.attributes?.title?.get("en")
                    ?: stringResource(R.string.no_title_available),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MangaTextCard(
    manga: Manga,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    var statistics by remember { mutableStateOf<GetStatisticsMangaUuid200Response?>(null) }

    LaunchedEffect(key1 = manga.id) {
        statistics = RetrofitClient.instance.create(StatisticsApi::class.java)
            .getStatisticsMangaUuid(uuid = manga.id!!).body()
    }

    val coverFileName = manga.relationships?.firstOrNull { it.type == "cover_art" }
        ?.attributes?.fileName
    val imageUrl: String? = coverFileName?.let {
        "https://uploads.mangadex.org/covers/${manga.id}/$it"
    }

    val fixedImageWidth = 83.dp
    val imageModifier = Modifier
        .width(fixedImageWidth)
        .aspectRatio(2f / 3f)
        .padding(5.dp)
        .clip(RoundedCornerShape(5.dp))

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                if (imageUrl != null) {
                    DisplayImage(
                        imageUrl = imageUrl,
                        modifier = imageModifier,
                        contentDescription = manga.attributes?.title?.get("en")
                            ?: stringResource(R.string.no_title_available),
                        onError = {
                            Image(
                                painter = painterResource(id = R.drawable.coverplaceholder),
                                contentDescription = stringResource(R.string.no_image_available),
                                modifier = imageModifier
                            )
                        }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.coverplaceholder),
                        contentDescription = stringResource(R.string.no_image_available),
                        modifier = imageModifier
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = manga.attributes?.title?.get("en")
                            ?: manga.attributes?.title?.get("ja")
                            ?: stringResource(R.string.no_title_available),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        // Rating
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.star_outline),
                                contentDescription = stringResource(R.string.rating),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = statistics
                                    ?.statistics?.get(manga.id.toString())
                                    ?.rating?.bayesian
                                    ?.setScale(2, RoundingMode.HALF_UP)
                                    ?.toString() ?: "0",
                                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        // Follows
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.bookmark_outline),
                                contentDescription = stringResource(R.string.follows),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = formatNumber(
                                    statistics?.statistics?.get(manga.id.toString())
                                        ?.follows?.toInt() ?: 0
                                ),
                                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        // Views
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.eye_outline),
                                contentDescription = stringResource(R.string.views),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = "N/A",
                                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        // Comments (clickable)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                statistics?.statistics?.get(manga.id.toString())
                                    ?.comments?.threadId?.let { threadId ->
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            "https://forums.mangadex.org/threads/$threadId".toUri()
                                        )
                                        context.startActivity(intent)
                                    }
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.chatbox_outline),
                                contentDescription = stringResource(R.string.comments),
                                modifier = Modifier.size(15.dp)
                            )
                            Text(
                                text = formatNumber(
                                    statistics?.statistics?.get(manga.id.toString())
                                        ?.comments?.repliesCount?.toInt() ?: 0
                                ),
                                modifier = Modifier.padding(horizontal = 3.dp, vertical = 1.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    // Render tags if available.
                    manga.attributes?.tags?.let { tags ->
                        TagRow(tags = tags)
                    }
                }
            }
            // Manga description at the bottom.
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagRow(tags: List<Tag>) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tags.forEach { tag ->
            TagCard(tag = tag)
        }
    }
}

@Composable
fun TagCard(
    tag: Tag,
    onClick: () -> Unit = {}
) {
    val isContentTag = tag.attributes?.group == TagAttributes.Group.content
    val containerColor = if (isContentTag) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        ),
        modifier = Modifier
            .clickable { onClick() }
            .height(30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tag.attributes?.name?.get("en")
                    ?: tag.attributes?.group?.value
                    ?: stringResource(R.string.no_name_available),
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            )
        }
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