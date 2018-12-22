package iHeart.demo.iHeartDemo.controllers;

import iHeart.demo.iHeartDemo.models.StationModel;
import iHeart.demo.iHeartDemo.services.face.StationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class StationController {


    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }
    @GetMapping("/station/id/{id}")
    public ResponseEntity<StationModel> findStationById(@PathVariable  Long id) {
        return new ResponseEntity<>(stationService.getStationById(id), HttpStatus.OK);
    }

    @GetMapping("/station/name/{name}")
    public ResponseEntity<StationModel> findStationByName(@PathVariable String name)  {
        return new ResponseEntity<>(stationService.getStationByName(name), HttpStatus.OK);
    }

    @GetMapping("/stations/hdenabled/{hdEnabled}")
    public ResponseEntity<List<StationModel>> findStationByHdEnabled(@PathVariable boolean hdEnabled) {
        return new ResponseEntity<>(stationService.getStationsByHdEnabled(hdEnabled), HttpStatus.OK);
    }

    @DeleteMapping("/station/{id}")
    public void deleteById(@PathVariable long id) {
        stationService.deleteById(id);
    }


    @PostMapping("/station")
    public ResponseEntity<StationModel> createOrUpdateStation(@Valid @RequestBody StationModel station)  {
        return new ResponseEntity<>(stationService.createOrUpdate(station), HttpStatus.OK);
    }

    @GetMapping("/station/all")
    public ResponseEntity<List<StationModel>> findAllStations() {
        return new ResponseEntity<>(stationService.getAllStations(), HttpStatus.OK);
    }


}
