package interviews.microsoft.design.cab_booking.models;

public class Trip {
    private String tripId;
    private TripState tripState;
    private Double cost;
    private Location source;
    private Location destination;
    private Cab cab;
    private Rider rider;

    public Trip(String tripId, TripState tripState, Double cost, Location source, Location destination, Cab cab, Rider rider) {
        this.tripId = tripId;
        this.tripState = tripState;
        this.cost = cost;
        this.source = source;
        this.destination = destination;
        this.cab = cab;
        this.rider = rider;
    }

    public String getTripId() {
        return tripId;
    }

    public TripState getTripState() {
        return tripState;
    }

    public void setTripState(TripState tripState) {
        this.tripState = tripState;
    }
}
