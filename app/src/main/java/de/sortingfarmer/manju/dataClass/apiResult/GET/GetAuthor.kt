package de.sortingfarmer.manju.dataClass.apiResult.GET

import com.example.manju.dataClass.author.Author
import de.sortingfarmer.manju.dataClass.author.Author

data class GetAuthor(
    val `data`: Author,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int,
)