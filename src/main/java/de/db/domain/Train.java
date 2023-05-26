package de.db.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "train_train_numbers", joinColumns = @JoinColumn(name = "train_id"))
    @Column(name = "train_number")
    private List<Integer> trainNumbers;

    @Column(name = "anno")
    private String anno;

    @Column(name = "time")
    private String time;

    @Column(name = "additional_text", length = 500)
    private String additionalText;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "train_id")
    private List<Subtrain> subtrains;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "train_id")
    private List<Waggon> waggons;

    @ElementCollection
    @CollectionTable(name = "train_traintypes", joinColumns = @JoinColumn(name = "train_id"))
    @Column(name = "traintype")
    private List<String> traintypes;
}
