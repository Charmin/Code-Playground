package interviews.microsoft.design.cab_booking.services;

import interviews.microsoft.design.cab_booking.models.Cab;
import interviews.microsoft.design.cab_booking.models.CabState;
import interviews.microsoft.design.cab_booking.models.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CabServiceImpl implements CabService {
    private Map<Long, Cab> cabMatrix = new ConcurrentHashMap<>();
    Lock lock;
    private final long MAX_RADIUS = 100;

    @Override
    public void registerCab(final Cab newCab) {
        if (cabMatrix.containsKey(newCab.getCabNo())) {
            throw new IllegalArgumentException("Cab is already registered in the system");
        }
        cabMatrix.put(newCab.getCabNo(), newCab);
    }

    @Override
    public List<Cab> showAvailableCabs(final Location location) {
        //see if sorted list(by distance) can be sent
        //can this value cached for a location
        List<Cab> availableCabs = new ArrayList<>();
        for (Map.Entry<Long, Cab> cabEntry: cabMatrix.entrySet()) {
            if (cabEntry.getValue().getCabState() == CabState.AVAILABLE &&
                    cabEntry.getValue().getCurrentLocation().getDistance(location) <= MAX_RADIUS) {
                availableCabs.add(cabEntry.getValue());
            }
        }
        return availableCabs;
     }

    @Override
    public void updateLocation(Long cabId, Location location) {
        lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                cabMatrix.get(cabId).setCurrentLocation(location);
            } finally {
                lock.unlock();
            }
        } else {
            throw new RuntimeException("Failed to update location of the cab");
        }
    }

    @Override
    public void updateAvailability(Long cabId, CabState cabState) {
        lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                cabMatrix.get(cabId).setCabState(cabState);
            } finally {
                lock.unlock();
            }
        } else {
            throw new RuntimeException("Failed to update availabilty");
        }
    }
}
