package de.db.entityXml;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "station")
@XmlAccessorType(XmlAccessType.FIELD)
public class StationXml {

    @XmlElement(name = "shortcode")
    private String shortcode;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "validity")
    private ValidityXml validity;

    @XmlElementWrapper(name = "tracks")
    @XmlElement(name = "track")
    private List<TrackXml> tracks;
}
