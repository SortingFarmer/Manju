package de.sortingfarmer.manju.dataClass.manga

data class Attributes(
    val altTitles: List<TLText>,
    val availableTranslatedLanguages: List<String?>,
    val chapterNumbersResetOnNewVolume: Boolean,
    val contentRating: String,
    val createdAt: String,
    val description: TLText?,
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
    val title: TLText,
    val updatedAt: String,
    val version: Int,
    val year: Int?,
)
