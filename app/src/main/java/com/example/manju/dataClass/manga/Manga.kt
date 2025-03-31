package com.example.manju.dataClass.manga

data class Manga(
    val attributes: Attributes,
    val id: String,
    val relationships: List<Relationship>,
    val type: String
)

data class AltTitle(
    val NULL: String?,
    val ar: String?,
    val az: String?,
    val be: String?,
    val bg: String?,
    val bn: String?,
    val ca: String?,
    val cs: String?,
    val cv: String?,
    val da: String?,
    val de: String?,
    val el: String?,
    val en: String?,
    val eo: String?,
    val es: String?,
    val `es-la`: String?,
    val et: String?,
    val eu: String?,
    val fa: String?,
    val fi: String?,
    val fr: String?,
    val ga: String?,
    val he: String?,
    val hi: String?,
    val hr: String?,
    val hu: String?,
    val id: String?,
    val `it`: String?,
    val ja: String?,
    val `ja-ro`: String?,
    val jv: String?,
    val ka: String?,
    val kk: String?,
    val ko: String?,
    val `ko-ro`: String?,
    val la: String?,
    val lt: String?,
    val mn: String?,
    val ms: String?,
    val my: String?,
    val ne: String?,
    val nl: String?,
    val no: String?,
    val pl: String?,
    val pt: String?,
    val `pt-br`: String?,
    val ro: String?,
    val ru: String?,
    val sk: String?,
    val sq: String?,
    val sr: String?,
    val sv: String?,
    val ta: String?,
    val te: String?,
    val th: String?,
    val tl: String?,
    val tr: String?,
    val uk: String?,
    val ur: String?,
    val uz: String?,
    val vi: String?,
    val zh: String?,
    val `zh-hk`: String?,
    val `zh-ro`: String?
)

data class Attributes(
    val altTitles: List<AltTitle>,
    val availableTranslatedLanguages: List<String?>,
    val chapterNumbersResetOnNewVolume: Boolean,
    val contentRating: String,
    val createdAt: String,
    val description: Description?,
    val isLocked: Boolean,
    val lastChapter: String?,
    val lastVolume: String?,
    val latestUploadedChapter: String?,
    val links: Links?,
    val originalLanguage: String,
    val publicationDemographic: String?,
    val state: String,
    val status: String,
    val tags: List<Tag>,
    val title: Title,
    val updatedAt: String,
    val version: Int,
    val year: Int?
)

data class AttributesX(
    val description: DescriptionX?,
    val group: String,
    val name: Name,
    val version: Int
)

data class AttributesXX(
    val biography: Biography?,
    val booth: String?,
    val createdAt: String,
    val description: String?,
    val fanBox: String?,
    val fantia: String?,
    val fileName: String?,
    val imageUrl: Any?,
    val locale: String?,
    val melonBook: String?,
    val name: String?,
    val namicomi: String?,
    val naver: String?,
    val nicoVideo: String?,
    val pixiv: String?,
    val skeb: String?,
    val tumblr: String?,
    val twitter: String?,
    val updatedAt: String,
    val version: Int,
    val volume: String?,
    val website: String?,
    val weibo: String?,
    val youtube: String?
)

data class Biography(
    val en: String?,
    val es: String?,
    val `es-la`: String?,
    val ja: String?,
    val ko: String?,
    val `pt-br`: String?,
    val zh: String?
)

data class Description(
    val ar: String?,
    val az: String?,
    val ca: String?,
    val cs: String?,
    val cv: String?,
    val da: String?,
    val de: String?,
    val el: String?,
    val en: String?,
    val es: String?,
    val `es-la`: String?,
    val eu: String?,
    val fa: String?,
    val fi: String?,
    val fr: String?,
    val gl: String?,
    val he: String?,
    val hi: String?,
    val hr: String?,
    val hu: String?,
    val id: String?,
    val `it`: String?,
    val ja: String?,
    val ka: String?,
    val kk: String?,
    val ko: String?,
    val lt: String?,
    val mn: String?,
    val ne: String?,
    val no: String?,
    val pl: String?,
    val pt: String?,
    val `pt-br`: String?,
    val ro: String?,
    val ru: String?,
    val sk: String?,
    val sr: String?,
    val th: String?,
    val tl: String?,
    val tr: String?,
    val uk: String?,
    val ur: String?,
    val uz: String?,
    val vi: String?,
    val zh: String?,
    val `zh-hk`: String?
)

class DescriptionX
data class Links(
    val al: String?,
    val amz: String?,
    val ap: String?,
    val bw: String?,
    val cdj: String?,
    val dj: String?,
    val ebj: String?,
    val engtl: String?,
    val kt: String?,
    val mal: String?,
    val mu: String?,
    val nu: String?,
    val raw: String?
)

data class Name(
    val en: String
)

data class Relationship(
    val attributes: AttributesXX?,
    val id: String,
    val related: String?,
    val type: String
)

data class Tag(
    val attributes: AttributesX,
    val id: String,
    val relationships: List<Any?>,
    val type: String
)

data class Title(
    val en: String?,
    val ja: String?,
    val `ja-ro`: String?,
    val zh: String?
)