package com.example.monpetitterroir.service;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.Seller;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseService {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String TAG = "firebase";
    public List<Recipe> recipes = new ArrayList<>();
    public List<Seller> sellers = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Recipe> listRecipes() {
        CompletableFuture.runAsync(() -> {
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
                    } else {
                        Log.d(TAG, "Error getting recipes: ", task.getException());
                    }
                });
        });
        return this.recipes;
    }

    public Recipe getRecipe(String id) {
        AtomicReference<Recipe> recipe = new AtomicReference<>(new Recipe());
        db.collection("recipes").document(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    List<Ingredient> listIngredient = new ArrayList<>();
                    Ingredient i = new Ingredient(1, "carrote", "https://cdn1.fermedesaintemarthe.com/I-Autre-26000_1200x1200-carotte-amsterdam-2-ab.net.jpg");
                    listIngredient.add(i);

                    recipe.set(new Recipe(
                            document.getId(),
                            document.getData().get("img").toString(),
                            document.getData().get("likes").toString(),
                            document.getData().get("title").toString(),
                            listIngredient
                    ));
                }
            }
        });
        return recipe.get();
    }

    public List<Seller> listSellers() {
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
                } else {
                    Log.d(TAG, "Error getting sellers: ", task.getException());
                }
            });
        return this.sellers;
    }
}
