package de.sortingfarmer.manju.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.sortingfarmer.manju.R
import de.sortingfarmer.manju.openapi.models.Chapter
import de.sortingfarmer.manju.openapi.models.StatisticsDetailsComments
import testChapter

@Composable
fun ChapterCard(
    chapter: Chapter,
    statistics: StatisticsDetailsComments? = null,
    read: Boolean = false,
    modifier: Modifier = Modifier
) {
    val iconsize = 16

    Card (
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (read) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(iconsize.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.eye_off_outline),
                            contentDescription = stringResource(R.string.chapter_read),
                        )
                    }
                } else {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(iconsize.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.eye_outline),
                            contentDescription = stringResource(R.string.chapter_unread),
                        )
                    }
                }
                VerticalDivider()
                Text(
                    text = chapter.attributes?.title ?: stringResource(
                        R.string.vol_ch,
                        chapter.attributes?.volume ?: 0,
                        chapter.attributes?.chapter ?: 0
                    )
                )
                VerticalDivider()
                Row {
                    Image(
                        painter = painterResource(R.drawable.chatbox_outline),
                        contentDescription = stringResource(R.string.comments),
                        modifier = Modifier.size(iconsize.dp)
                    )
                    Text(text = "${statistics?.repliesCount ?: 0.toString()}")
                }
            }
            HorizontalDivider()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Image(
                    painter = painterResource(R.drawable.people_circle_outline),
                    contentDescription = stringResource(R.string.group),
                    modifier = Modifier.size(iconsize.dp)
                )
                VerticalDivider()
                chapter.relationships?.forEach {
                    if (it.type == "scanlation_group") {
                        Text(text = it.attributes?.name ?: stringResource(R.string.no_name_available))
                    }
                }
                VerticalDivider()
                Image(
                    painter = painterResource(R.drawable.eye_outline),
                    contentDescription = stringResource(R.string.views),
                    modifier = Modifier.size(iconsize.dp)
                )
                Text(text = "N/A")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChapterCardReadPreview() {
    ChapterCard(testChapter, read = true)
}

@Preview(showBackground = true)
@Composable
fun ChapterCardUnreadPreview() {
    ChapterCard(testChapter, read = false)
}