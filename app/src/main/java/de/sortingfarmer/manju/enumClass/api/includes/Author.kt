package de.sortingfarmer.manju.enumClass.api.includes

enum class Author(val value: String) {
    MANGA("manga");

    override fun toString(): String {
        return value
    }
}