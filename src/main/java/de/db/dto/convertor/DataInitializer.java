package de.db.dto.convertor;

import de.db.domain.Station;
import de.db.entityXml.StationXml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@AllArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {

    private final ConvertFromXmlToEntity fromXmlToEntity;

    private final static String STATION = "stations";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File directory = new ClassPathResource(STATION).getFile();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {

                if (file.isFile() && file.getName().endsWith(".xml")) {
                    processXmlFile(file);
                }
            }
        }
    }

    private void processXmlFile(File file) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(StationXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StationXml stationXml = (StationXml) unmarshaller.unmarshal(file);
        Station station = fromXmlToEntity.convertToStation(stationXml);
        System.out.println(station.getShortcode());
        log.info("Station: " + station.getShortcode());
    }

}
