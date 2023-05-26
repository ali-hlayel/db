package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "waggon")
public class Waggon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position")
    private int position;

    @Column(name = "is_waggon")
    private int isWaggon;

    @ElementCollection
    @CollectionTable(name = "waggon_sections", joinColumns = @JoinColumn(name = "waggon_id"))
    @Column(name = "section")
    private List<String> sections;

    @Column(name = "number")
    private Integer number;

    @Column(name = "type")
    private String type;

    @Column(name = "symbols")
    private String symbols;

    @Column(name = "different_destination")
    private String differentDestination;

    @Column(name = "length")
    private int length;}
