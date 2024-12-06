package org.example;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Table(name = "client")
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long Id_Client;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Telephone;
    private String Adresse;
    @ManyToMany(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_groupe") // Clé étrangère vers voyage
    private List<GroupeVoyageur> groupeVoyageurs;
}
