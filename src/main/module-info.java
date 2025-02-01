module projet_java {
    requires javafx.controls;   // Nécessaire pour les contrôles JavaFX
    requires javafx.fxml;       // Si tu utilises FXML pour l'interface graphique
    requires java.persistence;  // JPA pour l'accès aux données
    requires hibernate.core;    // Hibernate pour la gestion de la persistance
    requires mysql.connector.java; // Connexion à MySQL

    exports org.example;         // Exporter le package contenant ta classe principale (MainFX)
    exports org.controllers;     // Exporter le package contenant tes contrôleurs JavaFX
    opens org.example to javafx.fxml; // Ouvre le package pour l'accès à FXML
}
