import android.annotation.SuppressLint
import android.text.format.DateUtils
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
import de.sortingfarmer.manju.openapi.models.AuthorAttributes
import de.sortingfarmer.manju.openapi.models.Chapter
import de.sortingfarmer.manju.openapi.models.ChapterAttributes
import de.sortingfarmer.manju.openapi.models.CoverAttributes
import de.sortingfarmer.manju.openapi.models.GetStatisticsChapterUuid200ResponseStatisticsValue
import de.sortingfarmer.manju.openapi.models.GetStatisticsMangaUuid200ResponseStatisticsValue
import de.sortingfarmer.manju.openapi.models.GetStatisticsMangaUuid200ResponseStatisticsValueRating
import de.sortingfarmer.manju.openapi.models.GetStatisticsMangaUuid200ResponseStatisticsValueRatingDistribution
import de.sortingfarmer.manju.openapi.models.Manga
import de.sortingfarmer.manju.openapi.models.MangaAttributes
import de.sortingfarmer.manju.openapi.models.Relationship
import de.sortingfarmer.manju.openapi.models.RelationshipAttributes
import de.sortingfarmer.manju.openapi.models.StatisticsDetailsComments
import de.sortingfarmer.manju.openapi.models.Tag
import de.sortingfarmer.manju.openapi.models.TagAttributes
import java.math.BigDecimal
import java.net.URI
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID

@Composable
fun DisplayImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    circleCrop: Boolean = false,
    onError: @Composable (() -> Unit) = { Text("Error loading image...") },
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
                onError()
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

fun getRelativeTime(isoString: String): CharSequence {
    val dateTime = OffsetDateTime.parse(isoString)
    val epochMilli = dateTime.toInstant().toEpochMilli()
    return DateUtils.getRelativeTimeSpanString(
        epochMilli,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS
    )
}

fun formatNumber(number: Int): String {
    return when {
        number >= 1_000_000 -> String.format(Locale.getDefault(), "%.1fM", number / 1_000_000.0)
        number >= 1_000 -> String.format(Locale.getDefault(), "%.1fk", number / 1_000.0)
        else -> number.toString()
    }
}

val testManga = Manga(
    id = UUID.fromString("4f9eab7d-a2b2-4ee5-9d59-6744f0df4e12"),
    type = Manga.Type.manga,
    attributes = MangaAttributes(
        title = mapOf(
            "en" to "Domestic na Kanojo"
        ),
        altTitles = listOf(
            mapOf(
                "en" to "Domestic Girlfriend"
            ),
            mapOf(
                "fr" to "Love X Dilemma"
            ),
            mapOf(
                "tr" to "Evcimen Kız Arkadaş"
            ),
            mapOf(
                "ru" to "Домекано"
            ),
            mapOf(
                "ar" to "عشيقتي من أسرتي"
            ),
            mapOf(
                "ja" to "ドメスティックな彼氏"
            ),
            mapOf(
                "zh" to "家有女友"
            ),
            mapOf(
                "ko" to "도메스틱한 그녀"
            ),
            mapOf(
                "ru" to "Домашняя девушка"
            ),
            mapOf(
                "vi" to "Bạn gái chung nhà"
            )
        ),
        description = mapOf(
            "en" to "Natsuo Fujii is in love with his teacher, Hina. Attempting to forget his feelings towards her, Natsuo goes to a mixer with his classmates where he meets an odd girl named Rui Tachibana. In a strange turn of events, Rui asks Natsuo to sneak out with her and do her a favor. To his surprise, their destination is Rui's house—and her request is for him to have sex with her. There's no love behind the act; she just wants to learn from the experience. Thinking that it might help him forget about Hina, Natsuo hesitantly agrees. After this unusual encounter Natsuo now faces a new problem. With his father remarrying, he ends up with a new pair of stepsisters; unfortunately, he knows these two girls all too well. He soon finds out his new siblings are none other than Hina and Rui! Now living with both the teacher he loves and the girl with whom he had his \"first time,\" Natsuo finds himself in an unexpected love triangle as he climbs ever closer towards adulthood.",
            "it" to "Quella notte, Natsuo perse la sua verginità. La sua partner? Si chiama Rui e l'ha incontrata appena il giorno stesso dopo la scuola. E' lei ad averlo convinto, semplicemente dicendo di voler conoscere di più riguardo il sesso. Non voleva iniziare una relazione, né le interessava di Natsuo. In seguito, Natsuo si sente in colpa per aver perso la verginità con una ragazza che non gli piace neanche particolarmente. In più, si sente come se avesse tradito la ragazza per cui ha una cotta, la sua insegnante Hina. Un particolare triangolo amoroso che comprende Natsuo, Rui e Hina avrà inizio quando scopriranno…",
            "pt" to "Hoje à noite, Natsuo perdeu a virgindade. Sua parceira? Seu nome é Rui e acabou de conhecê-lo entre amigos da escola. Ela foi a única que arquitetou essa situação, com o discurso de apenas conhecer mais sobre o sexo. Ela não queria começar um relacionamento, ela não liga pro Natsuo. No entanto, Natsuo não se sente bem por perder a virgindade com uma garota que ele não necessariamente gosta. Na verdade, isso fez com que ele sentisse que estivesse traindo sua paixão da escola: a professora Hina. Um triângulo amoroso surge entre Natsuo, Rui e Hina e tudo acaba com eles na mesma família!",
            "ru" to "Это история о трёх героях: Нацуо, Руи и Хине. Руи — молодая девушка, не желающая вступать в отношения, Нацуо — обычный ученик старшей школы, а Хина — молодая учительница. Руи, несмотря на то, что ей не интересны отношения, хочет узнать о сексе и уговаривает Нацуо лишить её девственности. После произошедшего Нацуо мучает чувство вины, так как он лишил невинности девушку, к которой не испытывает чувств. Кроме того Нацуо не может забыть и о своей симпатии к молодой учительнице Хине. Это история стара как мир — история о неразделённой любви.",
            "tr" to "Natsuo Fujii öğretmeni Hina'ya aşıktır. Ona karşı olan hislerini unutma amacıyla, Natsuo sınıf arkadaşlarıyla bir mixer partisine gidip orada Rui Tachibana isimli garip bir kız ile tanışır. Garip bir şekilde Rui, Natsuo'ya onun ile birlikte partiden kaçmasını ve ona bir iyilik yapmasını rica eder. Fakat aslında, gittikleri yer Rui'nin evidir—ve Rui'nin bahsettiği rica da onun ile cinsel ilişkiye girmesidir. Bu durumun arkasında herhangi bir aşk yoktur; Rui yalnızca deneyim elde etmek ister. Hina'yı unutmasına yardımcı olacağı düşüncesiyle, Natsu kararsızca kabul eder. Bu sıra dışı karşılaşmanın ardından, Natsuo'yu yeni bir problem karşılar. Babasının yeniden evlenmesi sonucunda, bir çift üvey kız kardeşi olur; fakat ne yazık ki, Natsuo\nbu iki kız kardeşi çok iyi tanıyordur. Hemen ardından bu kız kardeşlerin Hina ve Rui'den başkası olmadığını öğrenir. Aşık olduğu öğretmeni ve \"ilkini çaldığı\" kız ile, Natsuo kendini beklenmedik bir aşk üçgeninin ortasında bulur."
        ),
        isLocked = false,
        links = mapOf(
            "al" to "85802",
            "ap" to "domestic-girlfriend",
            "bw" to "series/18020/list",
            "kt" to "26187",
            "mu" to "110333",
            "amz" to "https://www.amazon.co.jp/gp/product/B074C7NF89/",
            "cdj" to "https://www.cdjapan.co.jp/product/NEOBK-2361120",
            "ebj" to "https://ebookjapan.yahoo.co.jp/books/247506/",
            "mal" to "70941",
            "raw" to "https://pocket.shonenmagazine.com/episode/13932016480029113059",
            "engtl" to "https://comics.inkr.com/title/182-domestic-girlfriend?utm_source=mgd"
        ),
        originalLanguage = "ja",
        lastVolume = "28",
        lastChapter = "276",
        publicationDemographic = MangaAttributes.PublicationDemographic.shounen,
        status = MangaAttributes.Status.completed,
        year = 2014,
        contentRating = MangaAttributes.ContentRating.erotica,
        tags = listOf(
            Tag(
                id = UUID.fromString("423e2eae-a7a2-4a8b-ac03-a8351462d71d"),
                type = Tag.Type.tag,
                attributes = TagAttributes(
                    name = mapOf(
                        "en" to "Romance"
                    ),
                    description = mapOf(),
                    group = TagAttributes.Group.genre,
                    version = 1
                ),
                relationships = listOf()
            ),
            Tag(
                id = UUID.fromString("5bd0e105-4481-44ca-b6e7-7544da56b1a3"),
                type = Tag.Type.tag,
                attributes = TagAttributes(
                    name = mapOf(
                        "en" to "Incest"
                    ),
                    description = mapOf(),
                    group = TagAttributes.Group.theme,
                    version = 1
                ),
                relationships = listOf()
            ),
            Tag(
                id = UUID.fromString("aafb99c1-7f60-43fa-b75f-fc9502ce29c7"),
                type = Tag.Type.tag,
                attributes = TagAttributes(
                    name = mapOf(
                        "en" to "Harem"
                    ),
                    description = mapOf(),
                    group = TagAttributes.Group.theme,
                    version = 1
                ),
                relationships = listOf()
            ),
            Tag(
                id = UUID.fromString("b9af3a63-f058-46de-a9a0-e0c13906197a"),
                type = Tag.Type.tag,
                attributes = TagAttributes(
                    name = mapOf(
                        "en" to "Drama"
                    ),
                    description = mapOf(),
                    group = TagAttributes.Group.genre,
                    version = 1
                ),
                relationships = listOf()
            ),
            Tag(
                id = UUID.fromString("caaa44eb-cd40-4177-b930-79d3ef2afe87"),
                type = Tag.Type.tag,
                attributes = TagAttributes(
                    name = mapOf(
                        "en" to "School Life"
                    ),
                    description = mapOf(),
                    group = TagAttributes.Group.theme,
                    version = 1
                ),
                relationships = listOf()
            ),
            Tag(
                id = UUID.fromString("e5301a23-ebd9-49dd-a0cb-2add944c7fe9"),
                type = Tag.Type.tag,
                attributes = TagAttributes(
                    name = mapOf(
                        "en" to "Slice of Life"
                    ),
                    description = mapOf(),
                    group = TagAttributes.Group.genre,
                    version = 1
                ),
                relationships = listOf()
            )
        ),
        state = MangaAttributes.State.published,
        chapterNumbersResetOnNewVolume = false,
        createdAt = "2018-10-06T03:49:01+00:00",
        updatedAt = "2024-12-05T08:30:21+00:00",
        version = 28,
        availableTranslatedLanguages = listOf(
            "vi",
            "es-la",
            "th",
            "pl",
            "en",
            "id",
            "fr",
            "ru",
            "pt-br",
            "ar",
            "it",
            "es"
        ),
        latestUploadedChapter = UUID.fromString("85c4351f-ffa0-4825-8c61-0937dd79ebd5"),
    ),
    relationships = listOf(
        Relationship(
            id = UUID.fromString("15f42798-fb95-4aac-a21e-d435d7f367be"),
            type = "artist",
            attributes = RelationshipAttributes(
                name = "Sasuga Kei",
                imageUrl = null,
                biography = mapOf(
                    "en" to "Real name: Sasaki Keiko. She worked as assistant of [Hinata Takeshi](https://mangadex.org/author/fef4fdfd-3dad-4fef-bcb1-1c0d78e49c72/hinata-takeshi) on [Ahiru no Sora](https://mangadex.org/title/8f3c24d3-6e15-4fea-a60f-f80cef482853/ahiru-no-sora)\n\nWas interviewed by anime/manga youtuber Gigguk:\n\n[Link](https://www.youtube.com/watch?v=V2AWjDjwM-w)\n\nSource : MangaUpdates"
                ),
                twitter = URI.create("https://twitter.com/k_sasuga"),
                pixiv = null,
                melonBook = null,
                fanBox = URI.create("https://kei-sasuga.fanbox.cc/"),
                booth = null,
                namicomi = null,
                nicoVideo = null,
                skeb = null,
                fantia = null,
                tumblr = null,
                youtube = null,
                weibo = null,
                naver = null,
                website = "https://www.instagram.com/kei_sasuga/",
                createdAt = "2021-04-19T21:59:45+00:00",
                updatedAt = "2023-03-03T13:19:27+00:00",
                version = 5
            )
        ),Relationship(
            id = UUID.fromString("15f42798-fb95-4aac-a21e-d435d7f367be"),
            type = "author",
            attributes = RelationshipAttributes(
                name = "Sasuga Kei",
                imageUrl = null,
                biography = mapOf(
                    "en" to "Real name: Sasaki Keiko. She worked as assistant of [Hinata Takeshi](https://mangadex.org/author/fef4fdfd-3dad-4fef-bcb1-1c0d78e49c72/hinata-takeshi) on [Ahiru no Sora](https://mangadex.org/title/8f3c24d3-6e15-4fea-a60f-f80cef482853/ahiru-no-sora)\n\nWas interviewed by anime/manga youtuber Gigguk:\n\n[Link](https://www.youtube.com/watch?v=V2AWjDjwM-w)\n\nSource : MangaUpdates"
                ),
                twitter = URI.create("https://twitter.com/k_sasuga"),
                pixiv = null,
                melonBook = null,
                fanBox = URI.create("https://kei-sasuga.fanbox.cc/"),
                booth = null,
                namicomi = null,
                nicoVideo = null,
                skeb = null,
                fantia = null,
                tumblr = null,
                youtube = null,
                weibo = null,
                naver = null,
                website = "https://www.instagram.com/kei_sasuga/",
                createdAt = "2021-04-19T21:59:45+00:00",
                updatedAt = "2023-03-03T13:19:27+00:00",
                version = 5
            )
        ),
        Relationship(
            id = UUID.fromString("7a00ab28-40ba-4f4e-8db1-764c6e2a62de"),
            type = "cover_art",
            attributes = RelationshipAttributes(
                description = "",
                volume = "28",
                fileName = "7129edad-f454-4b06-aee7-518a17259967.jpg",
                locale = "ja",
                createdAt = "2021-05-24T16:47:12+00:00",
                updatedAt = "2023-03-06T19:58:53+00:00",
                version = 2
            )
        )
    )
)

val testMangaStatistics = GetStatisticsMangaUuid200ResponseStatisticsValue(
    comments = StatisticsDetailsComments(
        threadId = BigDecimal("39453"),
        repliesCount = BigDecimal("414"),
    ),
    rating = GetStatisticsMangaUuid200ResponseStatisticsValueRating(
        average = BigDecimal("6.6518"),
        bayesian = BigDecimal("6.692215134660596"),
        distribution = GetStatisticsMangaUuid200ResponseStatisticsValueRatingDistribution(
            _1 = 598,
            _2 = 145,
            _3 = 139,
            _4 = 250,
            _5 = 222,
            _6 = 320,
            _7 = 431,
            _8 = 477,
            _9 = 464,
            _10 = 1170
        )
    ),
    follows = 30211
)

val testChapter = Chapter(
    id = UUID.fromString("6cad5f6f-066f-4a19-9e81-b5bb261c0558"),
    type = Chapter.Type.chapter,
    attributes = ChapterAttributes(
        volume = "1",
        chapter = "1",
        title = "I want to become an adult quickly",
        translatedLanguage = "en",
        externalUrl = null,
        publishAt = "2018-01-21T21:06:46+00:00",
        readableAt = "2018-01-21T21:06:46+00:00",
        createdAt = "2018-01-21T21:06:46+00:00",
        updatedAt = "2018-01-21T21:06:46+00:00",
        pages = 52,
        version = 1
    ),
    relationships = listOf(
        Relationship(
            id = UUID.fromString("62a656a0-7d6a-42b3-bb61-6ea2e5587509"),
            type = "scanlation_group",
            attributes = RelationshipAttributes(
                name = "Norway Scan",
                altNames = null,
                locked = false,
                website = "https://www.dropbox.com/sh/vjwerlviukmajjj/6wiyzDhGtg",
                ircServer = "irc.irchighway.net",
                ircChannel = "pocketloli",
                discord = null,
                contactEmail = null,
                description = "Norway Scan, interchangeable with \"Norway Scans\", is a scanlation group run by anonymous preferred (ap) and Volandum.  \n  \nAny opinion by Norway Scan members, including ap, does not represent the group. Each member is responsible for their opinions or actions.  \n  \nWe do NOT accept any donation. If you want to donate, please do it to our joint groups.  \n  \nanonymous preferred does NOT take any translation request or scanlation offer unless it concerns a project he's been working on. A thank-you letter with such a request, in particular, will be frowned upon. Don't even bother.  \n  \nShould you want to use ap's translation or scanlation for any language, feel free to do so \"without\" bothering asking for his approval. No credit required either. (it is recommended that you contact ap via Norwayscan at gmail.com; he might be able to share PSDs or cleans with you.)  \n  \nFreelancers whom Norway Scan is currently working with:  \nBlack Sword, BondEternal, FD2Raptor, Gecko, GodricKharg, himesaminako, Holy\\_Ca, HTML5, K-man, Kaillus, Kousora, L412D0W, Lemmieh, LoliHunterXD, MarketingReasons, Recongriffin, Sealophile, Spam Sucks, Tactless, Teras, Zekaito and Zujids  \n  \nGroups that Norway Scan has been jointing with, formerly or currently:  \nAfrodhi, C2, Café con Lenin, Ciel Scans, Death Toll Scanlations, Devil Slayer Team, DKThias, Dragon and Griffin Scans, Duralumin, Forgotten Scans, Itadakimasu-Scanlator, Lemon Scans, Loli Brigade Scans, Lovely Scans Italia, MangaIchi Scanlation Division, NEWAVE (formerly, New Wave Scanlations), NFP Scans, One Time Scans, Oyasumi Translations, Pocket Loli Scans, Renzokusei Scans, Riceballicious, S2Scans, Sacred Blade Scans, simhauu, The AG Team and Village Idiot Scanlations.  \n   \nHonorable mentions go to ex-workmates: Alad, andreaphobia, An Editor, Aoiha, Arihou, bdchee, blindbox, bluesky, cafecito, Cesc, Clueless, couchk, Derek, dikbut, Fenrill, GlassSkinned, H\\_Dogma, harshrox3, HimeMiya, iluminado, InfiniteVerisimilitude, Kallamez, khenny123, KnifeBuster, kobester11, LibraryTroll, Lvxi, Maglad, marthaurion, MaxLemon, monokuma, Nightshade\\_q8, n33t, nuggles, PervySageChuck, phaez, Purr-nyan, ranix, Silentdt, Sootopolis, SoSul, stokken, svines85, Tomlobeznokashima, Tryggve, ttahiii, and Vreiya.  \n   \nap's blacklist: al245, Cake-kun, Silentdt, and simhauu  \n  \nIf you can not find the files in the normal link above please look here.  \n  \n[url=[https://www.dropbox.com/sh/4cwdvihqgiyos0b/AABixSJqzH0UyAXDYOtqB4twa?dl=0\"]Dropbox](https://www.dropbox.com/sh/4cwdvihqgiyos0b/AABixSJqzH0UyAXDYOtqB4twa?dl=0%22]Dropbox) Archive[/url]",
                twitter = null,
                mangaUpdates = null,
                focusedLanguage = listOf("en"),
                official = false,
                verified = false,
                inactive = false,
                publishDelay = null,
                createdAt = "2021-04-19T21:45:59+00:00",
                updatedAt = "2021-04-19T21:45:59+00:00",
                version = 1
            )
        ),
        Relationship(
            id = UUID.fromString("76912a23-d4f0-4ed1-87bf-3376a5bc13f9"),
            type = "scanlation_group",
            attributes = RelationshipAttributes(
                name = "Café con Lenin",
                locked = false,
                website = null,
                ircServer = null,
                ircChannel = null,
                discord = null,
                contactEmail = null,
                description = "Inactive Group",
                twitter = null,
                mangaUpdates = null,
                official = false,
                focusedLanguage = listOf("en"),
                verified = false,
                inactive = false,
                publishDelay = null,
                createdAt = "2021-04-19T21:45:59+00:00",
                updatedAt = "2021-05-15T21:06:43+00:00",
                version = 2
            )
        ),
        Relationship(
            id = UUID.fromString("4f9eab7d-a2b2-4ee5-9d59-6744f0df4e12"),
            type = "manga"
        ),
        Relationship(
            id = UUID.fromString("4e65e334-47d7-4198-9130-683bef842ad4"),
            type = "user",
            attributes = RelationshipAttributes(
                username = "Winlux",
                roles = listOf(
                    "ROLE_MEMBER"
                ),
                version = 54
            )
        )
    )
)

val testChapterStatistics = GetStatisticsChapterUuid200ResponseStatisticsValue(
    comments = StatisticsDetailsComments(
        threadId = BigDecimal("4756728"),
        repliesCount = BigDecimal("12")
    )
)