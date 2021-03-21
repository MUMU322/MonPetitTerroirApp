package com.example.monpetitterroir.service;

import android.util.Log;

import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.Seller;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseService {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String TAG = "firebase";
    public List<Recipe> recipes = new ArrayList<>();
    public List<Seller> sellers = new ArrayList<>();

    public List<Recipe> listRecipes() {
        db.collection("recipes")
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Ingredient> listIngredient = new ArrayList<>();
                    for (DocumentChange document : task.getResult().getDocumentChanges()) {
                        Ingredient i = new Ingredient("1", "carrote", "https://cdn1.fermedesaintemarthe.com/I-Autre-26000_1200x1200-carotte-amsterdam-2-ab.net.jpg");
                        listIngredient.add(i);
                        QueryDocumentSnapshot myDocument = document.getDocument();
                        /*Recipe recipe = new Recipe(
                            document.getDocument().getId(),
                            myDocument.getData().get("img").toString(),
                            myDocument.getData().get("title").toString(),
                            Integer.parseInt(myDocument.getData().get("likes").toString()),
                            listIngredient
                        );*/
                     //   this.recipes.add(recipe);
                    }
                } else {
                    Log.d(TAG, "Error getting recipes: ", task.getException());
                }
        });
        return this.recipes;
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
