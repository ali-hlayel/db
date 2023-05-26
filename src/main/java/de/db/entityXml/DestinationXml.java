package de.db.entityXml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DestinationXml {

    @XmlElement(name = "destinationName")
    private String destinationName;

    @XmlElementWrapper(name = "destinationVia")
    @XmlElement(name = "item")
    private List<String> destinationVia;
}