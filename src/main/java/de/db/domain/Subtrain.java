package de.db.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Subtrain {

    @XmlElement(name = "destination")
    private Destination destination;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "identifier")
    private List<String> sections;
}
