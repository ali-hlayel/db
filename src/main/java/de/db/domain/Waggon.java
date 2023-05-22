package de.db.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Waggon {

    @XmlElement(name = "position")
    private int position;

    @XmlElement(name = "isWaggon")
    private int isWaggon;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "identifier")
    private List<String> sections;

    @XmlElement(name = "number")
    private Integer number;

    @XmlElement(name = "type")
    private String type;

    @XmlElement(name = "symbols")
    private String symbols;

    @XmlElement(name = "differentDestination")
    private String differentDestination;

    @XmlElement(name = "length")
    private int length;

}
