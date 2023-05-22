package de.db.controller;

import de.db.dto.SectionView;
import de.db.exception.StationException;
import de.db.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        SectionView result;
        try {
            result = stationService.getWagensSections(ril100, trainNumber, number);
        } catch (StationException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "", e);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
