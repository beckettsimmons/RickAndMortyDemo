package ca.example.rickandmorty

import ca.example.rickandmorty.entities.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ResourceTest {
    @Test
    fun `test equals`() {
        assertEquals(Resource.success(10), Resource.success(10))
        assertEquals(Resource.error<Int>("dummy error"), Resource.error<Int>("dummy error"))
        assertNotEquals(Resource.success(20), Resource.success(10))
        assertNotEquals(Resource.error<Int>("dummy error"), Resource.error<Int>("foo"))
        assertNotEquals(Resource.success(10), Resource.error<Int>("foo"))
    }

    @Test
    fun `test onSuccess`() {
        val actual = Resource.success(10).onSuccess {
            Resource.success(it * 2)
        }.onError {
            Resource.error("Failed")
        }
        assertEquals(Resource.success(20), actual)
    }

    @Test
    fun `test onSuccessAlso`() {
        var sum = 0
        Resource.success(10)
            .onSuccessAlso { sum += it }
            .onSuccessAlso { sum += it }
        assertEquals(20, sum)
    }

    @Test
    fun `test onError`() {
        TODO("Finish testing all Resource methods")
    }
}