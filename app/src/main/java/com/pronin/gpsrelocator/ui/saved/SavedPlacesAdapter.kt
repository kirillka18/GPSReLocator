package com.pronin.gpsrelocator.ui.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pronin.gpsrelocator.data.SavedPlace
import com.pronin.gpsrelocator.databinding.ItemSavedPlaceBinding

class SavedPlacesAdapter(
    private val onGoHere: (SavedPlace) -> Unit,
    private val onDelete: (SavedPlace) -> Unit
) : ListAdapter<SavedPlace, SavedPlacesAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSavedPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSavedPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: SavedPlace) {
            binding.tvPlaceName.text = place.name
            binding.tvPlaceCoords.text = place.formattedCoords()
            binding.btnGoHere.setOnClickListener { onGoHere(place) }
            binding.btnDelete.setOnClickListener { onDelete(place) }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<SavedPlace>() {
        override fun areItemsTheSame(oldItem: SavedPlace, newItem: SavedPlace) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SavedPlace, newItem: SavedPlace) = oldItem == newItem
    }
}
