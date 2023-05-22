package de.db.service;

import de.db.domain.*;
import de.db.dto.SectionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StationService {
    private final XmlQueryService xmlQueryService;

    @Autowired
    public StationService(XmlQueryService xmlQueryService) {
        this.xmlQueryService = xmlQueryService;
    }

    public SectionView getWagensSections(String stationShortCode, int trainNumber, int waggonNumber) {
        Station station = xmlQueryService.getStationData(stationShortCode);
        return SectionView.builder()
                .sections(station.getTracks().stream()
                        .flatMap(track -> track.getTrains().stream())
                        .filter(train -> train.getTrainNumbers().contains(trainNumber))
                        .flatMap(train -> train.getWaggons().stream())
                        .filter(waggon -> waggon.getNumber().equals(waggonNumber))
                        .flatMap(waggon -> waggon.getSections().stream())
                        .collect(Collectors.toSet()))
                .build();
    }
}
