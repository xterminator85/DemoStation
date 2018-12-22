package iHeart.demo.iHeartDemo.services.implementation;

import iHeart.demo.iHeartDemo.Exception.NotFoundException;
import iHeart.demo.iHeartDemo.models.StationModel;
import iHeart.demo.iHeartDemo.repositories.face.StationRepository;
import iHeart.demo.iHeartDemo.services.face.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImplementation implements StationService {


    private StationRepository stationRepository;

    @Autowired
    public StationServiceImplementation(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }


    @Override
    public StationModel getStationById(Long id) {
        Optional<StationModel> station = stationRepository.findById(id);

        if (!station.isPresent()) {
            throw new NotFoundException(String.format("Station ID %d was not found.", id));
        }

        return station.get();
    }

    @Override
    public StationModel getStationByName(String name) {
        Optional<StationModel> station = stationRepository.findByName(name);

        if (!station.isPresent()) {
            throw new NotFoundException(String.format("Station %s was not found.", name));
        }

        return station.get();
    }

    @Override
    public List<StationModel> getStationsByHdEnabled(Boolean hdEnabled) {
        return stationRepository.findByHdEnabled(hdEnabled);
    }

    @Override
    public void deleteById(long id) {
        if (!stationRepository.existsById(id)) {
            throw new NotFoundException(String.format("Station ID %d was not found.", id));
        }
        stationRepository.deleteById(id);

    }

    @Override
    public StationModel createOrUpdate(StationModel station) {
        return stationRepository.save(station);
    }

    @Override
    public List<StationModel> getAllStations() {
        return (List<StationModel>) stationRepository.findAll();
    }

}