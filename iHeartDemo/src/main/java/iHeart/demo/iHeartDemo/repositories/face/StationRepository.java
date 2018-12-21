package iHeart.demo.iHeartDemo.repositories.face;

import iHeart.demo.iHeartDemo.models.StationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface StationRepository extends CrudRepository<StationModel, Long>{



}
