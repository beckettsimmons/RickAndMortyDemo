package ca.example.rickandmorty.entities

/**
 * Simple structure to track success and error states.
 *
 * In most cases, there is no need to access any internal properties directly,
 * instead use the "on" methods to unwrap the internal state.
 */
class Resource<T> private constructor(
    val isSuccessful: Boolean,
    val value: T?,
    val message: String?,
) {
    val isError get() = !isSuccessful
    /**
     * If successful, calls [block] with value and returns it's result,
     * otherwise returns error untouched.
     */
    fun <R> onSuccess(block: (T) -> Resource<R>): Resource<R> {
        return if(isSuccessful) block(value!!) else error(message!!)
    }

    /**
     * If unsuccessful, calls [block] with message and returns it's result,
     * otherwise returns success untouched.
     */
    fun onError(block: (String) -> Resource<T>): Resource<T> {
        return if(isError) error(block(message!!)) else success(value!!)
    }

    /** Call [block] with successful value when successful and returns this. */
    fun onSuccessAlso(block: (T) -> Unit): Resource<T> = this.also {
        if(isSuccessful) block(value!!)
    }

    /** If unsuccessful, call [block] with error message and returns this. */
    fun onErrorAlso(block: (String) -> Unit): Resource<T> = this.also {
        if(isError) block(message!!)
    }

    /** If successful, call [block] and wrap it in a success. */
    fun <R> onSuccessWrap(block: (T) -> R): Resource<R> {
        return if(isSuccessful) success(block(value!!)) else error(message!!)
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Resource<*> ->
                if(isSuccessful) other.isSuccessful && value == other.value
                else other.isError && message == other.message
            else -> false
        }
    }

    companion object {
        /** Create a new successful resource. */
        fun <T> success(value: T) = Resource(true, value, null)
        /** Create a new failed resource. */
        fun <T> error(error: String) = Resource<T>(false, null, error)
    }
}