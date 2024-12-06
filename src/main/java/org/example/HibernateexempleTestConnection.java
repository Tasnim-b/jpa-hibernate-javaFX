package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateexempleTestConnection {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectJaveUnity");
        EntityManager em = emf.createEntityManager();
        System.out.println("Connexion réussie !");
        em.close();
        emf.close();
    }
}
