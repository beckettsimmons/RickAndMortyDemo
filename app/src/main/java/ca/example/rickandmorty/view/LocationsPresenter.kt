package ca.example.rickandmorty.view

import ca.example.rickandmorty.data.DataRepository
import kotlinx.coroutines.launch

/** Presenter for a list of locations. */
class LocationsPresenter(
    private val repo: DataRepository,
): BasePresenter<LocationsView>() {
    override fun onViewAttached(view: LocationsView?) {
        presenterScope?.launch {
            view?.showLoading()
            repo.getLocations().onSuccessAlso {
                view?.showLocations(it)
            }.onErrorAlso {
                view?.showMessage("Error Loading Locations", it)
            }
            view?.hideLoading()
        }
    }
}