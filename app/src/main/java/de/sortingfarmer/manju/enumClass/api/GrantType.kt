package de.sortingfarmer.manju.enumClass.api

enum class GrantType(val value: String) {
    PASSWORD("password"),
    REFRESH_TOKEN("refresh_token");

    override fun toString(): String {
        return value
    }
}