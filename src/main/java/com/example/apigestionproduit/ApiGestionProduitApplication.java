package com.example.apigestionproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiGestionProduitApplication {

    /**
     * Classe principale de l'application Spring Boot.
     * Point d'entrée pour le démarrage de l'API REST de gestion des produits.
     *
     * <p>Cette application expose une API RESTful CRUD complète pour la gestion
     * de produits avec les fonctionnalités suivantes:</p>
     * <ul>
     *   <li>Création de nouveaux produits</li>
     *   <li>Lecture et consultation des produits</li>
     *   <li>Mise à jour des informations produit</li>
     *   <li>Suppression de produits</li>
     *   <li>Recherche et filtrage par nom et catégorie</li>
     * </ul>
     *
     * <p><b>Configuration par défaut:</b></p>
     * <ul>
     *   <li>Port: 8080</li>
     *   <li>Context Path: /</li>
     *   <li>Base URL API: http://localhost:8080/api/products</li>
     * </ul>
     *
     * <p><b>Technologies utilisées:</b></p>
     * <ul>
     *   <li>Spring Boot 3.x</li>
     *   <li>Spring Web (REST)</li>
     *   <li>Stockage en mémoire (sans base de données)</li>
     * </ul>
     *
     * @author DJAKOU Ruben
     * @version 1.0
     * @since 2025-11-15
     */
    /**
     * Méthode principale qui démarre l'application Spring Boot.
     *
     * <p>Au démarrage, l'application:</p>
     * <ol>
     *   <li>Initialise le contexte Spring</li>
     *   <li>Configure les composants (Controllers, Services)</li>
     *   <li>Démarre le serveur web embarqué (Tomcat par défaut)</li>
     *   <li>Initialise des données de démonstration</li>
     * </ol>
     *
     * @param args Arguments de ligne de commande (optionnels)
     *
     * <p><b>Exemple de démarrage:</b></p>
     * <pre>
     * java -jar produit-api.jar
     * </pre>
     *
     * <p><b>Exemple avec port personnalisé:</b></p>
     * <pre>
     * java -jar produit-api.jar --server.port=9090
     * </pre>
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGestionProduitApplication.class, args);
        // Message de démarrage informatif
        System.out.println("\n========================================");
        System.out.println("  API REST Gestion Produits - DÉMARRÉE");
        System.out.println("========================================");
        System.out.println("URL de base: http://localhost:8080/api/products");
        System.out.println("\nEndpoints disponibles:");
        System.out.println("  GET    /api/products              - Liste tous les produits");
        System.out.println("  GET    /api/products/{id}         - Récupère un produit");
        System.out.println("  POST   /api/products              - Crée un produit");
        System.out.println("  PUT    /api/products/{id}         - Met à jour un produit");
        System.out.println("  DELETE /api/products/{id}         - Supprime un produit");
        System.out.println("  GET    /api/products/search?nom=  - Recherche par nom");
        System.out.println("  GET    /api/products/categorie/{} - Filtre par catégorie");
        System.out.println("  GET    /api/products/health       - État de l'API");
        System.out.println("========================================\n");
    }
}
