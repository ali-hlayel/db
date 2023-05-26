package de.db.entityXml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidityXml {

    @XmlElement(name = "from")
    private String from;

    @XmlElement(name = "to")
    private String to;
}
