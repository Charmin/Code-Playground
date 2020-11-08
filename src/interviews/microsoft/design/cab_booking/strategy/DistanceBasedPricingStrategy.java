package interviews.microsoft.design.cab_booking.strategy;

import interviews.microsoft.design.cab_booking.models.Cab;
import interviews.microsoft.design.cab_booking.models.Location;
import interviews.microsoft.design.cab_booking.models.Rider;

public class DistanceBasedPricingStrategy implements CabPricingStrategy {
    @Override
    public Double chargeTrip(Cab cab, Location start, Location end, Rider rider) {
        Long distance = start.getDistance(end);
        Double price;
        if (distance > 100) {
            price = chargeCab(cab) * 10;
        } else {
            price = chargeCab(cab);
        }
        return price;
    }

    private Double chargeCab(Cab cab) {
        return 1000.0;
    }
}
