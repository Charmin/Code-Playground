package interviews.microsoft.design.cab_booking.models;

public class Cab {
    private Long cabNo;
    private CabState cabState;
    private Trip ongoingTrip;
    private Location currentLocation;
    private Cab cabType;

    public Cab(Long cabNo, CabState cabState, Trip ongoingTrip, Location currentLocation, Cab cabType) {
        this.cabNo = cabNo;
        this.cabState = cabState;
        this.ongoingTrip = ongoingTrip;
        this.currentLocation = currentLocation;
        this.cabType = cabType;
    }

    public Long getCabNo() {
        return cabNo;
    }

    public CabState getCabState() {
        return cabState;
    }

    public Trip getOngoingTrip() {
        return ongoingTrip;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCabState(CabState cabState) {
        this.cabState = cabState;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
