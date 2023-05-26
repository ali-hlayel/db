package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identifier")
    private String identifier;
}
