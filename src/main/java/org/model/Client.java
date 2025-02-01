package org.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Table(name = "client")// Spécifie que cette entité sera mappée à la table "client" dans la base de données
@Entity // Indique que la classe est une entité JPA, donc elle sera mappée à une table dans la base de données
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
    @ManyToMany(fetch = FetchType.EAGER) // Déclare une relation Many-to-Many avec la classe GroupeVoyageur
   @JoinColumn(name = "id_groupe") // Clé étrangère vers GroupeVoyageur
    private List<GroupeVoyageur> groupeVoyageurs = new ArrayList<>();
    public Client(String nom, String prenom, String email, String telephone, String adresse) {
        this.Nom = nom;
        this.Prenom = prenom;
        this.Email = email;
        this.Telephone = telephone;
        this.Adresse = adresse;
    }
}
