package com.example.monpetitterroir.service;

import android.util.Log;

import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.Seller;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseService {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String TAG = "firebase";
    public List<Recipe> recipes = new ArrayList<>();
    public List<Seller> sellers = new ArrayList<>();

    /**
     * List all recipes
     * @param callback
     */
    public void listRecipes(RecipesCallback callback) {
        db.collection("recipes")
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Ingredient> listIngredient = new ArrayList<>();
                    for (DocumentChange document : task.getResult().getDocumentChanges()) {
                        Ingredient i = new Ingredient(1, "carrote", "https://cdn1.fermedesaintemarthe.com/I-Autre-26000_1200x1200-carotte-amsterdam-2-ab.net.jpg");
                        listIngredient.add(i);
                        QueryDocumentSnapshot myDocument = document.getDocument();
                        Recipe recipe = new Recipe(
                            document.getDocument().getId(),
                            myDocument.getData().get("img").toString(),
                            myDocument.getData().get("title").toString(),
                            myDocument.getData().get("likes").toString(),
                            listIngredient
                        );
                        this.recipes.add(recipe);
                    }
                    callback.onReceive(recipes);
                } else {
                    Log.d(TAG, "Error getting recipes: ", task.getException());
                }
            });
    }

    /**
     * Return the recipe with specified ID
     * @param id
     */
    public void getRecipe(String id, RecipeCallback callback) {
        db.collection("recipes").document(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<Ingredient> listIngredient = new ArrayList<>();
                    Ingredient i = new Ingredient(1, "carrote", "https://cdn1.fermedesaintemarthe.com/I-Autre-26000_1200x1200-carotte-amsterdam-2-ab.net.jpg");
                    listIngredient.add(i);
                    callback.onReceive(new Recipe(
                            document.getId(),
                            document.getData().get("img").toString(),
                            document.getData().get("likes").toString(),
                            document.getData().get("title").toString(),
                            listIngredient
                    ));
                }
            }
        });
    }

    /**
     * Return list of sellers
     */
    public void listSellers(SellersCallback callback) {
        db.collection("seller")
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentChange document : task.getResult().getDocumentChanges()) {
                        QueryDocumentSnapshot myDocument = document.getDocument();
                        Seller seller = new Seller(
                            document.getDocument().getId(),
                            myDocument.get("name").toString(),
                            myDocument.get("city").toString(),
                            myDocument.get("cp").toString()
                        );
                        this.sellers.add(seller);
                    }
                    callback.onReceive(sellers);
                } else {
                    Log.d(TAG, "Error getting sellers: ", task.getException());
                }
            });
    }

    /**
     * Callbacks after receive from firestore
     */
    public interface RecipesCallback{
        void onReceive(List<Recipe> recipes);
    }
    public interface RecipeCallback{
        void onReceive(Recipe recipe);
    }
    public interface SellersCallback{
        void onReceive(List<Seller> sellers);
    }
}
