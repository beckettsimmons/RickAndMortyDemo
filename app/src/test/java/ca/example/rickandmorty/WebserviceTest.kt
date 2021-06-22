package ca.example.rickandmorty

import ca.example.rickandmorty.data.Webservice
import ca.example.rickandmorty.entities.Character
import ca.example.rickandmorty.data.Webservice.ListResponse
import ca.example.rickandmorty.entities.Location
import ca.example.rickandmorty.entities.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class WebserviceTest {
    private val webservice = Webservice()

    @Test
    fun `test character`() = runBlocking {
        webservice.getCharacter(1).assertSuccess { response ->
            val expected = Character(
                id = 1,
                name = "Rick Sanchez",
                species = "Human",
                gender = "Male",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                location = Character.Location("Earth (Replacement Dimension)"),
                origin = Character.Location("Earth (C-137)"),
            )
            assertEquals(expected, response)
        }
    }

    @Test
    fun `test characters`() = runBlocking {
        webservice.getCharacters().assertSuccess { response ->
            val characters = response.results
            webservice.getCharacter(characters.first().id).assertSuccess { first ->
                assertEquals(characters.first(), first)
            }
            webservice.getCharacter(characters.last().id).assertSuccess { last ->
                assertEquals(characters.last(), last)
            }

            val expected = ListResponse.Info(
                count = 671,
                pages = 34,
                next = "https://rickandmortyapi.com/api/character?page=2",
                prev = null,
            )
            assertEquals(expected, response.info)
        }
    }

    @Test
    fun `test locations`() {
        TODO("Finish testing all other methods in the Webservice.")
    }
}

/** Assert a successful resource and then call [block]. */
inline fun <T> Resource<T>.assertSuccess(block: (T) -> Unit) {
    if(isSuccessful) block(value!!)
    else fail("Resource was expected to be successful but had error $message")
}