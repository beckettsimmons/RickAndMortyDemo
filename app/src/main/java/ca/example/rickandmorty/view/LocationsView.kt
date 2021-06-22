package ca.example.rickandmorty.view

import ca.example.rickandmorty.entities.Location

interface LocationsView: View {
    fun showLocations(locations: List<Location>)
}