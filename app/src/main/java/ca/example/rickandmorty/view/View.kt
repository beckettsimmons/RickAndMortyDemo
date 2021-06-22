package ca.example.rickandmorty.view

/** A generic view interface that implemented on any number of different platforms. */
interface View {
    fun showLoading()
    fun hideLoading()
    fun showMessage(title: String, body: String)
}