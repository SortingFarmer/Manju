package de.sortingfarmer.manju.dataClass.apiResult.GET

import com.example.manju.dataClass.manga.Manga
import de.sortingfarmer.manju.dataClass.manga.Manga

data class GetMangas(
    val data: List<Manga>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int,
)