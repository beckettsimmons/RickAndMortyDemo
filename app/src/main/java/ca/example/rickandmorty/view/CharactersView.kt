package ca.example.rickandmorty.view

import ca.example.rickandmorty.entities.Character

interface CharactersView: View {
    fun showCharacters(characters: List<Character>)
}