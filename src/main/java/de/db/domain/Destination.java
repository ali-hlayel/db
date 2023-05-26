package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination_name")
    private String destinationName;

    @ElementCollection
    @CollectionTable(name = "destination_via", joinColumns = @JoinColumn(name = "destination_id"))
    @Column(name = "via")
    private List<String> destinationVia;
}