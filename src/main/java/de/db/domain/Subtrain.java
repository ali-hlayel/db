package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "subtrain")
public class Subtrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ElementCollection
    @CollectionTable(name = "subtrain_sections", joinColumns = @JoinColumn(name = "subtrain_id"))
    @Column(name = "section")
    private List<String> sections;
}
