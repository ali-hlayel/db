package de.db.service;

import de.db.domain.repository.StationRepository;
import de.db.dto.SectionView;
import de.db.entityXml.*;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StationServiceTest {

    @Autowired
    private StationService stationService;

    private XmlQueryService xmlQueryService;

    private StationRepository stationRepository;

    @BeforeEach
    public void setUp() {
        xmlQueryService = mock(XmlQueryService.class);
        stationRepository = mock(StationRepository.class);
        stationService = new StationService(xmlQueryService, stationRepository);
    }

    @Test
    void getWagensSections() throws JAXBException, IOException {
        StationXml station = createStation();
        when(xmlQueryService.getStationData("ABC")).thenReturn(station);

        SectionView expectedSections = SectionView.builder().sections(Set.of("A", "B")).build();
        SectionView result = stationService.getWagensSections("ABC", 123, 1);

        assertEquals(expectedSections, result);
    }

    private StationXml createStation() {
        StationXml station = new StationXml();
        TrackXml track1 = new TrackXml();
        TrackXml track2 = new TrackXml();
        TrainXml train1 = new TrainXml();
        TrainXml train2 = new TrainXml();
        WaggonXml waggon1 = new WaggonXml();
        WaggonXml waggon2 = new WaggonXml();
        SectionXml section1 = new SectionXml();
        SectionXml section2 = new SectionXml();
        SectionXml section3 = new SectionXml();

        section1.setIdentifier("A");
        section2.setIdentifier("B");
        section3.setIdentifier("C");

        waggon1.setNumber(1);
        waggon1.setSections(List.of(section1.getIdentifier(), section2.getIdentifier()));
        waggon2.setNumber(2);
        waggon2.setSections(List.of((section3.getIdentifier())));

        train1.setTrainNumbers(List.of(123));
        train1.setWaggons(List.of(waggon1, waggon2));
        train2.setTrainNumbers(List.of(456));

        track1.setTrains(List.of(train1));
        track2.setTrains(List.of(train2));
        station.setTracks(Arrays.asList(track1, track2));

        return station;
    }
}