package ca.example.rickandmorty.data

import ca.example.rickandmorty.entities.Character
import ca.example.rickandmorty.entities.Episode
import ca.example.rickandmorty.entities.Location
import ca.example.rickandmorty.entities.Resource
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/** Low-level Rick and Morty API implementation. */
class Webservice(
    private val client: HttpClient = HttpClient(OkHttp),
    val baseUrl: String = "https://rickandmortyapi.com/api",
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    /** Used to parse any time of API list response. */
    @Serializable
    data class ListResponse<T>(
        val info: Info,
        val results: List<T>,
    ) {
        @Serializable
        data class Info(
            val count: Int,
            val pages: Int,
            val next: String?,
            val prev: String?,
        )
    }

    suspend fun getCharacters(): Resource<ListResponse<Character>> {
        return get("/character").onSuccessWrap { response ->
            println(response)
            json.decodeFromString(response)
        }
    }

    suspend fun getCharacter(id: Int): Resource<Character> {
        return get("/character/$id").onSuccessWrap { response ->
            json.decodeFromString(response)
        }
    }

    suspend fun getLocations(): Resource<ListResponse<Location>> {
        return get("/location").onSuccessWrap { response ->
            json.decodeFromString(response)
        }
    }

    suspend fun getLocation(id: Int): Resource<Location> {
        return get("/location/$id").onSuccessWrap { response ->
            json.decodeFromString(response)
        }
    }

    suspend fun getEpisodes(): Resource<ListResponse<Episode>> {
        return get("/episode").onSuccessWrap { response ->
            json.decodeFromString(response)
        }
    }

    suspend fun getEpisode(id: Int): Resource<Episode> {
        return get("/episode/$id").onSuccessWrap { response ->
            json.decodeFromString(response)
        }
    }

    /**
     * Simple GET implementation.
     *
     * In the future, this method can  catch all of the specific ktor
     * errors and encode them as needed.
     */
    suspend fun get(endpoint: String): Resource<String> = coroutineScope {
        try {
            Resource.success(client.get(baseUrl + endpoint))
        } catch(e: Exception) {
            Resource.error(e.toString())
        }
    }
}