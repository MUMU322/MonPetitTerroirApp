package com.example.monpetitterroir.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.Recipe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FirebaseService {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String TAG = "firebase";
    public List<Recipe> recipes = new ArrayList<>();

    public List<Recipe> listRecipes() {
        db.collection("recipes")
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (DocumentChange document : task.getResult().getDocumentChanges()) {
                        List<Ingredient> listIngredient = new ArrayList<>();
                        Ingredient i = new Ingredient(1, "carrote", "https://cdn1.fermedesaintemarthe.com/I-Autre-26000_1200x1200-carotte-amsterdam-2-ab.net.jpg");
                        listIngredient.add(i);
                        Recipe recipe = new Recipe(
                                document.getDocument().getId(),
                                Objects.requireNonNull(document.getDocument().getData().get("img")).toString(),
                                Objects.requireNonNull(document.getDocument().getData().get("title")).toString(),
                                Integer.parseInt(Objects.requireNonNull(document.getDocument().getData().get("likes")).toString()),
                                listIngredient
                        );
                        this.recipes.add(recipe);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
        });
        return this.recipes;
    }
}
