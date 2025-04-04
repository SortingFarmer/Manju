package de.sortingfarmer.manju.dataClass.author

import de.sortingfarmer.manju.dataClass.manga.Manga


data class Author(
    val attributes: Attributes,
    val id: String,
    val relationships: List<Manga>,
    val type: String,
)