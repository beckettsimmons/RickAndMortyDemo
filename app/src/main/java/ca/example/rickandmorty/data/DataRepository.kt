package ca.example.rickandmorty.data

import ca.example.rickandmorty.entities.Character
import ca.example.rickandmorty.entities.Episode
import ca.example.rickandmorty.entities.Location
import ca.example.rickandmorty.entities.Resource

/**
 * Caches the data from the data source.
 *
 * For the purpose of this example app, we will just keep an in-memory cache so that
 * data will survive orientation changes.
 *
 * In the future, this class can also use a custom CoroutineScope so that it can preload data
 * in the background independent of a particular fragment lifecycle; avoiding most progress dialogs.
 */
class DataRepository(private val source: Webservice) {
    private val charactersCache: MutableMap<String, List<Character>> = HashMap()
    private val characterCache: MutableMap<Int, Character> = HashMap()
    private val locationsCache: MutableMap<String, List<Location>> = HashMap()
    private val episodesCache: MutableMap<String, List<Episode>> = HashMap()

    suspend fun getCharacter(id: Int): Resource<Character> {
        return usingCache(characterCache, id) {
            source.getCharacter(id)
        }
    }

    suspend fun getCharacters(): Resource<List<Character>> {
        // For now, just load the first 20 characters.
        return usingCache(charactersCache, DEFAULT) {
            source.getCharacters().onSuccessWrap { response ->
                response.results.also { characters ->
                    // Also update the character cache
                    characters.forEach { characterCache[it.id] = it }
                }

            }
        }
    }

    suspend fun getLocations(): Resource<List<Location>> {
        return usingCache(locationsCache, DEFAULT) {
            source.getLocations().onSuccessWrap { response ->
                response.results
            }
        }
    }

    suspend fun getEpisodes(): Resource<List<Episode>> {
        return usingCache(episodesCache, DEFAULT) {
            source.getEpisodes().onSuccessWrap { response ->
                response.results
            }
        }
    }

    fun invalidateCharacters() {
        characterCache.clear()
        charactersCache.clear()
    }

    companion object {
        const val DEFAULT = "default"
    }
}

/** Check cache for key before making a longer request. Automatically adds successful requests to cache. */
inline fun <K, V> usingCache(cache: MutableMap<K, V>, key: K, block: () -> Resource<V>): Resource<V> {
    return if(cache.containsKey(key)) {
        Resource.success(cache.getValue(key))
    } else {
        block().onSuccessAlso {
            cache[key] = it
        }
    }
}