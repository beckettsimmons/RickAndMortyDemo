package ca.example.rickandmorty.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Direct API episode mapping.
 *
 * In a more complete app, this API mapping would be specific to the Webservice and a more generic
 * domain entity would be setup here.
 */
@Serializable
data class Episode(
    val id: Int,
    val name: String,
    @SerialName("air_date")
    val airDate: String,
    val episode: String,
)