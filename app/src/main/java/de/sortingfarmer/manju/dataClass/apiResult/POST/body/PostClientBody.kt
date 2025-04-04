package de.sortingfarmer.manju.dataClass.apiResult.POST.body

data class PostClientBody(
    val description: String,
    val name: String,
    val profile: String,
    val version: Int,
)