package ca.example.rickandmorty.view

import ca.example.rickandmorty.entities.Character

interface CharacterView: View {
    fun showCharacter(character: Character)
}