package de.sortingfarmer.manju.dataClass.apiResult.GET

data class GetAuthCheck(
    val result: String,
    val isAuthenticated: Boolean,
    val roles: List<String>,
    val permissions: List<String>,
)
