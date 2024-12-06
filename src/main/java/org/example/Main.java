package org.example;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.example.Client;
import org.example.Hotel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //en utilisant hibernate
/*        // Créer une session factory avec la configuration Hibernate
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Assurez-vous que votre fichier hibernate.cfg.xml existe
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Hotel.class) // Ajoutez ici toutes les classes que vous voulez tester
                .buildSessionFactory();

        // Créer une session Hibernate
        Session session = factory.getCurrentSession();

        try {
            // Démarrer une transaction
            session.beginTransaction();

            // Créer un objet Client pour tester la connexion et l'insertion dans la base de données
            Client client = new Client(1, "John", "Doe", "john.doe@example.com", "123456789", "123 Main St");

            session.persist(client);
            // Sauvegarder l'objet dans la base de données
            session.save(client);

            // Commit la transaction
            session.getTransaction().commit();

            System.out.println("Client ajouté avec succès!");

        } finally {
            factory.close();
        }
*/


        ////////////////////
        // Instanciation du DAO générique pour la classe Client
        GenericDAO<Client> clientDAO = new GenericDAO<>(Client.class);

        // Ajouter un client
       /* Client newClient = new Client();
        newClient.setNom("salma");
        newClient.setPrenom("marzouki");
        newClient.setEmail("salma@gmail.com");
        newClient.setTelephone("5323666");
        newClient.setAdresse("123 Main Street");
        clientDAO.add(newClient);*/

        // Récupérer un client par ID
       /* Client client = clientDAO.getById(1); // Remplacez 1 par l'ID réel de l'entité
        if (client != null) {
            System.out.println("Nom du client récupéré : " + client.getNom());
        } else {
            System.out.println("Client introuvable.");
        }*/

     /*   // Supprimer un client
       clientDAO.delete(2); // Remplacez 1 par l'ID que vous souhaitez supprimer
        //System.out.println("Client supprimé.");*/

//afficher la lister des clients
     /*   // Récupérer tous les clients
        List<Client> clients = clientDAO.getAll();
        //vu que la relation @ManyToMany on doit charger aussi la listes des groupe_voyageur
        // Initialiser explicitement la collection 'groupeVoyageurs' pour chaque client
        for (Client client : clients) {
            Hibernate.initialize(client.getGroupeVoyageurs());
        }

        // Afficher les clients récupérés
        System.out.println("Liste des clients : ");
        for (Client client : clients) {
            System.out.println(client);  // Supposant que vous avez une méthode toString() bien définie dans Client
        }*/
        ///////////////tester la metode getByID
       /* long clientId =2;

        // Récupérer le client par son ID
        Client client = clientDAO.getById(clientId);

        // Vérifier si le client a été trouvé
        if (client != null) {
            System.out.println("Client trouvé : " + client);
        } else {
            System.out.println("Client avec l'ID " + clientId + " non trouvé.");
        }*/

        //////////////////teste methode update :
        /*long clientId = 4;

        // Récupérer le client existant
        Client client = clientDAO.getById(clientId);

        // Vérifier si le client existe
        if (client != null) {
            // Mettre à jour les informations du client
            client.setNom("acsa");
            client.setPrenom("test");
            client.setEmail("akjkjkjmlk@gmail.com");
            client.setTelephone("12345678");

            // Appeler la méthode update pour enregistrer les modifications dans la base de données
            clientDAO.update(client);

        }*/










    }





}
