package de.sortingfarmer.manju.dataClass.apiResult.POST

import de.sortingfarmer.manju.dataClass.client.Client

data class PostClient(
    val `data`: Client,
    val response: String,
    val result: String,
)