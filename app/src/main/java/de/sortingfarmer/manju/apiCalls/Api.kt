package de.sortingfarmer.manju.apiCalls

import de.sortingfarmer.manju.dataClass.apiResult.GET.GetManga
import de.sortingfarmer.manju.dataClass.apiResult.GET.GetMangas
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @Deprecated("Use manga category specific api calls instead")
    @GET("/manga")
    suspend fun getManga(
        @Query(value = "includes[]") includes: List<String>? = listOf(
            "author",
            "artist",
            "cover_art"
        ),
        @Query(value = "limit") limit: Int? = 10,
        @Query(value = "offset") offset: Int? = 0,
        @Query(value = "title") title: String? = null,
        @Query(value = "year") year: Int? = null,
        @Query(value = "authorOrArtist") authorOrArtist: String? = null,
        @Query(value = "authors[]") authors: List<String>? = null,
        @Query(value = "artists[]") artists: List<String>? = null,
        @Query(value = "availableTranslatedLanguages[]") availableTranslatedLanguages: List<String>? = null,
        @Query(value = "includedTags[]") includedTags: List<String>? = null,
        @Query(value = "excludedTags[]") excludedTags: List<String>? = null,
        @Query(value = "includedTagsMode") includedTagsMode: String? = "AND",
        @Query(value = "excludedTagsMode") excludedTagsMode: String? = "OR",
        @Query(value = "status[]") status: List<String>? = null,
        @Query(value = "originalLanguage[]") originalLanguage: List<String>? = null,
        @Query(value = "excludedOriginalLanguage[]") excludedOriginalLanguage: List<String>? = null,
        @Query(value = "publicationDemographic[]") publicationDemographic: List<String>? = null,
        @Query(value = "ids[]") ids: List<String>? = null,
        @Query(value = "contentRating[]") contentRating: List<String>? = null,
        @Query(value = "createdAtSince") createdAtSince: String? = null,
        @Query(value = "updatedAtSince") updatedAtSince: String? = null,
        @Query(value = "order[title]") orderTitle: String? = null,
        @Query(value = "order[year]") orderYear: String? = null,
        @Query(value = "order[createdAt]") orderCreatedAt: String? = null,
        @Query(value = "order[updatedAt]") orderUpdatedAt: String? = null,
        @Query(value = "order[latestUploadedChapter]") orderLatestUploadedChapter: String? = "desc",
        @Query(value = "order[followedCount]") orderFollowedCount: String? = null,
        @Query(value = "order[relevance]") orderRelevance: String? = null,
        @Query(value = "order[rating]") orderRating: String? = null,
        @Query(value = "hasAvailableChapters") hasAvailableChapters: Boolean? = null,
        @Query(value = "group") group: String? = null,
    ): GetMangas

    @Deprecated("Use manga category specific api calls instead")
    @GET("/manga/{id}")
    suspend fun getMangaById(
        @Path(value = "id") id: String,
        @Query(value = "includes[]") includes: List<String>? = listOf(
            "author",
            "artist",
            "cover_art"
        ),
    ): GetManga

}