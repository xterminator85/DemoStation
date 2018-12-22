package iHeart.demo.iHeartDemo.services.face;


import iHeart.demo.iHeartDemo.models.StationModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StationService {
    StationModel getStationById(Long id);
    StationModel getStationByName(String name);
    List<StationModel> getStationsByHdEnabled(Boolean hdEnabled);
    void deleteById(long id);
    StationModel createOrUpdate(StationModel station);
    List<StationModel> getAllStations();
}
