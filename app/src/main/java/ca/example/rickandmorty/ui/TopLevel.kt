package ca.example.rickandmorty.ui

import ca.example.rickandmorty.data.DataRepository
import ca.example.rickandmorty.data.Webservice
import ca.example.rickandmorty.view.CharacterPresenter
import ca.example.rickandmorty.view.CharactersPresenter
import ca.example.rickandmorty.view.EpisodesPresenter
import ca.example.rickandmorty.view.LocationsPresenter

/** In lieu of multi-platform dependency injection, dependencies are setup here. */
object TopLevel {
    /* Only create one instance of these to share between presenters. */
    private val webservice = Webservice()
    private val dataRepo = DataRepository(webservice)

    /* Always create new presenters to avoid dangling view references. */
    fun getCharacterPresenter(id: Int) = CharacterPresenter(id, dataRepo)
    fun getCharactersPresenter() = CharactersPresenter(dataRepo)
    fun getLocationsPresenter() = LocationsPresenter(dataRepo)
    fun getEpisodesPresenter() = EpisodesPresenter(dataRepo)
}