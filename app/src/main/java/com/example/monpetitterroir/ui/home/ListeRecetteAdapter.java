package com.example.monpetitterroir.ui.home;

import android.content.Intent;
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
import com.example.monpetitterroir.model.Recette;
import com.example.monpetitterroir.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ListeRecetteAdapter extends RecyclerView.Adapter<ListeRecetteAdapter.ListeRecetteViewHolder> {


    /**
     * L'arrayList des recettes proposé
     */
    private List<Recipe> listeRecette;

    /**
     * Constructeur de l'adapteur
     * @param al
     *          l'arraylist des recettes
     */
    public ListeRecetteAdapter(ArrayList<Recipe> al){
        this.listeRecette=al;
    }

    /**
     Methode onCreateViewHolder du reyclerAdapter
     * @param parent
     *             View group parent
     * @param viewType
     *              Viex type
     * @return
     *              RecettteViewHolder, un item de la recyclerView
     */
    @Override
    public ListeRecetteAdapter.ListeRecetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_recette, parent, false);
        ListeRecetteViewHolder vh = new ListeRecetteViewHolder(v);
        return vh;
    }

    /**
     onBindViewHolder, recyclant les objets SportViewHolder
     * @param holder
     *          le holder deja existant
     * @param position
     *          la position de l'item apparaissant a l'ecran dans la liste des Sports
     */
    @Override
    public void onBindViewHolder(@NonNull ListeRecetteAdapter.ListeRecetteViewHolder holder, int position) {
        if(listeRecette.get(position) != null){
            Log.i("MICHEL2", listeRecette.get(position).getTitle());
            holder.textViewTitreRecette.setText(this.listeRecette.get(position).getTitle());
            Glide.with(holder.itemView).load(listeRecette.get(position).getImage()).into(holder.imageViewRecette);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent myItent = new Intent(this, HomeF)
                }
            });
        }
    }

    /**
     * methode getItemCount
     * @return la taille de la listes des recettes
     */
    @Override
    public int getItemCount() {
        return listeRecette.size();
    }


    public void refreshList(List<Recipe> recipeList){
        this.listeRecette = recipeList;
        notifyDataSetChanged();
    }


    /**
     * Classe SportViewHolder correspondant aux attributs des "tuiles" du recycler view
     */
    public static class ListeRecetteViewHolder extends RecyclerView.ViewHolder {

        /**
         * le titre de la recette
         */
        public TextView textViewTitreRecette;
        /**
         * la description de la rectte
         */
        public TextView textViewDescription;

        public ImageView imageViewRecette;



        /**
         * Constructeur
         * @param v
         *      view recyclée
         */
        public ListeRecetteViewHolder(View v) {
            super(v);
            textViewTitreRecette =  v.findViewById(R.id.recipeTitle);
            imageViewRecette = v.findViewById(R.id.recipeImage);

        }

    }


}
