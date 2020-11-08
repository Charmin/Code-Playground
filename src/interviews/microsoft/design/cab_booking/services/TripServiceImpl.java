package interviews.microsoft.design.cab_booking.services;

import interviews.microsoft.design.cab_booking.models.*;
import interviews.microsoft.design.cab_booking.strategy.CabPricingStrategy;
import interviews.microsoft.design.cab_booking.strategy.CabSelectionStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TripServiceImpl implements TripService {
    Map<String, List<Trip>> tripDetails = new ConcurrentHashMap<>();
    Map<String, Trip> trips = new ConcurrentHashMap<>();

    private CabService cabService;
    private RiderService riderService;
    private CabSelectionStrategy cabSelectionStrategy;
    private CabPricingStrategy pricingStrategy;

    public TripServiceImpl(CabService cabService,
                           RiderService riderService,
                           CabSelectionStrategy cabSelectionStrategy,
                           CabPricingStrategy pricingStrategy) {
        this.cabService = cabService;
        this.cabSelectionStrategy = cabSelectionStrategy;
        this.pricingStrategy = pricingStrategy;
        this.riderService = riderService;
    }

    @Override
    public List<Trip> getAllTrips(final String riderId) {
        if (tripDetails.get(riderId) == null) {
            return new ArrayList<>();
        }
        return tripDetails.get(riderId);
    }

    @Override
    public Trip createTrip(final String riderId, Location start, Location end) {
        Rider rider = riderService.getRiderDetail(riderId);
        List<Cab> availableCabs = cabService.showAvailableCabs(start);
        Optional<Cab> chosenCab = cabSelectionStrategy.selectCab(availableCabs, start, end);
        if (chosenCab.isPresent()) {
            Double price = pricingStrategy.chargeTrip(chosenCab.get(), start, end, rider);
            Random random = new Random();
            Trip trip = new Trip("TRIP"+random.nextInt(), TripState.BOOKED,
                    price, start, end, chosenCab.get(), rider);
            trips.put(trip.getTripId(), trip);
            tripDetails.computeIfAbsent(riderId, r -> new ArrayList<>()).add(trip);
            return trip;
        }
        throw new RuntimeException("Could not find suitable cab. Please try again");
    }

    @Override
    public void startTrip(String tripId) {
        if (trips.containsKey(tripId)) {
            trips.get(tripId).setTripState(TripState.IN_PROGRESS);
        } else {
            throw new RuntimeException("No such trip exists in the system");
        }
    }

    @Override
    public void endTrip(String tripId) {
        if (trips.containsKey(tripId)) {
            trips.get(tripId).setTripState(TripState.ENDED);
        }
    }
}
