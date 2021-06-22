package ca.example.rickandmorty.view

import ca.example.rickandmorty.data.DataRepository
import kotlinx.coroutines.launch

/** Presenter for a list of episodes. */
class EpisodesPresenter(
    private val repo: DataRepository,
): BasePresenter<EpisodesView>() {
    override fun onViewAttached(view: EpisodesView?) {
        presenterScope?.launch {
            view?.showLoading()
            repo.getEpisodes().onSuccessAlso {
                view?.showEpisodes(it)
            }.onErrorAlso {
                view?.showMessage("Error Loading Locations", it)
            }
            view?.hideLoading()
        }
    }
}