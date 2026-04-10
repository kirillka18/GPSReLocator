package com.pronin.gpsrelocator.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\"\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0013H\u0002J\b\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/pronin/gpsrelocator/service/MockLocationService;", "Landroid/app/Service;", "()V", "accuracy", "", "intervalMs", "", "locationManager", "Landroid/location/LocationManager;", "mockJob", "Lkotlinx/coroutines/Job;", "mockLat", "", "mockLng", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "buildNotification", "Landroid/app/Notification;", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "pushMockLocation", "sendStatusBroadcast", "isActive", "", "setupProviders", "startMocking", "stopMocking", "Companion", "app_debug"})
public final class MockLocationService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "com.pronin.gpsrelocator.ACTION_START";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "com.pronin.gpsrelocator.ACTION_STOP";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_UPDATE = "com.pronin.gpsrelocator.ACTION_UPDATE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_LAT = "extra_latitude";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_LNG = "extra_longitude";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_INTERVAL_MS = "extra_interval_ms";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ACCURACY = "extra_accuracy";
    private static final int NOTIFICATION_ID = 1337;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "mock_location_channel";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_STATUS = "com.pronin.gpsrelocator.MOCK_STATUS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_EXTRA_ACTIVE = "is_active";
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<java.lang.String> MOCK_PROVIDERS = null;
    @org.jetbrains.annotations.Nullable()
    private android.location.LocationManager locationManager;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job mockJob;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private double mockLat = 0.0;
    private double mockLng = 0.0;
    private long intervalMs = 1000L;
    private float accuracy = 1.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.pronin.gpsrelocator.service.MockLocationService.Companion Companion = null;
    
    public MockLocationService() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void startMocking() {
    }
    
    private final void setupProviders() {
    }
    
    private final void pushMockLocation() {
    }
    
    private final void stopMocking() {
    }
    
    private final void sendStatusBroadcast(boolean isActive) {
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/pronin/gpsrelocator/service/MockLocationService$Companion;", "", "()V", "ACTION_START", "", "ACTION_STOP", "ACTION_UPDATE", "BROADCAST_EXTRA_ACTIVE", "BROADCAST_STATUS", "CHANNEL_ID", "EXTRA_ACCURACY", "EXTRA_INTERVAL_MS", "EXTRA_LAT", "EXTRA_LNG", "MOCK_PROVIDERS", "", "getMOCK_PROVIDERS", "()Ljava/util/List;", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getMOCK_PROVIDERS() {
            return null;
        }
    }
}