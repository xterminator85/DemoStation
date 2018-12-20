package iHeart.demo.iHeartDemo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class StationController {


    @GetMapping("/test")
    public String retrieveAllStations() {
        String s="TEST";

        return s;
    }


}
