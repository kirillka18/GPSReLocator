package com.pronin.gpsrelocator.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pronin.gpsrelocator.data.AppDatabase
import com.pronin.gpsrelocator.data.SavedPlace
import kotlinx.coroutines.launch

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).savedPlaceDao()

    // Pair<latitude, longitude>
    private val _selectedLocation = MutableLiveData<Pair<Double, Double>>()
    val selectedLocation: LiveData<Pair<Double, Double>> = _selectedLocation

    private val _isMocking = MutableLiveData(false)
    val isMocking: LiveData<Boolean> = _isMocking

    private val _saveEvent = MutableLiveData<SavedPlace?>()
    val saveEvent: LiveData<SavedPlace?> = _saveEvent

    fun updateSelectedLocation(lat: Double, lng: Double) {
        _selectedLocation.value = Pair(lat, lng)
    }

    fun setMockingState(active: Boolean) {
        _isMocking.value = active
    }

    fun savePlace(name: String, lat: Double, lng: Double) {
        viewModelScope.launch {
            val place = SavedPlace(name = name, latitude = lat, longitude = lng)
            dao.insertPlace(place)
            _saveEvent.postValue(place)
        }
    }

    fun clearSaveEvent() {
        _saveEvent.value = null
    }
}
