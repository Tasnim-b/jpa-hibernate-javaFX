package org.example;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@Table(name = "destination")
@AllArgsConstructor
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_destination ;
    private String pays;
    private String ville;
    private String description;

}
