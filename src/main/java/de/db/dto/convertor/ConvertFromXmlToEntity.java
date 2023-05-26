package de.db.dto.convertor;

import de.db.domain.*;
import de.db.entityXml.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@Transactional
public class ConvertFromXmlToEntity {
    private final EntityManager entityManager;
    public Station convertToStation(StationXml stationXml) {
        Station station = new Station();
        station.setShortcode(stationXml.getShortcode());
        station.setName(stationXml.getName());
        station.setValidity(convertToValidity(stationXml.getValidity()));
        station.setTracks(convertToTracks(stationXml.getTracks()));
        entityManager.persist(station);
        return station;
    }

    public Validity convertToValidity(ValidityXml validityXml) {
        Validity validity = new Validity();
        validity.setFrom(validityXml.getFrom());
        validity.setTo(validityXml.getTo());
        entityManager.persist(validity);

        return validity;
    }

    public List<Track> convertToTracks(List<TrackXml> trackXmlList) {
        List<Track> tracks = new ArrayList<>();
        for (TrackXml trackXml : trackXmlList) {
            Track track = new Track();
            track.setName(trackXml.getName());
            track.setNumber(trackXml.getNumber());
            track.setTrains(convertToTrains(trackXml.getTrains()));
            track.setSections(trackXml.getSections());
            tracks.add(track);
            entityManager.persist(track);
        }
        return tracks;
    }

    public  List<Train> convertToTrains(List<TrainXml> trainXmlList) {
        List<Train> trains = new ArrayList<>();
        for (TrainXml trainXml : trainXmlList) {
            Train train = new Train();
            train.setTrainNumbers(trainXml.getTrainNumbers());
            train.setAnno(trainXml.getAnno());
            train.setTime(trainXml.getTime());
            train.setAdditionalText(trainXml.getAdditionalText());
            train.setName(trainXml.getName());
            train.setSubtrains(convertToSubtrains(trainXml.getSubtrains()));
            train.setWaggons(convertToWaggons(trainXml.getWaggons()));
            //train.setTrainTypes(trainXml.getTrainTypes());
            trains.add(train);
            entityManager.persist(train);

        }
        return trains;
    }

    public List<Subtrain> convertToSubtrains(List<SubtrainXml> subtrainXmlList) {
        List<Subtrain> subtrains = new ArrayList<>();
        for (SubtrainXml subtrainXml : subtrainXmlList) {
            Subtrain subtrain = new Subtrain();
            subtrain.setDestination(convertToDestination(subtrainXml.getDestination()));
            subtrain.setSections(subtrainXml.getSections());
            subtrains.add(subtrain);
            entityManager.persist(subtrain);
        }
        return subtrains;
    }

    public Destination convertToDestination(DestinationXml destinationXml) {
        Destination destination = new Destination();
        destination.setDestinationName(destinationXml.getDestinationName());
        destination.setDestinationVia(destinationXml.getDestinationVia());
        entityManager.persist(destination);
        return destination;
    }

    public List<Waggon> convertToWaggons(List<WaggonXml> waggonXmlList) {
        List<Waggon> waggons = new ArrayList<>();
        for (WaggonXml waggonXml : waggonXmlList) {
            Waggon waggon = new Waggon();
            waggon.setPosition(waggonXml.getPosition());
            waggon.setIsWaggon(waggonXml.getIsWaggon());
            waggon.setSections(waggonXml.getSections());
            waggon.setNumber(waggonXml.getNumber());
            waggon.setType(waggonXml.getType());
            waggon.setSymbols(waggonXml.getSymbols());
            waggon.setDifferentDestination(waggonXml.getDifferentDestination());
            waggon.setLength(waggonXml.getLength());
            waggons.add(waggon);
            entityManager.persist(waggon);
        }
        return waggons;
    }
}
