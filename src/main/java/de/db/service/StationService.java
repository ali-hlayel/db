package de.db.service;

import de.db.domain.*;
import de.db.dto.SectionView;
import de.db.exception.StationError;
import de.db.exception.StationException;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class StationService {
    private final XmlQueryService xmlQueryService;

    @Autowired
    public StationService(XmlQueryService xmlQueryService) {
        this.xmlQueryService = xmlQueryService;
    }

    public SectionView getWagensSections(String stationShortCode, int trainNumber, int waggonNumber) throws StationException {
        Station station;
        try {
            station = xmlQueryService.getStationData(stationShortCode);
        } catch (JAXBException e) {
            throw new StationException(StationError.FILE_PROCESSING_ERROR);
        } catch (IOException e) {
            throw new StationException(StationError.IO_FILE_ERROR);
        }

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
