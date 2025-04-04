package de.sortingfarmer.manju.dataClass.client

data class Client(
    val attributes: Attributes,
    val id: String,
    val relationships: List<Relationship>,
    val type: String,
)