package com.pronin.gpsrelocator.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pronin.gpsrelocator.R
import com.pronin.gpsrelocator.databinding.FragmentSavedPlacesBinding

class SavedPlacesFragment : Fragment() {

    private var _binding: FragmentSavedPlacesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedPlacesViewModel by viewModels()
    private lateinit var adapter: SavedPlacesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSavedPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = SavedPlacesAdapter(
            onGoHere = { place ->
                viewModel.onGoHereClicked(place)
            },
            onDelete = { place ->
                showDeleteConfirmation(place.name) {
                    viewModel.deletePlace(place)
                }
            }
        )

        binding.rvSavedPlaces.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SavedPlacesFragment.adapter
        }
    }

    private fun setupObservers() {
        viewModel.places.observe(viewLifecycleOwner) { places ->
            adapter.submitList(places)
            val count = places.size
            binding.tvPlacesCount.text = when (count) {
                0 -> ""
                1 -> "1 сохранённое место"
                in 2..4 -> "$count сохранённых места"
                else -> "$count сохранённых мест"
            }
            binding.emptyState.visibility = if (places.isEmpty()) View.VISIBLE else View.GONE
            binding.rvSavedPlaces.visibility = if (places.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.navigateToPlace.observe(viewLifecycleOwner) { place ->
            if (place != null) {
                // Navigate to map tab and pass coordinates
                val navController = findNavController()
                // Switch to map tab with the selected location stored in shared prefs or args
                requireActivity().let { activity ->
                    if (activity is com.pronin.gpsrelocator.MainActivity) {
                        activity.navigateToMapWithLocation(place.latitude, place.longitude)
                    }
                }
                viewModel.onNavigationHandled()
            }
        }
    }

    private fun showDeleteConfirmation(name: String, onConfirm: () -> Unit) {
        MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_GPSReLocator_Dialog)
            .setTitle(R.string.delete_place_confirm)
            .setMessage(name)
            .setPositiveButton(R.string.delete) { _, _ -> onConfirm() }
            .setNegativeButton(R.string.dialog_cancel, null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
