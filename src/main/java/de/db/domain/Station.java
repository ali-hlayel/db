package de.db.domain;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "station")
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {

    @XmlElement(name = "shortcode")
    private String shortcode;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "validity")
    private Validity validity;

    @XmlElementWrapper(name = "tracks")
    @XmlElement(name = "track")
    private List<Track> tracks;
}
