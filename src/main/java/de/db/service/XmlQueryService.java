package de.db.service;

import de.db.domain.Station;
import de.db.exception.StationError;
import de.db.exception.StationException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class XmlQueryService {

    private final static String STATION = "stations";

    public Station getStationData(String stationShortCode) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File directory = new ClassPathResource(STATION).getFile();
            Optional<File> xmlFile = findXmlFile(directory, stationShortCode);
            if (xmlFile.isPresent()) {
                File resourceFile = xmlFile.get();
                return (Station) unmarshaller.unmarshal(resourceFile);
            }

        } catch (IOException e) {
            throw new StationException(StationError.STATION_NOT_FOUND);
        }
        catch (JAXBException e) {
            throw new StationException(StationError.SECTION_NOT_FOUND);
        }
        return null;
    }

    private Optional<File> findXmlFile(File directory, String stationShortCode) {
        return Optional.ofNullable(directory.listFiles())
                .flatMap(files -> Stream.of(files)
                        .filter(file -> file.getName().startsWith(stationShortCode.toUpperCase() + "_"))
                        .findFirst());
    }
}