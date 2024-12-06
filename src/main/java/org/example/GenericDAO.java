package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class GenericDAO<T> {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectJaveUnity");
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    public void add(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // Démarrage de la transaction
            em.persist(entity); // Persistance de l'entité
            em.getTransaction().commit(); // Validation de la transaction
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // En cas d'erreur, rollback
            }
            e.printStackTrace();
        } finally {
            em.close(); // Libérer les ressources
        }
    }

    public T getById(Object id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entityClass, id);
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
