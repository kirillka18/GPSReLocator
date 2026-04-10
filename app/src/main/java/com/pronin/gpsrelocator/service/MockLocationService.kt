package com.pronin.gpsrelocator.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.pronin.gpsrelocator.MainActivity
import com.pronin.gpsrelocator.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MockLocationService : Service() {

    companion object {
        const val ACTION_START = "com.pronin.gpsrelocator.ACTION_START"
        const val ACTION_STOP = "com.pronin.gpsrelocator.ACTION_STOP"
        const val ACTION_UPDATE = "com.pronin.gpsrelocator.ACTION_UPDATE"
        const val EXTRA_LAT = "extra_latitude"
        const val EXTRA_LNG = "extra_longitude"
        const val EXTRA_INTERVAL_MS = "extra_interval_ms"
        const val EXTRA_ACCURACY = "extra_accuracy"

        private const val NOTIFICATION_ID = 1337
        private const val CHANNEL_ID = "mock_location_channel"

        // Broadcast sent to inform the app about mock status
        const val BROADCAST_STATUS = "com.pronin.gpsrelocator.MOCK_STATUS"
        const val BROADCAST_EXTRA_ACTIVE = "is_active"

        val MOCK_PROVIDERS = listOf(
            LocationManager.GPS_PROVIDER,
            LocationManager.NETWORK_PROVIDER,
            "fused"
        )
    }

    private var locationManager: LocationManager? = null
    private var mockJob: Job? = null
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private var mockLat: Double = 0.0
    private var mockLng: Double = 0.0
    private var intervalMs: Long = 1000L
    private var accuracy: Float = 1.0f

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                mockLat = intent.getDoubleExtra(EXTRA_LAT, 0.0)
                mockLng = intent.getDoubleExtra(EXTRA_LNG, 0.0)
                intervalMs = intent.getLongExtra(EXTRA_INTERVAL_MS, 1000L)
                accuracy = intent.getFloatExtra(EXTRA_ACCURACY, 1.0f)
                startMocking()
            }
            ACTION_UPDATE -> {
                mockLat = intent.getDoubleExtra(EXTRA_LAT, mockLat)
                mockLng = intent.getDoubleExtra(EXTRA_LNG, mockLng)
            }
            ACTION_STOP -> {
                stopMocking()
                stopSelf()
            }
        }
        return START_STICKY
    }

    private fun startMocking() {
        startForeground(NOTIFICATION_ID, buildNotification())
        setupProviders()
        mockJob?.cancel()
        mockJob = serviceScope.launch {
            sendStatusBroadcast(true)
            while (isActive) {
                pushMockLocation()
                delay(intervalMs)
            }
        }
    }

    private fun setupProviders() {
        locationManager?.let { lm ->
            for (provider in MOCK_PROVIDERS) {
                try {
                    lm.addTestProvider(
                        provider,
                        /* requiresNetwork= */ false,
                        /* requiresSatellite= */ false,
                        /* requiresCell= */ false,
                        /* hasMonetaryCost= */ false,
                        /* supportsAltitude= */ true,
                        /* supportsSpeed= */ true,
                        /* supportsBearing= */ true,
                        /* powerRequirement= */ Criteria.POWER_LOW,
                        /* accuracy= */ Criteria.ACCURACY_FINE
                    )
                    lm.setTestProviderEnabled(provider, true)
                } catch (_: Exception) {
                    // Provider may already exist or fused is not available
                }
            }
        }
    }

    private fun pushMockLocation() {
        locationManager?.let { lm ->
            for (provider in MOCK_PROVIDERS) {
                try {
                    val loc = Location(provider).apply {
                        latitude = mockLat
                        longitude = mockLng
                        altitude = 0.0
                        this.accuracy = this@MockLocationService.accuracy
                        bearing = 0f
                        speed = 0f
                        time = System.currentTimeMillis()
                        elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            bearingAccuracyDegrees = 0f
                            speedAccuracyMetersPerSecond = 0f
                            verticalAccuracyMeters = this@MockLocationService.accuracy
                        }
                    }
                    lm.setTestProviderLocation(provider, loc)
                } catch (_: Exception) {
                    // Provider not active or removed
                }
            }
        }
    }

    private fun stopMocking() {
        mockJob?.cancel()
        locationManager?.let { lm ->
            for (provider in MOCK_PROVIDERS) {
                try {
                    lm.removeTestProvider(provider)
                } catch (_: Exception) {}
            }
        }
        sendStatusBroadcast(false)
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    private fun sendStatusBroadcast(isActive: Boolean) {
        val intent = Intent(BROADCAST_STATUS).apply {
            putExtra(BROADCAST_EXTRA_ACTIVE, isActive)
            setPackage(packageName)
        }
        sendBroadcast(intent)
    }

    private fun buildNotification(): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val stopIntent = PendingIntent.getService(
            this, 1,
            Intent(this, MockLocationService::class.java).apply { action = ACTION_STOP },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText("%.5f, %.5f".format(mockLat, mockLng))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_delete, getString(R.string.notification_stop), stopIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = getString(R.string.notification_channel_description)
            setShowBadge(false)
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        stopMocking()
        super.onDestroy()
    }
}
