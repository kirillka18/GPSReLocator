package com.pronin.gpsrelocator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pronin.gpsrelocator.databinding.ActivityMainBinding
import com.pronin.gpsrelocator.ui.map.MapFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Let content draw behind status bar and navigation bar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupNavigation()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // NavHostFragment: add top padding so content doesn't hide under status bar
            binding.navHostFragment.updatePadding(top = systemBars.top)

            // BottomNavigationView: add bottom padding for Samsung gesture nav bar
            binding.bottomNav.updatePadding(bottom = systemBars.bottom)

            insets
        }
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    fun navigateToMapWithLocation(lat: Double, lng: Double) {
        binding.bottomNav.selectedItemId = R.id.mapFragment
        binding.root.postDelayed({
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            navHostFragment?.childFragmentManager?.fragments
                ?.filterIsInstance<MapFragment>()
                ?.firstOrNull()
                ?.navigateToLocation(lat, lng)
        }, 200L)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
