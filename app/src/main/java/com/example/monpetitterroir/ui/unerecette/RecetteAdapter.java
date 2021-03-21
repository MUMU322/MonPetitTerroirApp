package com.example.monpetitterroir.ui.unerecette;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.monpetitterroir.R;
import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.Recipe;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RecetteAdapter extends RecyclerView.Adapter<RecetteAdapter.RecetteViewHolder>{

    /**
     * L'arrayList des ingredients
     */
    private List<Ingredient> listeIngredients;

    /**
     * Constructeur de l'adapteur
     * @param al l'arraylist des ingredients
     */
    public RecetteAdapter(ArrayList<Ingredient> al){
        this.listeIngredients=al;
    }


    /**
     * Methode onCreateViewHolder du reyclerAdapter
     * @param parent    View group parent
     * @param viewType  Viex type
     * @return RecetteViewHolder, un item de la recyclerView
     */
    @NotNull
    @Override
    public RecetteAdapter.RecetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Création de la vue
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_recette, parent, false);
        return new RecetteAdapter.RecetteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetteViewHolder holder, int position) {
        if (listeIngredients.get(position) != null) {
            // Récupération de la recette
            Ingredient ingredient = listeIngredients.get(position);
            //On injecte l'image avec Glide
            Glide.with(holder.itemView).load(ingredient.getImage()).into(holder.imageViewIngredients);
            holder.nomIngredient.setText(ingredient.getName());
            holder.nomIngredient.setText((new Random().nextInt(401)+" g"));
            if(new Random().nextBoolean()) holder.localisable.setVisibility(View.GONE);
        }
    }

    public void refreshList(List<Ingredient> ingredientList){
        this.listeIngredients = ingredientList;
        notifyDataSetChanged();
    }


    /**
     * methode getItemCount
     * @return la taille de la listes des ingredients
     */
    @Override
    public int getItemCount() {
        return listeIngredients.size();
    }


    public static class RecetteViewHolder extends RecyclerView.ViewHolder {

        /**
         * Image de l'ingredients
         */
        public ImageView imageViewIngredients;

        /**
         * Le nom de l'ingredient
         */
        public TextView nomIngredient;

        /**
         * La quantite necessaire
         */
        public TextView quantiteIngredient;

        /**
         * le logo position qui indique si l'aliment est dispo chez un producteur ou non
         */
        public ImageView localisable;

        /**
         * Constructeur
         * @param v view recyclée
         */
        public RecetteViewHolder(View v) {
            super(v);
            this.imageViewIngredients = v.findViewById(R.id.imgIngredient);
            this.nomIngredient=v.findViewById(R.id.nomIngredient);
            this.quantiteIngredient=v.findViewById(R.id.quantiteIngredient);
            this.localisable=v.findViewById(R.id.localisable);
        }
    }
}
