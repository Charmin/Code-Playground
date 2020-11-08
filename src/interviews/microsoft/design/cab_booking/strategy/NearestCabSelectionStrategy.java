package interviews.microsoft.design.cab_booking.strategy;

import interviews.microsoft.design.cab_booking.models.Cab;
import interviews.microsoft.design.cab_booking.models.Location;

import java.util.List;
import java.util.Optional;

public class NearestCabSelectionStrategy implements CabSelectionStrategy {

    @Override
    public Optional<Cab> selectCab(List<Cab> candidateCabs, Location start, Location end) {
        Cab nearest = null;
        long distance = Long.MAX_VALUE;
        for (Cab cab : candidateCabs) {
            if (cab.getCurrentLocation().getDistance(start) <= distance) {
                distance = cab.getCurrentLocation().getDistance(start);
                nearest = cab;
            }
        }
        return Optional.ofNullable(nearest);
    }
}
