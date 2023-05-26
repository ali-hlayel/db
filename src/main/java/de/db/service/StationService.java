package de.db.service;

import de.db.domain.Station;
import de.db.domain.repository.StationRepository;
import de.db.dto.SectionView;
import de.db.entityXml.StationXml;
import de.db.exception.StationError;
import de.db.exception.StationException;
import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StationService {

    private final XmlQueryService xmlQueryService;

    private final StationRepository stationRepository;

    public SectionView findWagensSections(String stationShortCode, int trainNumber, int waggonNumber) {
        Station station = stationRepository.findByShortcodeIgnoreCase(stationShortCode).orElseThrow(() -> new StationException(StationError.STATION_NOT_FOUND));
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

    public SectionView getWagensSections(String stationShortCode, int trainNumber, int waggonNumber) throws StationException {
        StationXml station;
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
