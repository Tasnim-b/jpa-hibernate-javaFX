package org.example;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long Id_voyage;
    private String nom;
    private String email;
    private String telephone;
    private Specialite specialite;

}
