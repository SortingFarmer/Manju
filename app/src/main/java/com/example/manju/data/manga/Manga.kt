package com.example.manju.data.manga

data class Manga(
    val attributes: Attributes,
    val id: String,
    val relationships: List<Relationship>,
    val type: String
)