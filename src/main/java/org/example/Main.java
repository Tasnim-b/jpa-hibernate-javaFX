package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import org.dao.GenericDAO;

import org.model.Client;


import java.io.IOException;

import javafx.fxml.FXMLLoader;

import static javafx.application.Application.launch;


public class Main extends Application {
    //@Override
    /*public void start(Stage primaryStage) throws IOException {
        // Charger le fichier FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ajouterClient.fxml"));
        if (fxmlLoader.getLocation() == null) {
            System.out.println("Fichier FXML introuvable");
        }
        Scene scene = new Scene(fxmlLoader.load(), 400, 400); // Dimensions de la fenêtre
        primaryStage.setTitle("Ajout d'un Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    public static void main(String[] args) {
        Application.launch(args);






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
        newClient.setNom("tasnouma ");
        newClient.setPrenom("ben mabrouk");
        newClient.setEmail("tasnim@gmail.com");
        newClient.setTelephone("26089341");
        newClient.setAdresse("22 zouhour");
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

   public void start(Stage primaryStage) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuView.fxml"));
      // GridPane root = loader.load();
      BorderPane root = loader.load();
       //VBox root = loader.load();

       Image logo = new Image(getClass().getResourceAsStream("/image/logo2.png"));
       primaryStage.getIcons().add(logo);

       // Créer un ImageView pour afficher le logo dans l'interface
       Image logo1 = new Image(getClass().getResourceAsStream("/image/img2.png"));
       ImageView logoView = new ImageView(logo1);
       logoView.setFitHeight(500);
       logoView.setFitWidth(250);   // Ajustez la taille si nécessaire
       logoView.setPreserveRatio(true);

       // Créer un label de bienvenue
       Label welcomeLabel = new Label("Bienvenue à notre Agence de Voyage ");
       welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

       // Créer un VBox contenant l'image et le message
       VBox welcomeBox = new VBox(10, logoView, welcomeLabel);
       welcomeBox.setAlignment(Pos.CENTER);
       welcomeBox.setStyle("-fx-padding: 10px;"); // Ajoute un peu d'espacement autour

       // Ajouter le VBox contenant l'image et le message dans la partie CENTER du BorderPane
       root.setCenter(welcomeBox);

       Scene scene = new Scene(root, 800, 600);
      // scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
       primaryStage.setTitle("Gestion Agence de Voyage");
       primaryStage.setScene(scene);
       primaryStage.show();
   }






}
