package com.example.manju.dataClass.apiResult.GET

import com.example.manju.dataClass.manga.Manga

data class GetManga(
    val data: Manga,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
)