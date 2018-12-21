package iHeart.demo.iHeartDemo.services.face;


import iHeart.demo.iHeartDemo.models.StationModel;
import org.springframework.stereotype.Component;


@Component
public interface StationService {

    StationModel getStationById(Long id);


}
