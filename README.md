📡 Endpoints de l'API
Base URL
http://localhost:8080/api/products
Liste des Endpoints
MéthodeEndpointDescriptionGET/api/productsRécupère tous les produitsGET/api/products/{id}Récupère un produit par IDPOST/api/productsCrée un nouveau produitPUT/api/products/{id}Met à jour un produitDELETE/api/products/{id}Supprime un produitGET/api/products/search?nom={terme}Recherche par nomGET/api/products/categorie/{categorie}Filtre par catégorieGET/api/products/healthÉtat de l'API

📝 Exemples d'Utilisation
1. Récupérer tous les produits
Requête:
httpGET http://localhost:8080/api/products
Réponse: (200 OK)
json[
  {
    "id": 1,
    "nom": "Ordinateur Portable",
    "description": "PC portable 15 pouces, 16GB RAM, SSD 512GB",
    "prix": 899.99,
    "quantiteStock": 10,
    "categorie": "Électronique",
    "dateCreation": "2025-01-01T10:30:00",
    "dateModification": "2025-01-01T10:30:00"
  }
]
2. Créer un nouveau produit
Requête:
httpPOST http://localhost:8080/api/products
Content-Type: application/json

{
  "nom": "Smartphone Android",
  "description": "Écran OLED 6.5 pouces, 128GB",
  "prix": 599.99,
  "quantiteStock": 25,
  "categorie": "Électronique"
}
Réponse: (201 CREATED)
json{
  "id": 4,
  "nom": "Smartphone Android",
  "description": "Écran OLED 6.5 pouces, 128GB",
  "prix": 599.99,
  "quantiteStock": 25,
  "categorie": "Électronique",
  "dateCreation": "2025-01-01T14:22:10",
  "dateModification": "2025-01-01T14:22:10"
}
3. Mettre à jour un produit
Requête:
httpPUT http://localhost:8080/api/products/1
Content-Type: application/json

{
  "nom": "Ordinateur Portable Pro",
  "description": "PC portable 15 pouces, 32GB RAM, SSD 1TB",
  "prix": 1299.99,
  "quantiteStock": 5,
  "categorie": "Électronique"
}
Réponse: (200 OK)
4. Supprimer un produit
Requête:
httpDELETE http://localhost:8080/api/products/1
Réponse: (204 NO CONTENT)
5. Rechercher des produits
Requête:
httpGET http://localhost:8080/api/products/search?nom=ordinateur
Réponse: (200 OK) - Liste des produits contenant "ordinateur"
