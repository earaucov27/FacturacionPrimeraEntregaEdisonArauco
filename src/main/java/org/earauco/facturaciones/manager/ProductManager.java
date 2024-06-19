package org.earauco.facturaciones.manager;

import jakarta.persistence.EntityManager;
import org.earauco.facturaciones.entity.Product;

import java.util.List;

public class ProductManager {

    public void create(String description, String code, int stock, double price) {
        EntityManager entityManager = GenericManager.getEntityManager();
        entityManager.getTransaction().begin();
        Product product = new Product();
        product.setDescription(description);
        product.setCode(code);
        product.setStock(stock);
        product.setPrice(price);
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Product> readAll() {
        EntityManager entityManager = GenericManager.getEntityManager();
        List<Product> products = entityManager.createQuery("FROM Product", Product.class).getResultList();
        entityManager.close();
        return products;
    }

    public Product readById(Integer id) {
        EntityManager entityManager = GenericManager.getEntityManager();
        Product product = entityManager.find(Product.class, id);
        entityManager.close();
        return product;
    }

    public void update(Integer id, String description, String code, int stock, double price) {
        EntityManager entityManager = GenericManager.getEntityManager();
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            if (description != null) { product.setDescription(description); }
            if (code != null) { product.setCode(code); }
            if (stock >= 0) { product.setStock(stock); }
            if (price >= 0) { product.setPrice(price); }
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }

    public void delete(Integer id) {
        EntityManager entityManager = GenericManager.getEntityManager();
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
}

