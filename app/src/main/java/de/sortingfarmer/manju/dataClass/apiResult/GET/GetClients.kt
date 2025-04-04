package de.sortingfarmer.manju.dataClass.apiResult.GET

import com.example.manju.dataClass.client.Client
import de.sortingfarmer.manju.dataClass.client.Client

data class GetClients(
    val `data`: List<Client>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int,
)