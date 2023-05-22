package de.db.service;

import de.db.domain.Station;
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
    public Station getStationData(String stationShortCode) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File directory = new ClassPathResource("stations").getFile();
            File[] files = directory.listFiles();
            Optional<File> xmlFile = findXmlFile(directory, stationShortCode);
            if (xmlFile.isPresent()) {
                File resourceFile = xmlFile.get();
                Station station = (Station) unmarshaller.unmarshal(resourceFile);
                return station;
            }

        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } return null;
    }

    private Optional<File> findXmlFile(File directory, String stationShortCode) {
        return Optional.ofNullable(directory.listFiles())
                .flatMap(files -> Stream.of(files)
                        .filter(file -> file.getName().startsWith(stationShortCode.toUpperCase() + "_"))
                        .findFirst());
    }
}