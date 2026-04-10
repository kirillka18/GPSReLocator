package com.pronin.gpsrelocator.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pronin.gpsrelocator.BuildConfig
import com.pronin.gpsrelocator.R
import com.pronin.gpsrelocator.databinding.FragmentSettingsBinding
import com.pronin.gpsrelocator.utils.PrefsManager

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var prefs: PrefsManager

    // Interval options in milliseconds
    private val intervalOptions = listOf(250L, 500L, 1000L, 2000L, 3000L, 5000L, 7000L, 10000L, 15000L, 30000L)
    private val intervalLabels = listOf("250мс", "500мс", "1с", "2с", "3с", "5с", "7с", "10с", "15с", "30с")

    // Accuracy options in meters
    private val accuracyOptions = listOf(0.5f, 1f, 2f, 3f, 5f, 8f, 10f, 15f, 20f, 50f)
    private val accuracyLabels = listOf("0.5м", "1м", "2м", "3м", "5м", "8м", "10м", "15м", "20м", "50м")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = PrefsManager(requireContext())

        setupVersionInfo()
        setupIntervalControl()
        setupAccuracyControl()
        setupSwitches()
        setupMapTypeSelector()
    }

    private fun setupVersionInfo() {
        binding.tvVersion.text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
    }

    private fun setupIntervalControl() {
        val currentIdx = intervalOptions.indexOf(prefs.mockIntervalMs).coerceAtLeast(0)
        binding.seekbarInterval.max = intervalOptions.size - 1
        binding.seekbarInterval.progress = currentIdx
        updateIntervalDisplay(currentIdx)

        binding.seekbarInterval.setOnSeekBarChangeListener(object : android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
                updateIntervalDisplay(progress)
                prefs.mockIntervalMs = intervalOptions[progress]
            }
            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {}
        })
    }

    private fun updateIntervalDisplay(idx: Int) {
        binding.tvIntervalDisplay.text = intervalLabels[idx]
        binding.tvIntervalValue.text = "Обновление каждые ${intervalLabels[idx]}"
    }

    private fun setupAccuracyControl() {
        val currentIdx = accuracyOptions.indexOf(prefs.mockAccuracy).let { if (it < 0) 1 else it }
        binding.seekbarAccuracy.max = accuracyOptions.size - 1
        binding.seekbarAccuracy.progress = currentIdx
        updateAccuracyDisplay(currentIdx)

        binding.seekbarAccuracy.setOnSeekBarChangeListener(object : android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar?, progress: Int, fromUser: Boolean) {
                updateAccuracyDisplay(progress)
                prefs.mockAccuracy = accuracyOptions[progress]
            }
            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {}
        })
    }

    private fun updateAccuracyDisplay(idx: Int) {
        binding.tvAccuracyDisplay.text = accuracyLabels[idx]
    }

    private fun setupSwitches() {
        binding.switchKeepScreen.isChecked = prefs.keepScreenOn
        binding.switchKeepScreen.setOnCheckedChangeListener { _, checked ->
            prefs.keepScreenOn = checked
        }

        binding.switchCompass.isChecked = prefs.showCompass
        binding.switchCompass.setOnCheckedChangeListener { _, checked ->
            prefs.showCompass = checked
        }
    }

    private fun setupMapTypeSelector() {
        // Map type names correspond directly to PrefsManager.MAP_TYPE_* constants (0, 1, 2)
        val typeNames = arrayOf("Тёмная (CartoDB)", "Стандарт (OSM)", "Спутник (Esri)")
        val currentIdx = prefs.mapType.coerceIn(0, typeNames.size - 1)
        binding.tvMapType.text = typeNames[currentIdx]

        binding.rowMapType.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_GPSReLocator_Dialog)
                .setTitle(R.string.settings_map_type_title)
                .setSingleChoiceItems(typeNames, prefs.mapType) { dialog, which ->
                    prefs.mapType = which
                    binding.tvMapType.text = typeNames[which]
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
