package com.example.manju.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.manju.R
import com.example.manju.data.manga.Manga
import com.example.manju.data.manga.Relationship
import com.example.manju.data.manga.Tag

@Composable
fun MangaImageCard(
    manga: Manga,
    modifier: Modifier = Modifier,
    onClick: (manga: Manga) -> Unit
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(manga.relationships.find {
            it.type == "cover_art"
        }?.attributes?.fileName?.let {
            "https://uploads.mangadex.org/covers/${manga.id}/$it"
        })
    ).state

    Card (
        modifier = modifier.padding(16.dp),
        onClick = {
            onClick(manga)
        }
    ) {
        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = modifier
                    .width(((200 / 3) * 2).dp)
                    .height(200.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                modifier = modifier
                    .width(((200 / 3) * 2).dp)
                    .height(200.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                painter = imageState.painter,
                contentDescription = manga.attributes.title.en,
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = manga.attributes.title.en ?: "Title not found.",
            modifier = modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun MangaTextCard(
    manga: Manga,
    modifier: Modifier = Modifier,
    onClick: (manga: Manga) -> Unit
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(manga.relationships.find {
            it.type == "cover_art"
        }?.attributes?.fileName?.let {
            "https://uploads.mangadex.org/covers/${manga.id}/$it"
        })
    ).state

    val iconSize = 20

    Card (
        modifier = modifier,
        onClick = {
            onClick(manga)
        }
    ) {
        Row {
            if (imageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .width(((100 / 3) * 2).dp)
                        .height(100.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .width(((100 / 3) * 2).dp)
                        .height(100.dp)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    painter = imageState.painter,
                    contentDescription = manga.attributes.title.en,
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    text = manga.attributes.title.en ?: "Title not found.",
                    modifier = modifier
                        .padding(5.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
                Row (
                    modifier = modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.star_outline),
                            contentDescription = "Rating",
                            modifier = modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Ratings
                            text = "10.0",
                            modifier = modifier.padding(2.dp),
                        )

                    }
                    VerticalDivider(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.height(iconSize.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.bookmark_outline),
                            contentDescription = "Follows",
                            modifier = modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Follows
                            text = "0",
                            modifier = modifier.padding(2.dp),
                        )

                    }
                    VerticalDivider(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.height(iconSize.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.eye_outline),
                            contentDescription = "Views",
                            modifier = modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Views
                            text = "N/A",
                            modifier = modifier.padding(2.dp),
                        )
                    }
                    VerticalDivider(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.height(iconSize.dp))
                    Row {
                        Image(
                            painter = painterResource(R.drawable.chatbox_outline),
                            contentDescription = "Comments",
                            modifier = modifier
                                .size(iconSize.dp)
                                .padding(2.dp),
                        )
                        Text(
                            //Comments
                            text = "0",
                            modifier = modifier.padding(2.dp),
                        )
                    }
                }
                Row {
                    LazyRow {
                        manga.attributes.tags.forEach { tag ->
                            item {
                                TagCard(tag = tag)
                            }
                        }
                    }
                }
            }
        }
        Text(
            text = manga.attributes.description?.en ?: stringResource(R.string.no_description_available),
            modifier = modifier.padding(5.dp),
        )
    }
}

@Composable
fun TagCard(tag: Tag) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier.padding(2.dp)
    ) {
        Text(
            text = tag.attributes.name.en,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )
    }
}

/**
 * {
 *       "id": "035fda6c-e843-4303-8e9a-562f8f697d50",
 *       "type": "manga",
 *       "attributes": {
 *         "title": {
 *           "en": "THE iDOLM@STER Cinderella Girls - DereMas Battle Royale (Doujinshi)"
 *         },
 *         "altTitles": [
 *           {
 *             "ja": "デレマスバトルロワイアル (アイドルマスターシンデレラガールズ)"
 *           },
 *           {
 *             "ja-ro": "DereMas Battle Royale (THE iDOLM@STER Cinderella Girls)"
 *           },
 *           {
 *             "ja-ro": "Deremasu Battle Royale (THE iDOLM@STER Cinderella Girls)"
 *           },
 *           {
 *             "ja-ro": "DereBato"
 *           },
 *           {
 *             "en": "The Idolmaster Cinderella Girls - Battle Royale (Doujinshi)"
 *           }
 *         ],
 *         "description": {
 *           "en": "30 popular idols from The Idolmaster Cinderella Girls are gathered on a remote island by a mysterious management. They must survive and fight each other in order to survive and escape from the island."
 *         },
 *         "isLocked": false,
 *         "links": {
 *           "raw": "https://twitter.com/Battle_oekaki"
 *         },
 *         "originalLanguage": "ja",
 *         "lastVolume": "",
 *         "lastChapter": "",
 *         "publicationDemographic": null,
 *         "status": "ongoing",
 *         "year": 2018,
 *         "contentRating": "safe",
 *         "tags": [
 *           {
 *             "id": "07251805-a27e-4d59-b488-f0bfbec15168",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Thriller"
 *               },
 *               "description": {},
 *               "group": "genre",
 *               "version": 1
 *             },
 *             "relationships": []
 *           },
 *           {
 *             "id": "391b0423-d847-456f-aff0-8b0cfc03066b",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Action"
 *               },
 *               "description": {},
 *               "group": "genre",
 *               "version": 1
 *             },
 *             "relationships": []
 *           },
 *           {
 *             "id": "5fff9cde-849c-4d78-aab0-0d52b2ee1d25",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Survival"
 *               },
 *               "description": {},
 *               "group": "theme",
 *               "version": 1
 *             },
 *             "relationships": []
 *           },
 *           {
 *             "id": "b13b2a48-c720-44a9-9c77-39c9979373fb",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Doujinshi"
 *               },
 *               "description": {},
 *               "group": "format",
 *               "version": 1
 *             },
 *             "relationships": []
 *           },
 *           {
 *             "id": "b29d6a3d-1569-4e7a-8caf-7557bc92cd5d",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Gore"
 *               },
 *               "description": {},
 *               "group": "content",
 *               "version": 1
 *             },
 *             "relationships": []
 *           },
 *           {
 *             "id": "e197df38-d0e7-43b5-9b09-2842d0c326dd",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Web Comic"
 *               },
 *               "description": {},
 *               "group": "format",
 *               "version": 1
 *             },
 *             "relationships": []
 *           },
 *           {
 *             "id": "ee968100-4191-4968-93d3-f82d72be7e46",
 *             "type": "tag",
 *             "attributes": {
 *               "name": {
 *                 "en": "Mystery"
 *               },
 *               "description": {},
 *               "group": "genre",
 *               "version": 1
 *             },
 *             "relationships": []
 *           }
 *         ],
 *         "state": "published",
 *         "chapterNumbersResetOnNewVolume": false,
 *         "createdAt": "2024-10-28T05:57:12+00:00",
 *         "updatedAt": "2024-10-29T14:58:49+00:00",
 *         "version": 8,
 *         "availableTranslatedLanguages": [
 *           "id"
 *         ],
 *         "latestUploadedChapter": "115be311-2158-45ab-8194-26785e8fcb78"
 *       },
 *       "relationships": [
 *         {
 *           "id": "dee720cf-4974-414e-991d-bd01a4d66fb7",
 *           "type": "author",
 *           "attributes": {
 *             "name": "Shiranai (Battle_oekaki)",
 *             "imageUrl": null,
 *             "biography": {
 *               "en": "**Native name**: 知らない\n\n**Alt. names**:\nBattle_oekaki"
 *             },
 *             "twitter": "https://twitter.com/Battle_oekaki",
 *             "pixiv": "https://www.pixiv.net/users/63634715",
 *             "melonBook": null,
 *             "fanBox": null,
 *             "booth": null,
 *             "namicomi": null,
 *             "nicoVideo": null,
 *             "skeb": "https://skeb.jp/@Battle_oekaki",
 *             "fantia": null,
 *             "tumblr": null,
 *             "youtube": null,
 *             "weibo": null,
 *             "naver": null,
 *             "website": null,
 *             "createdAt": "2024-10-28T06:02:55+00:00",
 *             "updatedAt": "2024-10-29T04:07:02+00:00",
 *             "version": 3
 *           }
 *         },
 *         {
 *           "id": "dee720cf-4974-414e-991d-bd01a4d66fb7",
 *           "type": "artist",
 *           "attributes": {
 *             "name": "Shiranai (Battle_oekaki)",
 *             "imageUrl": null,
 *             "biography": {
 *               "en": "**Native name**: 知らない\n\n**Alt. names**:\nBattle_oekaki"
 *             },
 *             "twitter": "https://twitter.com/Battle_oekaki",
 *             "pixiv": "https://www.pixiv.net/users/63634715",
 *             "melonBook": null,
 *             "fanBox": null,
 *             "booth": null,
 *             "namicomi": null,
 *             "nicoVideo": null,
 *             "skeb": "https://skeb.jp/@Battle_oekaki",
 *             "fantia": null,
 *             "tumblr": null,
 *             "youtube": null,
 *             "weibo": null,
 *             "naver": null,
 *             "website": null,
 *             "createdAt": "2024-10-28T06:02:55+00:00",
 *             "updatedAt": "2024-10-29T04:07:02+00:00",
 *             "version": 3
 *           }
 *         },
 *         {
 *           "id": "ba79c2c9-858c-4c05-80c1-acb0f2f4c540",
 *           "type": "cover_art",
 *           "attributes": {
 *             "description": "",
 *             "volume": null,
 *             "fileName": "c1bdbf28-d346-438c-81f8-3d3f0ed3d30e.jpg",
 *             "locale": "ja",
 *             "createdAt": "2024-10-28T05:57:13+00:00",
 *             "updatedAt": "2024-10-28T05:57:13+00:00",
 *             "version": 1
 *           }
 *         },
 *         {
 *           "id": "fa71bf61-432d-4dcc-8e53-7f2d96fa2cb5",
 *           "type": "creator",
 *           "attributes": {
 *             "username": "Miharu-kun",
 *             "roles": [
 *               "ROLE_USER"
 *             ],
 *             "version": 2
 *           }
 *         }
 *       ]
 *     }
 */

//@Preview
//@Composable
//fun MangaImageCardPreview() {
//    var manga = Manga(
//        id = "035fda6c-e843-4303-8e9a-562f8f697d50",
//        type = "manga",
//        attributes = Attributes(
//
//        )
//    )
//}