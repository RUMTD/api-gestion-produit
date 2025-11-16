package com.example.apigestionproduit.service;

import com.example.apigestionproduit.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service de gestion des produits.
 * Implémente la logique métier pour les opérations CRUD sur les produits.
 *
 * Cette classe utilise une Map en mémoire pour stocker les produits.
 * En production, elle devrait être remplacée par une persistance en base de données.
 *
 * @author DJAKOU Ruben
 * @version 1.0
 * @since 2025-11-15
 */
@Service
public class ProductService {

    /**
     * Stockage en mémoire des produits.
     * Utilise ConcurrentHashMap pour la sécurité des threads.
     */
    private final Map<Long, Product> productsDB = new ConcurrentHashMap<>();

    /**
     * Générateur d'identifiants uniques pour les produits.
     */
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Constructeur initialisant quelques produits de démonstration.
     */
    public ProductService() {
        initializeDemoData();
    }

    /**
     * Initialise des données de démonstration au démarrage.
     * Crée 3 produits exemple pour faciliter les tests.
     */
    private void initializeDemoData() {
        createProduct(new Product(null, "Ordinateur Portable",
                "PC portable 15 pouces, 16GB RAM, SSD 512GB",
                new BigDecimal("899.99"), 10, "Électronique"));

        createProduct(new Product(null, "Souris Sans Fil",
                "Souris ergonomique Bluetooth avec 6 boutons",
                new BigDecimal("29.99"), 50, "Accessoires"));

        createProduct(new Product(null, "Clavier Mécanique",
                "Clavier gaming RGB avec switches Cherry MX",
                new BigDecimal("129.99"), 25, "Accessoires"));
    }

    /**
     * Récupère tous les produits disponibles.
     *
     * @return Liste de tous les produits
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productsDB.values());
    }

    /**
     * Récupère un produit par son identifiant.
     *
     * @param id Identifiant du produit recherché
     * @return Optional contenant le produit si trouvé, vide sinon
     */
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(productsDB.get(id));
    }

    /**
     * Crée un nouveau produit dans le système.
     * Génère automatiquement un identifiant unique.
     *
     * @param product Produit à créer (l'ID sera ignoré et régénéré)
     * @return Le produit créé avec son nouvel identifiant
     */
    public Product createProduct(Product product) {
        // Génération d'un nouvel ID
        Long newId = idGenerator.getAndIncrement();
        product.setId(newId);

        // Sauvegarde dans la "base de données" mémoire
        productsDB.put(newId, product);

        return product;
    }

    /**
     * Met à jour un produit existant.
     * Tous les champs du produit sont mis à jour.
     *
     * @param id Identifiant du produit à modifier
     * @param productDetails Nouvelles informations du produit
     * @return Optional contenant le produit mis à jour si trouvé, vide sinon
     */
    public Optional<Product> updateProduct(Long id, Product productDetails) {
        Product existingProduct = productsDB.get(id);

        if (existingProduct == null) {
            return Optional.empty();
        }

        // Mise à jour des champs
        existingProduct.setNom(productDetails.getNom());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrix(productDetails.getPrix());
        existingProduct.setQuantiteStock(productDetails.getQuantiteStock());
        existingProduct.setCategorie(productDetails.getCategorie());
        existingProduct.updateDateModification();

        // Sauvegarde des modifications
        productsDB.put(id, existingProduct);

        return Optional.of(existingProduct);
    }

    /**
     * Supprime un produit du système.
     *
     * @param id Identifiant du produit à supprimer
     * @return true si le produit a été supprimé, false s'il n'existait pas
     */
    public boolean deleteProduct(Long id) {
        return productsDB.remove(id) != null;
    }

    /**
     * Recherche des produits par catégorie.
     *
     * @param categorie Catégorie à rechercher
     * @return Liste des produits appartenant à cette catégorie
     */
    public List<Product> getProductsByCategorie(String categorie) {
        return productsDB.values().stream()
                .filter(p -> p.getCategorie() != null &&
                        p.getCategorie().equalsIgnoreCase(categorie))
                .toList();
    }

    /**
     * Recherche des produits dont le nom contient le terme recherché.
     * La recherche est insensible à la casse.
     *
     * @param nom Terme à rechercher dans le nom
     * @return Liste des produits correspondants
     */
    public List<Product> searchProductsByNom(String nom) {
        return productsDB.values().stream()
                .filter(p -> p.getNom() != null &&
                        p.getNom().toLowerCase().contains(nom.toLowerCase()))
                .toList();
    }

    /**
     * Vérifie si un produit existe dans le système.
     *
     * @param id Identifiant du produit à vérifier
     * @return true si le produit existe, false sinon
     */
    public boolean existsById(Long id) {
        return productsDB.containsKey(id);
    }

    /**
     * Retourne le nombre total de produits.
     *
     * @return Nombre de produits dans le système
     */
    public long count() {
        return productsDB.size();
    }
}