package de.db.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "number")
    private int number;

    @XmlElementWrapper(name = "trains")
    @XmlElement(name = "train")
    private List<Train> trains;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "identifier")
    private List<String> sections;
}
