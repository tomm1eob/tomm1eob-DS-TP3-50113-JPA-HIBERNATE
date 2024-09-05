package main;

import entidades.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tp3-50113-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            entityManager.getTransaction().begin();


            entityManager.flush();

            entityManager.getTransaction().commit();


            // Consultar y mostrar la entidad persistida
            // Cliente retrievedPerson = entityManager.find(Cliente.class, cliente1.getId());
            // System.out.println("Retrieved Person: " + retrievedPerson.getNombre());

        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Persona");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
