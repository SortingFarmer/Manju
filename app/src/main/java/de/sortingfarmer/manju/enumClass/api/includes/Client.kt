package de.sortingfarmer.manju.enumClass.api.includes

enum class Client(val value: String) {
    CREATOR("creator");

    override fun toString(): String {
        return value
    }
}