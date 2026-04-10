package com.pronin.gpsrelocator.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00128F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u00188F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010 \u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b!\u0010\t\"\u0004\b\"\u0010\u000b\u00a8\u0006$"}, d2 = {"Lcom/pronin/gpsrelocator/utils/PrefsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "keepScreenOn", "getKeepScreenOn", "()Z", "setKeepScreenOn", "(Z)V", "", "mapType", "getMapType", "()I", "setMapType", "(I)V", "", "mockAccuracy", "getMockAccuracy", "()F", "setMockAccuracy", "(F)V", "", "mockIntervalMs", "getMockIntervalMs", "()J", "setMockIntervalMs", "(J)V", "prefs", "Landroid/content/SharedPreferences;", "showCompass", "getShowCompass", "setShowCompass", "Companion", "app_debug"})
public final class PrefsManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    public static final int MAP_TYPE_DARK = 0;
    public static final int MAP_TYPE_STANDARD = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_INTERVAL = "mock_interval_ms";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ACCURACY = "mock_accuracy";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_KEEP_SCREEN = "keep_screen_on";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_MAP_TYPE = "map_type";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SHOW_COMPASS = "show_compass";
    @org.jetbrains.annotations.NotNull()
    public static final com.pronin.gpsrelocator.utils.PrefsManager.Companion Companion = null;
    
    public PrefsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final long getMockIntervalMs() {
        return 0L;
    }
    
    public final void setMockIntervalMs(long value) {
    }
    
    public final float getMockAccuracy() {
        return 0.0F;
    }
    
    public final void setMockAccuracy(float value) {
    }
    
    public final boolean getKeepScreenOn() {
        return false;
    }
    
    public final void setKeepScreenOn(boolean value) {
    }
    
    public final int getMapType() {
        return 0;
    }
    
    public final void setMapType(int value) {
    }
    
    public final boolean getShowCompass() {
        return false;
    }
    
    public final void setShowCompass(boolean value) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/pronin/gpsrelocator/utils/PrefsManager$Companion;", "", "()V", "KEY_ACCURACY", "", "KEY_INTERVAL", "KEY_KEEP_SCREEN", "KEY_MAP_TYPE", "KEY_SHOW_COMPASS", "MAP_TYPE_DARK", "", "MAP_TYPE_SATELLITE", "MAP_TYPE_STANDARD", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}