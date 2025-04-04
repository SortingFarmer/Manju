package de.sortingfarmer.manju.enumClass.api

enum class Order(val value: String) {
    ASC("asc"),
    DESC("desc");

    override fun toString(): String {
        return value
    }
}