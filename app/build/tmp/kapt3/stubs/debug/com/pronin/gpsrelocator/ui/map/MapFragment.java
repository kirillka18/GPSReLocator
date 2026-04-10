package com.pronin.gpsrelocator.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0016\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&J$\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u001fH\u0016J\b\u00101\u001a\u00020\u001fH\u0016J\b\u00102\u001a\u00020\u001fH\u0016J\u001a\u00103\u001a\u00020\u001f2\u0006\u00104\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00105\u001a\u00020\u001fH\u0002J\b\u00106\u001a\u00020\u001fH\u0002J\b\u00107\u001a\u00020\u001fH\u0002J\b\u00108\u001a\u00020\u001fH\u0002J\b\u00109\u001a\u00020\u001fH\u0002J\b\u0010:\u001a\u00020\u001fH\u0002J\u0018\u0010;\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&H\u0002J\u0018\u0010<\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&H\u0002J\b\u0010=\u001a\u00020\u001fH\u0002J\b\u0010>\u001a\u00020\u001fH\u0002J\u0010\u0010?\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020\rH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006B"}, d2 = {"Lcom/pronin/gpsrelocator/ui/map/MapFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/pronin/gpsrelocator/databinding/FragmentMapBinding;", "binding", "getBinding", "()Lcom/pronin/gpsrelocator/databinding/FragmentMapBinding;", "idleHandler", "Landroid/os/Handler;", "idleRunnable", "Ljava/lang/Runnable;", "isFirstLocation", "", "locationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "mockStatusReceiver", "Landroid/content/BroadcastReceiver;", "myLocationOverlay", "Lorg/osmdroid/views/overlay/mylocation/MyLocationNewOverlay;", "prefs", "Lcom/pronin/gpsrelocator/utils/PrefsManager;", "viewModel", "Lcom/pronin/gpsrelocator/ui/map/MapViewModel;", "getViewModel", "()Lcom/pronin/gpsrelocator/ui/map/MapViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableMyLocation", "", "getTileSource", "Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;", "type", "", "navigateToLocation", "lat", "", "lng", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "requestLocationPermission", "scheduleIdleUpdate", "setupClickListeners", "setupMap", "setupObservers", "showMockPermissionDialog", "showSavePlaceDialog", "startMockLocation", "stopMockLocation", "tryFusedLocation", "updateMockingUI", "isMocking", "Companion", "app_debug"})
public final class MapFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.pronin.gpsrelocator.databinding.FragmentMapBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.pronin.gpsrelocator.utils.PrefsManager prefs;
    @org.jetbrains.annotations.Nullable()
    private org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay myLocationOverlay;
    private boolean isFirstLocation = true;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler idleHandler = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Runnable idleRunnable = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver mockStatusReceiver = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> locationPermissionLauncher = null;
    
    /**
     * CartoDB Dark Matter — free, no API key, great dark style
     */
    @org.jetbrains.annotations.NotNull()
    private static final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase DARK_TILE_SOURCE = null;
    
    /**
     * Standard OpenStreetMap tiles (light)
     */
    @org.jetbrains.annotations.NotNull()
    private static final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase STANDARD_TILE_SOURCE = null;
    
    /**
     * Esri Satellite imagery — free, no API key
     */
    @org.jetbrains.annotations.NotNull()
    private static final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase SATELLITE_TILE_SOURCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.pronin.gpsrelocator.ui.map.MapFragment.Companion Companion = null;
    
    public MapFragment() {
        super();
    }
    
    private final com.pronin.gpsrelocator.databinding.FragmentMapBinding getBinding() {
        return null;
    }
    
    private final com.pronin.gpsrelocator.ui.map.MapViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupMap() {
    }
    
    private final void scheduleIdleUpdate() {
    }
    
    /**
     * Returns the OSMDroid tile source based on user preference integer
     */
    private final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase getTileSource(int type) {
        return null;
    }
    
    private final void enableMyLocation() {
    }
    
    public final void navigateToLocation(double lat, double lng) {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void tryFusedLocation() {
    }
    
    private final void startMockLocation(double lat, double lng) {
    }
    
    private final void stopMockLocation() {
    }
    
    private final void updateMockingUI(boolean isMocking) {
    }
    
    private final void showSavePlaceDialog(double lat, double lng) {
    }
    
    private final void showMockPermissionDialog() {
    }
    
    private final void requestLocationPermission() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/pronin/gpsrelocator/ui/map/MapFragment$Companion;", "", "()V", "DARK_TILE_SOURCE", "Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;", "getDARK_TILE_SOURCE", "()Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;", "SATELLITE_TILE_SOURCE", "getSATELLITE_TILE_SOURCE", "STANDARD_TILE_SOURCE", "getSTANDARD_TILE_SOURCE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * CartoDB Dark Matter — free, no API key, great dark style
         */
        @org.jetbrains.annotations.NotNull()
        public final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase getDARK_TILE_SOURCE() {
            return null;
        }
        
        /**
         * Standard OpenStreetMap tiles (light)
         */
        @org.jetbrains.annotations.NotNull()
        public final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase getSTANDARD_TILE_SOURCE() {
            return null;
        }
        
        /**
         * Esri Satellite imagery — free, no API key
         */
        @org.jetbrains.annotations.NotNull()
        public final org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase getSATELLITE_TILE_SOURCE() {
            return null;
        }
    }
}