package de.sortingfarmer.manju.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.RetrofitClient
import de.sortingfarmer.manju.openapi.apis.StatisticsApi
import de.sortingfarmer.manju.openapi.models.Chapter
import de.sortingfarmer.manju.openapi.models.GetStatisticsChapterUuid200Response
import de.sortingfarmer.manju.ui.theme.ManjuTheme
import de.sortingfarmer.manju.ui.theme.ManjuThemeExtended
import formatNumber
import getRelativeTime
import testChapter
import java.util.UUID
import androidx.core.net.toUri

@Composable
fun ChapterCard(
    chapter: Chapter,
    modifier: Modifier = Modifier,
    read: Boolean = false,
    onClick: () -> Unit,
    onReadMarkerClick: (id: UUID?) -> Unit,
    onGroupClick: (id: UUID?) -> Unit,
    onUserClick: (id: UUID?) -> Unit,
) {
    val iconsize = 30
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var statistics by remember { mutableStateOf<GetStatisticsChapterUuid200Response?>(null) }

    LaunchedEffect(key1 = chapter.id) {
        statistics = RetrofitClient.instance.create(StatisticsApi::class.java)
            .getStatisticsChapterUuid(
                uuid = chapter.id!!,
            ).body()
    }

    Card(
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clickable(onClick = { onClick() })
    ) {
        Column {
            Row {
                VerticalDivider(
                    modifier = Modifier
                        .height(iconsize.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    thickness = 5.dp,
                    color = if (read) ManjuThemeExtended.extendedColors.statusGray.color else ManjuThemeExtended.extendedColors.statusBlue.color
                )
                Image(
                    painter = painterResource(
                        if (read) R.drawable.eye_off_outline else R.drawable.eye_outline
                    ),
                    contentDescription = stringResource(
                        if (read) R.string.chapter_unread else R.string.chapter_read
                    ),
                    modifier = Modifier
                        .size(iconsize.dp)
                        .padding(2.dp)
                        .clickable(onClick = { onReadMarkerClick(chapter.id) })
                )
                if (chapter.attributes?.volume != null) {
                    Text(
                        text = stringResource(R.string.vol, chapter.attributes.volume),
                        modifier = Modifier
                            .padding(0.dp, 5.dp, 1.dp, 0.dp),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (chapter.attributes?.chapter != null) {
                    Text(
                        text = stringResource(R.string.ch, chapter.attributes.chapter),
                        modifier = Modifier
                            .padding(0.dp, 5.dp, 1.dp, 0.dp),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (chapter.attributes?.title != null) {
                    Text(
                        text = chapter.attributes.title,
                        modifier = Modifier
                            .padding(5.dp),
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row(
                modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Row(
                    modifier = Modifier.clickable(onClick = { expanded = !expanded })
                ) {
                    Icon(
                        painter = painterResource(R.drawable.language_outline),
                        contentDescription = stringResource(R.string.translators),
                        modifier = Modifier
                            .size(15.dp)
                            .padding(2.dp)
                    )
                    Text(
                        text = stringResource(R.string.translators),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    chapter.relationships?.forEach {
                        if (it.type == "scanlation_group") {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = it.attributes?.name
                                            ?: stringResource(R.string.no_name_available)
                                    )
                                },
                                onClick = { onGroupClick(it.id) },
                                leadingIcon = {
                                    Icon(
                                        painterResource(R.drawable.people_circle_outline),
                                        contentDescription = stringResource(R.string.group),
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            )
                        }
                        if (it.type == "user") {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = it.attributes?.username
                                            ?: stringResource(R.string.no_name_available)
                                    )
                                },
                                onClick = { onUserClick(it.id) },
                                leadingIcon = {
                                    Icon(
                                        painterResource(R.drawable.person_circle_outline),
                                        contentDescription = stringResource(R.string.translator),
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            )
                        }
                    }
                }
                Text(
                    text = " • ",
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier.clickable(onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            "https://forums.mangadex.org/threads/${
                                statistics?.statistics?.get(chapter.id.toString())?.comments?.threadId
                            }".toUri()
                        )
                        context.startActivity(intent)
                    })
                ) {
                    Icon(
                        painter = painterResource(R.drawable.chatbox_outline),
                        contentDescription = stringResource(R.string.comments),
                        modifier = Modifier
                            .size(15.dp)
                            .padding(2.dp)
                    )
                    Text(
                        text = formatNumber(
                            statistics?.statistics?.get(chapter.id.toString())?.comments?.repliesCount?.toInt()
                                ?: 0
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = " • ",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text =
                        getRelativeTime(chapter.attributes?.readableAt!!).toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChapterCardReadPreview() {
    ManjuTheme {
        ChapterCard(
            testChapter,
            read = true,
            onClick = {},
            onReadMarkerClick = {},
            onGroupClick = {},
            onUserClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChapterCardUnreadPreview() {
    ManjuTheme {
        ChapterCard(
            testChapter,
            read = false,
            onClick = {},
            onReadMarkerClick = {},
            onGroupClick = {},
            onUserClick = {},
        )
    }
}