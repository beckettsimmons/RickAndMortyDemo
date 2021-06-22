package ca.example.rickandmorty.entities

import kotlinx.serialization.Serializable

/**
 * Direct API character mapping.
 *
 * In a more complete app, this API mapping would be specific to the Webservice and a more generic
 * domain entity would be setup here.
 */
@Serializable
data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val image: String,
    val location: Location,
    val origin: Location,
) {
    @Serializable
    data class Location(
        val name: String
    )
}