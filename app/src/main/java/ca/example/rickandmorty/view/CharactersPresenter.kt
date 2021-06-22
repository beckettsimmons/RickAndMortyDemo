package ca.example.rickandmorty.view

import ca.example.rickandmorty.data.DataRepository
import kotlinx.coroutines.launch

/** Presenter for a list of characters. */
class CharactersPresenter(
    private val repo: DataRepository,
): BasePresenter<CharactersView>() {
    override fun onViewAttached(view: CharactersView?) {
        loadCharacters()
    }

    private fun loadCharacters() {
        presenterScope?.launch {
            view?.showLoading()
            repo.getCharacters().onSuccessAlso {
                view?.showCharacters(it)
            }.onErrorAlso {
                view?.showMessage("Error Loading Characters", it)
            }
            view?.hideLoading()
        }
    }

    fun refresh() {
        repo.invalidateCharacters()
        view?.showCharacters(emptyList())
        loadCharacters()
    }
}