package ca.example.rickandmorty.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ca.example.rickandmorty.Utils
import ca.example.rickandmorty.databinding.FragmentEpisodesBinding
import ca.example.rickandmorty.entities.Episode
import ca.example.rickandmorty.ui.TopLevel
import ca.example.rickandmorty.view.EpisodesPresenter
import ca.example.rickandmorty.view.EpisodesView

/** A fragment that displays a list of episodes. */
class EpisodesFragment : Fragment(), EpisodesView {
    lateinit var presenter: EpisodesPresenter
    lateinit var binding: FragmentEpisodesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = TopLevel.getEpisodesPresenter()
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        presenter.attachView(this)
        return binding.root
    }

    override fun showEpisodes(episodes: List<Episode>) {
        binding.episodesList.adapter = EpisodesAdapter(
            requireContext(), episodes
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