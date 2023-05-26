package de.db.entityXml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TrainXml {

    @XmlElementWrapper(name = "trainNumbers")
    @XmlElement(name = "trainNumber")
    private List<Integer> trainNumbers;

    @XmlElement(name = "anno")
    private String anno;

    @XmlElement(name = "time")
    private String time;

    @XmlElement(name = "additionalText")
    private String additionalText;

    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "subtrains")
    @XmlElement(name = "subtrain")
    private List<SubtrainXml> subtrains;

    @XmlElementWrapper(name = "waggons")
    @XmlElement(name = "waggon")
    private List<WaggonXml> waggons;

    @XmlElementWrapper(name = "traintypes")
    @XmlElement(name = "traintype")
    private List<String> traintypes;
}
