package com.example.manju.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.manju.dataClass.manga.AltTitle
import com.example.manju.dataClass.manga.Attributes
import com.example.manju.dataClass.manga.AttributesX
import com.example.manju.dataClass.manga.AttributesXX
import com.example.manju.dataClass.manga.Biography
import com.example.manju.dataClass.manga.Description
import com.example.manju.dataClass.manga.Links
import com.example.manju.dataClass.manga.Manga
import com.example.manju.dataClass.manga.Name
import com.example.manju.dataClass.manga.Relationship
import com.example.manju.dataClass.manga.Tag
import com.example.manju.dataClass.manga.Title
import kotlin.math.roundToInt

@Composable
fun DisplayImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    circleCrop: Boolean = false
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .apply {
                crossfade(true)
                size(Size.ORIGINAL) // Set the size to original
                if (circleCrop) {
                    transformations(CircleCropTransformation())
                }
            }
            .build()
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }

            is AsyncImagePainter.State.Error -> {
                Text(text = "Error loading image")
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = if (circleCrop) Modifier.clip(RectangleShape) else Modifier
                )
            }
        }
    }
}

val shotgunBoy = Manga(
    id = "e8c8bc87-0b71-4893-9940-3fbd4bfd386b",
    type = "manga",
    attributes = Attributes(
        title = Title(
            en = "Shotgun Boy",
            ja = null,
            `ja-ro` = null,
            zh = null
        ),
        altTitles = listOf(
            AltTitle(
                NULL = null,
                ar = null,
                az = null,
                be = null,
                bg = null,
                bn = null,
                ca = null,
                cs = null,
                cv = null,
                da = null,
                de = null,
                el = null,
                en = null,
                eo = null,
                es = null,
                `es-la` = null,
                et = null,
                eu = null,
                fa = null,
                fi = null,
                fr = null,
                ga = null,
                he = null,
                hi = null,
                hr = null,
                hu = null,
                id = null,
                it = null,
                ja = null,
                `ja-ro` = null,
                jv = null,
                ka = null,
                kk = null,
                ko = "엽총소년",
                `ko-ro` = null,
                la = null,
                lt = null,
                mn = null,
                ms = null,
                my = null,
                ne = null,
                nl = null,
                no = null,
                pl = "Chłopak ze strzelbą",
                pt = null,
                `pt-br` = "Garoto da Espingarda",
                ro = null,
                ru = "Мальчик с ружьём",
                sk = null,
                sq = null,
                sr = null,
                sv = null,
                ta = null,
                te = null,
                th = null,
                tl = null,
                tr = null,
                uk = null,
                ur = null,
                uz = null,
                vi = null,
                zh = null,
                `zh-hk` = null,
                `zh-ro` = null
            )
        ),
        description = Description(
            en = "While running for his life in the woods, bullying victim Gyuhwan stumbles upon a shotgun and just enough shells for his classmates. It’s a recipe for disaster. But fate takes an unexpected turn when he returns to find his classmates under attack by brain-eating creatures. Now, the only thing that stands in the way of their ravenous feast is a boy with a shotgun.",
            ar = null,
            az = null,
            ca = null,
            cs = null,
            cv = null,
            da = null,
            de = null,
            el = null,
            es = null,
            `es-la` = null,
            eu = null,
            fa = null,
            fi = null,
            fr = null,
            gl = null,
            he = null,
            hi = null,
            hr = null,
            hu = null,
            id = null,
            it = null,
            ja = null,
            ka = null,
            kk = null,
            ko = null,
            lt = null,
            mn = null,
            ne = null,
            no = null,
            pl = null,
            pt = null,
            `pt-br` = null,
            ro = null,
            ru = null,
            sk = null,
            sr = null,
            th = null,
            tl = null,
            tr = null,
            uk = null,
            ur = null,
            uz = null,
            vi = null,
            zh = null,
            `zh-hk` = null
        ),
        isLocked = false,
        links = Links(
            al = "130229",
            amz = null,
            ap = "shotgun-boy",
            bw = null,
            cdj = null,
            dj = null,
            ebj = null,
            engtl = "https://www.webtoons.com/en/thriller/shotgun-boy/list?title_no=2534",
            kt = null,
            mal = "146751",
            mu = "178191",
            nu = null,
            raw = "https://comic.naver.com/webtoon/list.nhn?titleId=759925"
        ),
        originalLanguage = "ko",
        lastVolume = "",
        lastChapter = "68",
        publicationDemographic = null,
        status = "completed",
        year = 2021,
        contentRating = "safe",
        tags = listOf(
            Tag(
                id = "07251805-a27e-4d59-b488-f0bfbec15168",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Thriller"),
                    description = null,
                    group = "genre",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "256c8bd9-4904-4360-bf4f-508a76d67183",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Sci-Fi"),
                    description = null,
                    group = "genre",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "36fd93ea-e8b8-445e-b836-358f02b3d33d",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Monsters"),
                    description = null,
                    group = "theme",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "391b0423-d847-456f-aff0-8b0cfc03066b",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Action"),
                    description = null,
                    group = "genre",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "3b60b75c-a2d7-4860-ab56-05f391bb889c",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Psychological"),
                    description = null,
                    group = "genre",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "3e2b8dae-350e-4ab8-a8ce-016e844b9f0d",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Long Strip"),
                    description = null,
                    group = "format",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "5fff9cde-849c-4d78-aab0-0d52b2ee1d25",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Survival"),
                    description = null,
                    group = "theme",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "9467335a-1b83-4497-9231-765337a00b96",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Post-Apocalyptic"),
                    description = null,
                    group = "theme",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "b29d6a3d-1569-4e7a-8caf-7557bc92cd5d",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Gore"),
                    description = null,
                    group = "content",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "b9af3a63-f058-46de-a9a0-e0c13906197a",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Drama"),
                    description = null,
                    group = "genre",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "caaa44eb-cd40-4177-b930-79d3ef2afe87",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "School Life"),
                    description = null,
                    group = "theme",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "cdad7e68-1419-41dd-bdce-27753074a640",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Horror"),
                    description = null,
                    group = "genre",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "e197df38-d0e7-43b5-9b09-2842d0c326dd",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Web Comic"),
                    description = null,
                    group = "format",
                    version = 1
                ),
                relationships = emptyList()
            ),
            Tag(
                id = "f5ba408b-0e7a-484d-8d49-4e9125ac96de",
                type = "tag",
                attributes = AttributesX(
                    name = Name(en = "Full Color"),
                    description = null,
                    group = "format",
                    version = 1
                ),
                relationships = emptyList()
            )
        ),
        state = "published",
        chapterNumbersResetOnNewVolume = false,
        createdAt = "2021-03-01T03:30:09+00:00",
        updatedAt = "2023-04-29T11:21:49+00:00",
        version = 10,
        availableTranslatedLanguages = listOf("pt-br", "ru", "pl", "id", "it", "uk", "en", "fr"),
        latestUploadedChapter = "8413364b-f663-4727-97ab-811dc7359e3f"
    ),
    relationships = listOf(
        Relationship(
            id = "69a21c21-4ebd-4d34-89d7-b996b0c93011",
            type = "author",
            attributes = AttributesXX(
                biography = Biography(
                    en = "I live a pretty monotonous life going back and forth between my home and my work studio. The only joys I have in life are playing video games and watching movies or dramas. I create comics in the thriller genre, but I don't particularly like thrillers. I will always do my best. Thanks.\n\nWas a part of [Team Getname](https://mangadex.org/author/0686351f-1496-4916-9ca9-8f1d0524ea02/team-getname) with [Aruani](https://mangadex.org/author/c07766c9-7322-4175-845c-00ad618ef128/aruani)\n\n[Naver Creator Page](https://comic.naver.com/community/u/carnbykim)",
                    zh = null,
                    es = null,
                    `es-la` = null,
                    ja = null,
                    ko = null,
                    `pt-br` = null
                ),
                name = "Carnby Kim (김칸비)",
                imageUrl = null,
                twitter = null,
                pixiv = null,
                melonBook = null,
                fanBox = null,
                booth = null,
                namicomi = null,
                nicoVideo = null,
                skeb = null,
                fantia = null,
                tumblr = null,
                youtube = null,
                weibo = null,
                naver = "https://blog.naver.com/carnby",
                website = "https://www.instagram.com/carnbykim/",
                createdAt = "2021-04-19T21:59:45+00:00",
                updatedAt = "2024-10-17T03:40:25+00:00",
                version = 4,
                volume = null,
                description = null,
                fileName = null,
                locale = null
            ),
            related = null
        ),
        Relationship(
            id = "0f709d02-1c54-4f3c-9faf-a408d1bc0d64",
            type = "artist",
            attributes = AttributesXX(
                biography = null,
                name = "Hong Pil",
                imageUrl = null,
                twitter = null,
                pixiv = null,
                melonBook = null,
                fanBox = null,
                booth = null,
                namicomi = null,
                nicoVideo = null,
                skeb = null,
                fantia = null,
                tumblr = null,
                youtube = null,
                weibo = null,
                naver = null,
                website = null,
                createdAt = "2021-04-19T21:59:45+00:00",
                updatedAt = "2021-04-19T21:59:45+00:00",
                version = 1,
                volume = null,
                description = null,
                fileName = null,
                locale = null
            ),
            related = null
        ),
        Relationship(
            id = "8b784180-3619-4fbc-a517-097d0b34eec1",
            type = "cover_art",
            attributes = AttributesXX(
                biography = null,
                name = null,
                imageUrl = null,
                twitter = null,
                pixiv = null,
                melonBook = null,
                fanBox = null,
                booth = null,
                namicomi = null,
                nicoVideo = null,
                skeb = null,
                fantia = null,
                tumblr = null,
                youtube = null,
                weibo = null,
                naver = null,
                website = null,
                createdAt = "2021-05-24T18:33:21+00:00",
                updatedAt = "2022-01-14T09:13:07+00:00",
                version = 2,
                volume = "1",
                description = null,
                fileName = "1bcef884-5af6-4f3a-8306-b2e62c9db5d2.jpg",
                locale = null
            ),
            related = null
        ),
        Relationship(
            id = "7cc37898-6669-407f-b7a6-f23a2859bc0b",
            type = "manga",
            attributes = null,
            related = "sequel"
        )
    )
)

fun getUnreadFeedCount(): Int {
    return (Math.random() * 100).roundToInt()
}