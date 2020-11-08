package interviews.microsoft.design.cab_booking.strategy;

import interviews.microsoft.design.cab_booking.models.Cab;
import interviews.microsoft.design.cab_booking.models.Location;
import interviews.microsoft.design.cab_booking.models.Rider;

public interface CabPricingStrategy {
    Double chargeTrip(final Cab cab, Location start, Location end, Rider rider);
}
