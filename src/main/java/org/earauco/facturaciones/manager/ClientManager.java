package org.earauco.facturaciones.manager;

import jakarta.persistence.EntityManager;
import org.earauco.facturaciones.entity.Client;

import java.util.List;

public class ClientManager {

    public void create(String name, String lastname, String docnumber) {
        EntityManager entityManager = GenericManager.getEntityManager();
        entityManager.getTransaction().begin();
        Client client = new Client();
        client.setName(name);
        client.setLastname(lastname);
        client.setDocnumber(docnumber);
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Client> readAll() {
        EntityManager entityManager = GenericManager.getEntityManager();
        List<Client> clients = entityManager.createQuery("FROM Client", Client.class).getResultList();
        entityManager.close();
        return clients;
    }

    public Client readById(Integer id) {
        EntityManager entityManager = GenericManager.getEntityManager();
        Client client = entityManager.find(Client.class, id);
        entityManager.close();
        return client;
    }

    public void update(Integer id, String name, String lastname, String docnumber) {
        EntityManager entityManager = GenericManager.getEntityManager();
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            if (name != null) { client.setName(name); }
            if (lastname != null) { client.setLastname(lastname); }
            if (docnumber != null) { client.setDocnumber(docnumber); }
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    public void delete(Integer id) {
        EntityManager entityManager = GenericManager.getEntityManager();
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
}

