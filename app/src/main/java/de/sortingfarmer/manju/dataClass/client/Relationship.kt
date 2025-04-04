package de.sortingfarmer.manju.dataClass.client

data class Relationship(
    val attributes: Attributes?,
    val id: String,
    val related: String,
    val type: String,
)