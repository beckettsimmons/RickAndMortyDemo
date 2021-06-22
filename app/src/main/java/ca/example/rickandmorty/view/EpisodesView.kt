package ca.example.rickandmorty.view

import ca.example.rickandmorty.entities.Episode

interface EpisodesView: View {
    fun showEpisodes(episodes: List<Episode>)
}