package com.pronin.gpsrelocator.ui.saved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pronin.gpsrelocator.data.AppDatabase
import com.pronin.gpsrelocator.data.SavedPlace
import kotlinx.coroutines.launch

class SavedPlacesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).savedPlaceDao()

    val places: LiveData<List<SavedPlace>> = dao.getAllPlaces().asLiveData()

    private val _navigateToPlace = MutableLiveData<SavedPlace?>()
    val navigateToPlace: LiveData<SavedPlace?> = _navigateToPlace

    fun deletePlace(place: SavedPlace) {
        viewModelScope.launch {
            dao.deletePlace(place)
        }
    }

    fun onGoHereClicked(place: SavedPlace) {
        _navigateToPlace.value = place
    }

    fun onNavigationHandled() {
        _navigateToPlace.value = null
    }
}
