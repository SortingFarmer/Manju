package de.sortingfarmer.manju.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.openapi.models.Chapter
import de.sortingfarmer.manju.openapi.models.GetStatisticsChapterUuid200ResponseStatisticsValue
import de.sortingfarmer.manju.ui.theme.ManjuTheme
import de.sortingfarmer.manju.ui.theme.ManjuThemeExtended
import formatNumber
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
    onGroupClick: (id: UUID?) -> Unit,
    onTranslatorClick: (id: UUID?) -> Unit,
    onCommentsClick: (id: BigDecimal?) -> Unit,
) {
    val iconsize = 30

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
                Text(
                    text = chapter.attributes?.title
                        ?: stringResource(R.string.no_title_available),
                    modifier = Modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
                        text = formatNumber(statistics?.comments?.repliesCount?.toInt() ?: 0),
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
    ManjuTheme {
        ChapterCard(
            testChapter,
            read = true,
            statistics = testChapterStatistics,
            onClick = {},
            onReadMarkerClick = {},
            onGroupClick = {},
            onTranslatorClick = {},
            onCommentsClick = {},
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
            statistics = testChapterStatistics,
            onClick = {},
            onReadMarkerClick = {},
            onGroupClick = {},
            onTranslatorClick = {},
            onCommentsClick = {},
        )
    }
}