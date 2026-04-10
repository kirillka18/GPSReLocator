package com.pronin.gpsrelocator.ui.map

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.LocationServices
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pronin.gpsrelocator.R
import com.pronin.gpsrelocator.databinding.FragmentMapBinding
import com.pronin.gpsrelocator.service.MockLocationService
import com.pronin.gpsrelocator.utils.PrefsManager
import com.pronin.gpsrelocator.utils.isMockLocationEnabled
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.util.MapTileIndex
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MapViewModel by viewModels()
    private lateinit var prefs: PrefsManager

    private var myLocationOverlay: MyLocationNewOverlay? = null
    private var isFirstLocation = true

    // Debounce map center updates so we don't spam on every scroll pixel
    private val idleHandler = Handler(Looper.getMainLooper())
    private val idleRunnable = Runnable {
        val center = binding.mapView.mapCenter
        viewModel.updateSelectedLocation(center.latitude, center.longitude)
    }

    private val mockStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val isActive = intent?.getBooleanExtra(MockLocationService.BROADCAST_EXTRA_ACTIVE, false) ?: false
            viewModel.setMockingState(isActive)
        }
    }

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            enableMyLocation()
        } else {
            Toast.makeText(requireContext(), R.string.permission_required, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // OSMDroid requires this before any MapView is used
        Configuration.getInstance().apply {
            userAgentValue = requireContext().packageName
            // Cache tiles in app's private storage — no external storage permission needed
            osmdroidBasePath = requireContext().filesDir
            osmdroidTileCache = requireContext().cacheDir
        }

        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = PrefsManager(requireContext())

        setupMap()
        setupObservers()
        setupClickListeners()
        requestLocationPermission()
    }

    private fun setupMap() {
        binding.mapView.apply {
            // Set dark tile source based on preference
            setTileSource(getTileSource(prefs.mapType))

            // Disable built-in zoom buttons (we have our own FAB)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

            // Enable multi-touch zoom
            setMultiTouchControls(true)

            // Zoom and initial center (will be overridden by location)
            controller.setZoom(14.0)
            controller.setCenter(GeoPoint(55.751244, 37.618423)) // Moscow as default

            // Detect when user stops scrolling/zooming → update coordinates
            addMapListener(object : MapListener {
                override fun onScroll(event: ScrollEvent?): Boolean {
                    scheduleIdleUpdate()
                    return false
                }

                override fun onZoom(event: ZoomEvent?): Boolean {
                    scheduleIdleUpdate()
                    return false
                }
            })
        }

        // Initial coordinate display
        val center = binding.mapView.mapCenter
        viewModel.updateSelectedLocation(center.latitude, center.longitude)
    }

    private fun scheduleIdleUpdate() {
        idleHandler.removeCallbacks(idleRunnable)
        idleHandler.postDelayed(idleRunnable, 300L)
    }

    /** Returns the OSMDroid tile source based on user preference integer */
    private fun getTileSource(type: Int): OnlineTileSourceBase {
        return when (type) {
            PrefsManager.MAP_TYPE_SATELLITE -> SATELLITE_TILE_SOURCE
            PrefsManager.MAP_TYPE_STANDARD -> TileSourceFactory.MAPNIK
            else -> DARK_TILE_SOURCE // default: dark
        }
    }

    private fun enableMyLocation() {
        val provider = GpsMyLocationProvider(requireContext())
        myLocationOverlay = MyLocationNewOverlay(provider, binding.mapView).apply {
            enableMyLocation()
            // Move to current location only on first fix
            runOnFirstFix {
                activity?.runOnUiThread {
                    if (isFirstLocation) {
                        isFirstLocation = false
                        val loc = myLocation
                        if (loc != null) {
                            binding.mapView.controller.animateTo(loc, 15.0, 800L)
                        }
                    }
                }
            }
        }
        binding.mapView.overlays.add(myLocationOverlay)
        binding.mapView.invalidate()
    }

    fun navigateToLocation(lat: Double, lng: Double) {
        val point = GeoPoint(lat, lng)
        binding.mapView.controller.animateTo(point, 15.0, 800L)
        viewModel.updateSelectedLocation(lat, lng)
    }

    private fun setupObservers() {
        viewModel.selectedLocation.observe(viewLifecycleOwner) { (lat, lng) ->
            binding.tvCoordinates.text = "%.6f, %.6f".format(lat, lng)
        }

        viewModel.isMocking.observe(viewLifecycleOwner) { isMocking ->
            updateMockingUI(isMocking)
            if (isMocking && prefs.keepScreenOn) {
                requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }

        viewModel.saveEvent.observe(viewLifecycleOwner) { place ->
            if (place != null) {
                Toast.makeText(requireContext(), R.string.place_saved, Toast.LENGTH_SHORT).show()
                binding.btnSavePlace.setImageResource(R.drawable.ic_star_filled)
                viewModel.clearSaveEvent()
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnRelocate.setOnClickListener {
            val loc = viewModel.selectedLocation.value ?: run {
                Toast.makeText(requireContext(), R.string.error_location_unavailable, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!requireContext().isMockLocationEnabled()) {
                showMockPermissionDialog()
                return@setOnClickListener
            }
            startMockLocation(loc.first, loc.second)
        }

        binding.btnCancelRelocation.setOnClickListener {
            stopMockLocation()
        }

        binding.btnSavePlace.setOnClickListener {
            val loc = viewModel.selectedLocation.value ?: return@setOnClickListener
            showSavePlaceDialog(loc.first, loc.second)
        }

        binding.fabMyLocation.setOnClickListener {
            myLocationOverlay?.myLocation?.let { geoPoint ->
                binding.mapView.controller.animateTo(geoPoint, 15.0, 600L)
            } ?: tryFusedLocation()
        }
    }

    private fun tryFusedLocation() {
        try {
            val fusedClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val point = GeoPoint(it.latitude, it.longitude)
                    binding.mapView.controller.animateTo(point, 15.0, 600L)
                }
            }
        } catch (_: SecurityException) {}
    }

    private fun startMockLocation(lat: Double, lng: Double) {
        val intent = Intent(requireContext(), MockLocationService::class.java).apply {
            action = MockLocationService.ACTION_START
            putExtra(MockLocationService.EXTRA_LAT, lat)
            putExtra(MockLocationService.EXTRA_LNG, lng)
            putExtra(MockLocationService.EXTRA_INTERVAL_MS, prefs.mockIntervalMs)
            putExtra(MockLocationService.EXTRA_ACCURACY, prefs.mockAccuracy)
        }
        ContextCompat.startForegroundService(requireContext(), intent)
        viewModel.setMockingState(true)
    }

    private fun stopMockLocation() {
        requireContext().startService(
            Intent(requireContext(), MockLocationService::class.java).apply {
                action = MockLocationService.ACTION_STOP
            }
        )
        viewModel.setMockingState(false)
        binding.btnSavePlace.setImageResource(R.drawable.ic_star_outline)
        Toast.makeText(requireContext(), R.string.relocation_stopped, Toast.LENGTH_SHORT).show()
    }

    private fun updateMockingUI(isMocking: Boolean) {
        if (isMocking) {
            binding.btnRelocate.visibility = View.GONE
            binding.btnCancelRelocation.visibility = View.VISIBLE
            binding.statusBadge.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_status_active)
            (binding.statusDot.background as? GradientDrawable)?.setColor(Color.parseColor("#FFD600"))
            binding.tvStatus.text = getString(R.string.status_active)
            binding.tvStatus.setTextColor(Color.parseColor("#FFD600"))
        } else {
            binding.btnRelocate.visibility = View.VISIBLE
            binding.btnCancelRelocation.visibility = View.GONE
            binding.statusBadge.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_status_inactive)
            (binding.statusDot.background as? GradientDrawable)?.setColor(Color.parseColor("#666666"))
            binding.tvStatus.text = getString(R.string.status_inactive)
            binding.tvStatus.setTextColor(Color.parseColor("#AAAAAA"))
        }
    }

    private fun showSavePlaceDialog(lat: Double, lng: Double) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_save_place, null)
        val etName = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.et_place_name)
        val tvCoords = dialogView.findViewById<android.widget.TextView>(R.id.tv_dialog_coords)
        tvCoords.text = "%.6f, %.6f".format(lat, lng)

        MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_GPSReLocator_Dialog)
            .setView(dialogView)
            .setPositiveButton(R.string.dialog_save) { _, _ ->
                val name = etName.text?.toString()?.trim()
                if (!name.isNullOrBlank()) viewModel.savePlace(name, lat, lng)
            }
            .setNegativeButton(R.string.dialog_cancel, null)
            .show()
    }

    private fun showMockPermissionDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_GPSReLocator_Dialog)
            .setTitle("Mock Location")
            .setMessage(R.string.mock_permission_required)
            .setPositiveButton(R.string.open_dev_settings) { _, _ ->
                try {
                    startActivity(Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS))
                } catch (_: Exception) {
                    Toast.makeText(requireContext(), "Откройте настройки разработчика вручную", Toast.LENGTH_LONG).show()
                }
            }
            .setNegativeButton(R.string.dialog_cancel, null)
            .show()
    }

    private fun requestLocationPermission() {
        val fine = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        val coarse = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
        if (fine == PackageManager.PERMISSION_GRANTED || coarse == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        } else {
            locationPermissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
        binding.mapView.setTileSource(getTileSource(prefs.mapType))
        myLocationOverlay?.enableMyLocation()
        val filter = IntentFilter(MockLocationService.BROADCAST_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireContext().registerReceiver(mockStatusReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            requireContext().registerReceiver(mockStatusReceiver, filter)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
        myLocationOverlay?.disableMyLocation()
        try {
            requireContext().unregisterReceiver(mockStatusReceiver)
        } catch (_: Exception) {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        idleHandler.removeCallbacks(idleRunnable)
        _binding = null
    }

    companion object {
        /** CartoDB Dark Matter — free, no API key, great dark style */
        val DARK_TILE_SOURCE: OnlineTileSourceBase = object : XYTileSource(
            "CartoDark",
            1, 19, 256, ".png",
            arrayOf(
                "https://a.basemaps.cartocdn.com/dark_all/",
                "https://b.basemaps.cartocdn.com/dark_all/",
                "https://c.basemaps.cartocdn.com/dark_all/"
            ),
            "© OpenStreetMap contributors © CARTO"
        ) {
            override fun getTileURLString(pTileIndex: Long): String {
                return baseUrl +
                        MapTileIndex.getZoom(pTileIndex) + "/" +
                        MapTileIndex.getX(pTileIndex) + "/" +
                        MapTileIndex.getY(pTileIndex) + mImageFilenameEnding
            }
        }

        /** Standard OpenStreetMap tiles (light) */
        val STANDARD_TILE_SOURCE: OnlineTileSourceBase = TileSourceFactory.MAPNIK

        /** Esri Satellite imagery — free, no API key */
        val SATELLITE_TILE_SOURCE: OnlineTileSourceBase = object : XYTileSource(
            "EsriSatellite",
            1, 19, 256, ".jpg",
            arrayOf("https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/"),
            "© Esri"
        ) {
            override fun getTileURLString(pTileIndex: Long): String {
                return baseUrl +
                        MapTileIndex.getZoom(pTileIndex) + "/" +
                        MapTileIndex.getY(pTileIndex) + "/" +
                        MapTileIndex.getX(pTileIndex)
            }
        }
    }
}
