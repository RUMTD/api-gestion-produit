package com.example.apigestionproduit.controller;
import com.example.apigestionproduit.model.Product;
import com.example.apigestionproduit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des produits.
 * Expose les endpoints de l'API CRUD pour manipuler les produits.
 *
 * Base URL: /api/products
 *
 * <p>Endpoints disponibles:</p>
 * <ul>
 *   <li>GET    /api/products           - Liste tous les produits</li>
 *   <li>GET    /api/products/{id}      - Récupère un produit par ID</li>
 *   <li>POST   /api/products           - Crée un nouveau produit</li>
 *   <li>PUT    /api/products/{id}      - Met à jour un produit</li>
 *   <li>DELETE /api/products/{id}      - Supprime un produit</li>
 *   <li>GET    /api/products/search    - Recherche par nom</li>
 *   <li>GET    /api/products/categorie/{categorie} - Filtre par catégorie</li>
 * </ul>
 *
 * @author Votre Nom
 * @version 1.0
 * @since 2025-01-01
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    /**
     * Service de gestion des produits injecté par Spring.
     */
    private final ProductService productService;

    /**
     * Constructeur avec injection de dépendances.
     *
     * @param productService Service de gestion des produits
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Récupère la liste complète de tous les produits.
     *
     * <p><b>Endpoint:</b> GET /api/products</p>
     *
     * @return ResponseEntity contenant la liste des produits (200 OK)
     *
     * <p><b>Exemple de réponse:</b></p>
     * <pre>
     * [
     *   {
     *     "id": 1,
     *     "nom": "Ordinateur Portable",
     *     "description": "PC portable...",
     *     "prix": 899.99,
     *     "quantiteStock": 10,
     *     "categorie": "Électronique"
     *   }
     * ]
     * </pre>
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Récupère un produit spécifique par son identifiant.
     *
     * <p><b>Endpoint:</b> GET /api/products/{id}</p>
     *
     * @param id Identifiant du produit recherché
     * @return ResponseEntity avec le produit (200 OK) ou 404 NOT FOUND
     *
     * <p><b>Codes de retour:</b></p>
     * <ul>
     *   <li>200 OK - Produit trouvé</li>
     *   <li>404 NOT FOUND - Produit inexistant</li>
     * </ul>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getProductById(id);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau produit dans le système.
     *
     * <p><b>Endpoint:</b> POST /api/products</p>
     * <p><b>Content-Type:</b> application/json</p>
     *
     * @param product Données du produit à créer (dans le corps de la requête)
     * @return ResponseEntity avec le produit créé (201 CREATED)
     *
     * <p><b>Exemple de corps de requête:</b></p>
     * <pre>
     * {
     *   "nom": "Nouveau Produit",
     *   "description": "Description du produit",
     *   "prix": 49.99,
     *   "quantiteStock": 100,
     *   "categorie": "Électronique"
     * }
     * </pre>
     *
     * <p><b>Note:</b> L'ID sera généré automatiquement et ne doit pas être fourni.</p>
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Met à jour intégralement un produit existant.
     * Tous les champs du produit seront remplacés.
     *
     * <p><b>Endpoint:</b> PUT /api/products/{id}</p>
     * <p><b>Content-Type:</b> application/json</p>
     *
     * @param id Identifiant du produit à modifier
     * @param productDetails Nouvelles données du produit
     * @return ResponseEntity avec le produit mis à jour (200 OK) ou 404 NOT FOUND
     *
     * <p><b>Codes de retour:</b></p>
     * <ul>
     *   <li>200 OK - Produit mis à jour avec succès</li>
     *   <li>404 NOT FOUND - Produit inexistant</li>
     * </ul>
     *
     * <p><b>Exemple de corps de requête:</b></p>
     * <pre>
     * {
     *   "nom": "Produit Modifié",
     *   "description": "Nouvelle description",
     *   "prix": 59.99,
     *   "quantiteStock": 75,
     *   "categorie": "Accessoires"
     * }
     * </pre>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product productDetails) {

        Optional<Product> updatedProduct = productService.updateProduct(id, productDetails);

        return updatedProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Supprime un produit du système.
     *
     * <p><b>Endpoint:</b> DELETE /api/products/{id}</p>
     *
     * @param id Identifiant du produit à supprimer
     * @return ResponseEntity vide (204 NO CONTENT) ou 404 NOT FOUND
     *
     * <p><b>Codes de retour:</b></p>
     * <ul>
     *   <li>204 NO CONTENT - Produit supprimé avec succès</li>
     *   <li>404 NOT FOUND - Produit inexistant</li>
     * </ul>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Recherche des produits par terme dans le nom.
     * La recherche est insensible à la casse.
     *
     * <p><b>Endpoint:</b> GET /api/products/search?nom={terme}</p>
     *
     * @param nom Terme à rechercher dans le nom des produits
     * @return ResponseEntity avec la liste des produits correspondants (200 OK)
     *
     * <p><b>Exemple d'utilisation:</b></p>
     * <code>GET /api/products/search?nom=ordinateur</code>
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam("nom") String nom) {

        List<Product> products = productService.searchProductsByNom(nom);
        return ResponseEntity.ok(products);
    }

    /**
     * Récupère tous les produits d'une catégorie spécifique.
     *
     * <p><b>Endpoint:</b> GET /api/products/categorie/{categorie}</p>
     *
     * @param categorie Nom de la catégorie
     * @return ResponseEntity avec la liste des produits de cette catégorie (200 OK)
     *
     * <p><b>Exemple d'utilisation:</b></p>
     * <code>GET /api/products/categorie/Électronique</code>
     */
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Product>> getProductsByCategorie(
            @PathVariable("categorie") String categorie) {

        List<Product> products = productService.getProductsByCategorie(categorie);
        return ResponseEntity.ok(products);
    }

    /**
     * Vérifie l'état de santé de l'API et retourne des statistiques.
     *
     * <p><b>Endpoint:</b> GET /api/products/health</p>
     *
     * @return ResponseEntity avec le nombre total de produits (200 OK)
     *
     * <p><b>Exemple de réponse:</b></p>
     * <pre>
     * {
     *   "status": "UP",
     *   "totalProducts": 42
     * }
     * </pre>
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        long count = productService.count();
        return ResponseEntity.ok(
                String.format("{\"status\":\"UP\",\"totalProducts\":%d}", count)
        );
    }
}