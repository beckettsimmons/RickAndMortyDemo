package ca.example.rickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ca.example.rickandmorty.R
import ca.example.rickandmorty.Utils
import ca.example.rickandmorty.databinding.FragmentCharacterBinding
import ca.example.rickandmorty.entities.Character
import ca.example.rickandmorty.ui.TopLevel
import ca.example.rickandmorty.view.CharacterPresenter
import ca.example.rickandmorty.view.CharacterView
import com.squareup.picasso.Picasso

/** Fragment that displays a single character. */
class CharacterFragment : Fragment(), CharacterView {
    lateinit var presenter: CharacterPresenter
    lateinit var binding: FragmentCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireArguments().apply {
            val userId = getString("userId")?.toInt() ?: error("MUST pass argument [userId] to CharacterFragment")
            presenter = TopLevel.getCharacterPresenter(userId)
        }
        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        // Setup toolbar
        presenter.attachView(this)
        return binding.root
    }

    override fun showCharacter(character: Character) {
        Picasso.with(context)
            .load(character.image)
            .into(binding.backdropImage)
        binding.characterName.text = character.name
        binding.species.setup(character.species, "Species", R.drawable.ic_species)
        binding.gender.setup(character.gender, "Gender", R.drawable.ic_gender)
        binding.location.setup(character.location.name, "Current Location", R.drawable.ic_location)
        binding.origin.setup(character.origin.name, "Origin", R.drawable.ic_home_black_24dp)
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