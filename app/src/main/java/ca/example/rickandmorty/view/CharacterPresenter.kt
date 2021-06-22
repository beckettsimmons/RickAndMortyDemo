package ca.example.rickandmorty.view

import ca.example.rickandmorty.data.DataRepository
import kotlinx.coroutines.launch

/** Presenter for a single character. */
class CharacterPresenter(
    private val characterId: Int,
    private val repo: DataRepository,
): BasePresenter<CharacterView>() {
    override fun onViewAttached(view: CharacterView?) {
        presenterScope?.launch {
            view?.showLoading()
            repo.getCharacter(characterId).onSuccessAlso {
                view?.showCharacter(it)
            }.onErrorAlso {
                view?.showMessage("Error Loading Characters", it)
            }
            view?.hideLoading()
        }
    }
}