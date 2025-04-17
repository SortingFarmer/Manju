package de.sortingfarmer.manju.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.openapi.models.Chapter
import de.sortingfarmer.manju.openapi.models.GetStatisticsChapterUuid200ResponseStatisticsValue
import getRelativeTime
import testChapter
import testChapterStatistics
import java.math.BigDecimal
import java.util.UUID

@Composable
fun ChapterCard(
    chapter: Chapter,
    statistics: GetStatisticsChapterUuid200ResponseStatisticsValue?,
    modifier: Modifier = Modifier,
    read: Boolean = false,
    onClick: () -> Unit,
    onReadMarkerClick: (id: UUID?) -> Unit,
    onGroupClick: (id: UUID?) -> Unit = {},
    onTranslatorClick: (id: UUID?) -> Unit = {},
    onCommentsClick: (id: BigDecimal?) -> Unit = {},
) {
    val iconsize = 30

    Card(
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .clickable(onClick = { onClick() })
    ) {
        Column {
            Row {
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
                Text(
                    text = chapter.attributes?.title
                        ?: stringResource(R.string.no_title_available),
                    modifier = Modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text =
                        getRelativeTime(chapter.attributes?.readableAt!!).toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = " • ",
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier.clickable(onClick = { onCommentsClick(statistics?.comments?.threadId) })
                ) {
                    Icon(
                        painter = painterResource(R.drawable.chatbox_outline),
                        contentDescription = stringResource(R.string.comments),
                        modifier = Modifier
                            .size(15.dp)
                            .padding(2.dp)
                    )
                    Text(
                        text = if (statistics?.comments?.repliesCount == null) "0" else statistics.comments.repliesCount.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Row(
                modifier = Modifier.padding(6.dp, 0.dp, 0.dp, 2.dp)
            ) {
                chapter.relationships?.forEach {
                    if (it.type == "scanlation_group") {
                        Text(
                            text = it.attributes?.name
                                ?: stringResource(R.string.no_name_available),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable(onClick = { onGroupClick(it.id) })
                        )
                        Text(
                            text = " • ",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    if (it.type == "user") {
                        Text(
                            text = it.attributes?.username
                                ?: stringResource(R.string.no_name_available),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable(onClick = { onTranslatorClick(it.id) })
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ChapterCardReadPreview() {
    ChapterCard(
        testChapter,
        read = true,
        statistics = testChapterStatistics,
        onClick = {},
        onReadMarkerClick = {},
        onGroupClick = {},
        onTranslatorClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun ChapterCardUnreadPreview() {
    ChapterCard(
        testChapter,
        read = false,
        statistics = testChapterStatistics,
        onClick = {},
        onReadMarkerClick = {},
        onGroupClick = {},
        onTranslatorClick = {},
    )
}