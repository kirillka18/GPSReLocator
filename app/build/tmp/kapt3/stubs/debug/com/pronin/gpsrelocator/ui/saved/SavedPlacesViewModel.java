package com.pronin.gpsrelocator.ui.saved;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0015\u001a\u00020\u0012R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r\u00a8\u0006\u0016"}, d2 = {"Lcom/pronin/gpsrelocator/ui/saved/SavedPlacesViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_navigateToPlace", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pronin/gpsrelocator/data/SavedPlace;", "dao", "Lcom/pronin/gpsrelocator/data/SavedPlaceDao;", "navigateToPlace", "Landroidx/lifecycle/LiveData;", "getNavigateToPlace", "()Landroidx/lifecycle/LiveData;", "places", "", "getPlaces", "deletePlace", "", "place", "onGoHereClicked", "onNavigationHandled", "app_debug"})
public final class SavedPlacesViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.pronin.gpsrelocator.data.SavedPlaceDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.pronin.gpsrelocator.data.SavedPlace>> places = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.pronin.gpsrelocator.data.SavedPlace> _navigateToPlace = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.pronin.gpsrelocator.data.SavedPlace> navigateToPlace = null;
    
    public SavedPlacesViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.pronin.gpsrelocator.data.SavedPlace>> getPlaces() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.pronin.gpsrelocator.data.SavedPlace> getNavigateToPlace() {
        return null;
    }
    
    public final void deletePlace(@org.jetbrains.annotations.NotNull()
    com.pronin.gpsrelocator.data.SavedPlace place) {
    }
    
    public final void onGoHereClicked(@org.jetbrains.annotations.NotNull()
    com.pronin.gpsrelocator.data.SavedPlace place) {
    }
    
    public final void onNavigationHandled() {
    }
}