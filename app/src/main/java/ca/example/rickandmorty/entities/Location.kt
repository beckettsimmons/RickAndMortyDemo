package ca.example.rickandmorty.entities

import kotlinx.serialization.Serializable

/**
 * Direct API location mapping.
 *
 * In a more complete app, this API mapping would be specific to the Webservice and a more generic
 * domain entity would be setup here.
 */
@Serializable
data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)