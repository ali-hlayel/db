package de.db.entityXml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackXml {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "number")
    private int number;

    @XmlElementWrapper(name = "trains")
    @XmlElement(name = "train")
    private List<TrainXml> trains;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "identifier")
    private List<String> sections;
}
