package org.earauco.facturaciones;

import org.earauco.facturaciones.entity.Client;
import org.earauco.facturaciones.entity.Product;
import org.earauco.facturaciones.manager.ClientManager;
import org.earauco.facturaciones.manager.GenericManager;
import org.earauco.facturaciones.manager.ProductManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientManager clienteManager = new ClientManager();
        ProductManager productoManager = new ProductManager();

        // Create a new client
        try {
            clienteManager.create("Juan", "Perez", "12345678901");
            System.out.println("Cliente creado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al crear el cliente: " + e.getMessage());
        }

        // Read all clients
        try {
            List<Client> clients = clienteManager.readAll();
            clients.forEach(client -> System.out.println(client.getName() + " " + client.getLastname()));
        } catch (Exception e) {
            System.err.println("Error al leer los clientes: " + e.getMessage());
        }

        // Create a new product
        try {
            productoManager.create("Laptop", "LP123", 10, 1000.0);
            System.out.println("Producto creado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
        }

        // Read all products
        try {
            List<Product> products = productoManager.readAll();
            products.forEach(product -> System.out.println(product.getDescription() + " - " + product.getPrice()));
        } catch (Exception e) {
            System.err.println("Error al leer los productos: " + e.getMessage());
        }

        // Update a client
        try {
            clienteManager.update(1, "Juan", "Lopez", "12345678901");
            System.out.println("Cliente actualizado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }

        // Delete a product
        try {
            productoManager.delete(1);
            System.out.println("Producto eliminado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }

        // Close EntityManagerFactory
        GenericManager.closeEntityManagerFactory();
    }
}
