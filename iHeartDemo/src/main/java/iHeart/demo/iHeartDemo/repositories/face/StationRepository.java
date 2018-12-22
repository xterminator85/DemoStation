package iHeart.demo.iHeartDemo.repositories.face;

import iHeart.demo.iHeartDemo.models.StationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public interface StationRepository extends CrudRepository<StationModel, Long>{
    Optional<StationModel> findByName(String name);
    List<StationModel> findByHdEnabled(Boolean hdEnabled);
}
