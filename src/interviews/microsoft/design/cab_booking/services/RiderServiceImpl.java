package interviews.microsoft.design.cab_booking.services;

import interviews.microsoft.design.cab_booking.models.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderServiceImpl implements RiderService {
    Map<String, Rider> riderDetail = new HashMap<>();

    @Override
    public void createRider(String customerId) {

    }

    @Override
    public Rider getRiderDetail(String customerId) {
        return null;
    }
}
