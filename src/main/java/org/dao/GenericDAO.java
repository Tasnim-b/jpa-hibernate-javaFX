package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor

public class GenericDAO<T> {//T est un paramètre de type qui représente le type d'objet que vous allez manipuler dans la classe GenericDAO
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectJaveUnity");//crée une entityManager de notre entité de persistence
    private Class<T> entityClass;//une référence à la classe d'entité spécifique qui sera manipulée




    public void add(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // Démarrage de la transaction
            em.persist(entity); // enregistrer l'entité ajouté
            em.getTransaction().commit(); // Validation de la transaction
        } catch (Exception e) {//en cas d'echec
            if (em.getTransaction().isActive()) {//vérif ajout
                em.getTransaction().rollback(); // En cas d'erreur, annulation
            }
            e.printStackTrace();//en cas d'échec elle permet d'annuler toute modif
        } finally {
            em.close(); // Libérer les ressources
        }
    }

    public T getById(Object id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entityClass, id);//recherche by id
        } finally {
            em.close();
        }
    }

    public void update(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Object id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<T> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        } finally {
            em.close();
        }
    }

}
