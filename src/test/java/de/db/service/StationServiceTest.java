package de.db.service;

import de.db.domain.*;
import de.db.dto.SectionView;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StationServiceTest {

    @Autowired
    private StationService stationService;

    private XmlQueryService xmlQueryService;

    @BeforeEach
    public void setUp() {
        xmlQueryService = mock(XmlQueryService.class);
        stationService = new StationService(xmlQueryService);
    }

    @Test
    void getWagensSections() throws JAXBException, IOException {
        Station station = createStation();
        when(xmlQueryService.getStationData("ABC")).thenReturn(station);

        SectionView expectedSections = SectionView.builder().sections(Set.of("A", "B")).build();
        SectionView result = stationService.getWagensSections("ABC", 123, 1);

        assertEquals(expectedSections, result);
    }

    private Station createStation() {
        Station station = new Station();
        Track track1 = new Track();
        Track track2 = new Track();
        Train train1 = new Train();
        Train train2 = new Train();
        Waggon waggon1 = new Waggon();
        Waggon waggon2 = new Waggon();
        Section section1 = new Section();
        Section section2 = new Section();
        Section section3 = new Section();

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