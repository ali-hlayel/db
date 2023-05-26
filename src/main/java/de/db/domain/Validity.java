package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "validity")
public class Validity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "validity_from")
    private String from;

    @Column(name = "validity_to")
    private String to;
}
