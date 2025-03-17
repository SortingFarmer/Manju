package com.example.manju.data.manga

data class Mangas(
    val `data`: List<Manga>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
)