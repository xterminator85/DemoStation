package iHeart.demo.iHeartDemo.controllers;

import iHeart.demo.iHeartDemo.models.StationModel;
import iHeart.demo.iHeartDemo.services.face.StationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class StationController {


    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/test")
    public String retrieveAllStations() {
        String s="TEST";

        return s;
    }

    @GetMapping("/station/id/{id}")
    public StationModel findStationById(@PathVariable  Long id) {
        return stationService.getStationById(id);
    }





}
