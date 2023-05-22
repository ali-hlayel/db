package de.db.service;

import de.db.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class StationService {
    private final XmlQueryService xmlQueryService;

    @Autowired
    public StationService(XmlQueryService xmlQueryService) {
        this.xmlQueryService = xmlQueryService;
    }

    public Set<String> getWagensSections(String stationShortCode, int trainNumber, int waggonNumber) {
        Station station = xmlQueryService.getStationData(stationShortCode);

                return station.getTracks().stream()
                .flatMap(track -> track.getTrains().stream())
                .filter(train -> train.getTrainNumbers().contains(trainNumber))
                .flatMap(train -> train.getWaggons().stream())
                .filter(waggon -> waggon.getNumber().equals(waggonNumber))
                .flatMap(waggon -> waggon.getSections().stream())
                .collect(Collectors.toSet());

    }
}
