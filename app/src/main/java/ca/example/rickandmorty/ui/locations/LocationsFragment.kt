package ca.example.rickandmorty.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ca.example.rickandmorty.Utils
import ca.example.rickandmorty.databinding.FragmentLocationsBinding
import ca.example.rickandmorty.entities.Location
import ca.example.rickandmorty.ui.TopLevel
import ca.example.rickandmorty.view.LocationsPresenter
import ca.example.rickandmorty.view.LocationsView

/** A fragment that displays a list of locations. */
class LocationsFragment : Fragment(), LocationsView {
    lateinit var presenter: LocationsPresenter
    lateinit var binding: FragmentLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        presenter = TopLevel.getLocationsPresenter()
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        presenter.attachView(this)
        return binding.root
    }

    override fun showLocations(locations: List<Location>) {
        binding.locationList.adapter = LocationsAdapter(
            requireContext(), locations
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
