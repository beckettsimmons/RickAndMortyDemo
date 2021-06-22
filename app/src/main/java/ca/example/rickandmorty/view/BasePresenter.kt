package ca.example.rickandmorty.view

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

/**
 * Base implementation for a multiplatform presenter.
 *
 * This base can be used for controlling the view on Android, iOS, and web.
 * It also provides a coroutine scope that will cancel all children when the
 * view gets detached.
 */
abstract class BasePresenter<T: View> {
    var view: T? = null
    var presenterScope: CoroutineScope? = null

    /** Called when the view is first attached. */
    abstract fun onViewAttached(view: T?)

    /** Call this method once the view has finished setup and we're ready to load data. */
    fun attachView(view: T) {
        this.view = view
        this.presenterScope = CoroutineScope(Dispatchers.Main + Job())
        onViewAttached(view)
    }

    /** Be sure to call this method when the view is destroyed to avoid dangling refernces. */
    fun detachView() {
        view = null
        presenterScope?.cancel()
        presenterScope = null
    }
}