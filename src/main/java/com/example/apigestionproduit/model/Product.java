package com.example.apigestionproduit.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Classe représentant un produit dans le système de gestion.
 * Cette entité contient toutes les informations nécessaires pour décrire un produit.
 *
 * @author DJAKOU Ruben
 * @version 1.0
 * @since 2025-11-15
 */
public class Product {

    /**
     * Identifiant unique du produit.
     * Généré automatiquement lors de la création.
     */
    private Long id;

    /**
     * Nom du produit.
     * Ne peut pas être null ou vide.
     */
    private String nom;

    /**
     * Description détaillée du produit.
     * Peut contenir des informations supplémentaires sur les caractéristiques.
     */
    private String description;

    /**
     * Prix unitaire du produit en devise locale.
     * Doit être supérieur à zéro.
     */
    private BigDecimal prix;

    /**
     * Quantité disponible en stock.
     * Valeur par défaut : 0
     */
    private Integer quantiteStock;

    /**
     * Catégorie du produit (ex: Électronique, Alimentaire, etc.).
     */
    private String categorie;

    /**
     * Date de création de l'enregistrement du produit.
     */
    private LocalDateTime dateCreation;

    /**
     * Date de dernière modification du produit.
     */
    private LocalDateTime dateModification;

    /**
     * Constructeur par défaut.
     * Initialise la date de création à la date actuelle.
     */
    public Product() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
        this.quantiteStock = 0;
    }

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param id Identifiant du produit
     * @param nom Nom du produit
     * @param description Description du produit
     * @param prix Prix du produit
     * @param quantiteStock Quantité en stock
     * @param categorie Catégorie du produit
     */
    public Product(Long id, String nom, String description, BigDecimal prix,
                   Integer quantiteStock, String categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.categorie = categorie;
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
    }

    // === GETTERS ET SETTERS ===

    /**
     * Récupère l'identifiant du produit.
     * @return l'identifiant unique du produit
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du produit.
     * @param id l'identifiant à attribuer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère le nom du produit.
     * @return le nom du produit
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du produit.
     * @param nom le nom à attribuer
     */
    public void setNom(String nom) {
        this.nom = nom;
        this.dateModification = LocalDateTime.now();
    }

    /**
     * Récupère la description du produit.
     * @return la description du produit
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du produit.
     * @param description la description à attribuer
     */
    public void setDescription(String description) {
        this.description = description;
        this.dateModification = LocalDateTime.now();
    }

    /**
     * Récupère le prix du produit.
     * @return le prix du produit
     */
    public BigDecimal getPrix() {
        return prix;
    }

    /**
     * Définit le prix du produit.
     * @param prix le prix à attribuer
     */
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
        this.dateModification = LocalDateTime.now();
    }

    /**
     * Récupère la quantité en stock.
     * @return la quantité disponible
     */
    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    /**
     * Définit la quantité en stock.
     * @param quantiteStock la quantité à attribuer
     */
    public void setQuantiteStock(Integer quantiteStock) {
        this.quantiteStock = quantiteStock;
        this.dateModification = LocalDateTime.now();
    }

    /**
     * Récupère la catégorie du produit.
     * @return la catégorie du produit
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Définit la catégorie du produit.
     * @param categorie la catégorie à attribuer
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
        this.dateModification = LocalDateTime.now();
    }

    /**
     * Récupère la date de création.
     * @return la date de création du produit
     */
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    /**
     * Récupère la date de dernière modification.
     * @return la date de dernière modification
     */
    public LocalDateTime getDateModification() {
        return dateModification;
    }

    /**
     * Met à jour la date de modification à la date actuelle.
     */
    public void updateDateModification() {
        this.dateModification = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", quantiteStock=" + quantiteStock +
                ", categorie='" + categorie + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                '}';
    }
}