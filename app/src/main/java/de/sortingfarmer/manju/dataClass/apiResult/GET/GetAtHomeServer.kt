package de.sortingfarmer.manju.dataClass.apiResult.GET

import com.example.manju.dataClass.AtHomeServerChapter
import de.sortingfarmer.manju.dataClass.AtHomeServerChapter

data class GetAtHomeServer(
    val baseUrl: String,
    val chapter: AtHomeServerChapter,
    val result: String,
)