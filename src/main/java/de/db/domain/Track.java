package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "track_id")
    private List<Train> trains;

    @ElementCollection
    @CollectionTable(name = "track_sections", joinColumns = @JoinColumn(name = "track_id"))
    @Column(name = "section")
    private List<String> sections;
}
