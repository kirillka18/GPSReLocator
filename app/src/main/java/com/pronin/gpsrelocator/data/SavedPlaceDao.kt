package com.pronin.gpsrelocator.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedPlaceDao {

    @Query("SELECT * FROM saved_places ORDER BY createdAt DESC")
    fun getAllPlaces(): Flow<List<SavedPlace>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place: SavedPlace): Long

    @Delete
    suspend fun deletePlace(place: SavedPlace)

    @Query("DELETE FROM saved_places WHERE id = :id")
    suspend fun deletePlaceById(id: Long)

    @Query("SELECT COUNT(*) FROM saved_places")
    suspend fun getCount(): Int
}
