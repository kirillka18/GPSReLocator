package com.pronin.gpsrelocator.utils

import android.content.Context
import android.content.SharedPreferences

class PrefsManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("gpsrelocator_prefs", Context.MODE_PRIVATE)

    var mockIntervalMs: Long
        get() = prefs.getLong(KEY_INTERVAL, 1000L)
        set(value) = prefs.edit().putLong(KEY_INTERVAL, value).apply()

    var mockAccuracy: Float
        get() = prefs.getFloat(KEY_ACCURACY, 1.0f)
        set(value) = prefs.edit().putFloat(KEY_ACCURACY, value).apply()

    var keepScreenOn: Boolean
        get() = prefs.getBoolean(KEY_KEEP_SCREEN, true)
        set(value) = prefs.edit().putBoolean(KEY_KEEP_SCREEN, value).apply()

    /** Map tile style: 0 = Dark (CartoDB), 1 = Standard (OSM), 2 = Satellite (Esri) */
    var mapType: Int
        get() = prefs.getInt(KEY_MAP_TYPE, MAP_TYPE_DARK)
        set(value) = prefs.edit().putInt(KEY_MAP_TYPE, value).apply()

    var showCompass: Boolean
        get() = prefs.getBoolean(KEY_SHOW_COMPASS, true)
        set(value) = prefs.edit().putBoolean(KEY_SHOW_COMPASS, value).apply()

    companion object {
        const val MAP_TYPE_DARK = 0
        const val MAP_TYPE_STANDARD = 1
        const val MAP_TYPE_SATELLITE = 2

        private const val KEY_INTERVAL = "mock_interval_ms"
        private const val KEY_ACCURACY = "mock_accuracy"
        private const val KEY_KEEP_SCREEN = "keep_screen_on"
        private const val KEY_MAP_TYPE = "map_type"
        private const val KEY_SHOW_COMPASS = "show_compass"
    }
}
