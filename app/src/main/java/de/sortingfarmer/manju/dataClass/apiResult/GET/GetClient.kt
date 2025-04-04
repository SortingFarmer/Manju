package de.sortingfarmer.manju.dataClass.apiResult.GET

import com.example.manju.dataClass.client.Client
import de.sortingfarmer.manju.dataClass.client.Client

data class GetClient(
    val `data`: Client,
    val response: String,
    val result: String,
)