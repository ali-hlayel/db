package de.db.controller;

import de.db.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)

public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/station/{ril100}/train/{trainNumber}/waggon/{number}")
    public ResponseEntity<?> getStation(@PathVariable String ril100, @PathVariable int trainNumber, @PathVariable int number) {
        System.out.println(stationService.getWagensSections(ril100, trainNumber, number));
        return new ResponseEntity<>(stationService.getWagensSections(ril100, trainNumber, number), HttpStatus.OK);
    }
}
