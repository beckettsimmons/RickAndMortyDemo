package ca.example.rickandmorty.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ca.example.rickandmorty.Utils
import ca.example.rickandmorty.databinding.FragmentCharactersBinding
import ca.example.rickandmorty.entities.Character
import ca.example.rickandmorty.ui.TopLevel
import ca.example.rickandmorty.view.CharactersPresenter
import ca.example.rickandmorty.view.CharactersView

/** A fragment that displays a list of characters. */
class CharactersFragment : Fragment(), CharactersView {
    lateinit var presenter: CharactersPresenter
    lateinit var binding: FragmentCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = TopLevel.getCharactersPresenter()
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        binding.refreshButton.setOnClickListener {
            presenter.refresh()
        }
        presenter.attachView(this)
        return binding.root
    }

    override fun showCharacters(characters: List<Character>) {
        binding.characterList.adapter = CharacterAdapter(
            requireContext(), characters
        )
    }

    override fun showLoading() { binding.progressBar.visibility = View.VISIBLE }
    override fun hideLoading() { binding.progressBar.visibility = View.GONE }

    override fun showMessage(title: String, body: String) {
        Utils.showAlertDialog(requireContext(), title, body)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}