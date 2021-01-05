package com.example.monpetitterroir.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monpetitterroir.R;
import com.example.monpetitterroir.model.Recette;

import java.util.ArrayList;

public class ListeRecetteAdapter extends RecyclerView.Adapter<ListeRecetteAdapter.ListeRecetteViewHolder> {


    /**
     * L'arrayList des recettes proposé
     */
    private ArrayList<Recette> listeRecette;

    /**
     * Constructeur de l'adapteur
     * @param al
     *          l'arraylist des recettes
     */
    public ListeRecetteAdapter(ArrayList<Recette> al){
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
        holder.textViewTitreRecette.setText(this.listeRecette.get(position).getTitre());
        holder.textViewDescription.setText(this.listeRecette.get(position).getDescription());
    }

    /**
     * methode getItemCount
     * @return la taille de la listes des sports
     */
    @Override
    public int getItemCount() {
        return listeRecette.size();
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



        /**
         * Constructeur
         * @param v
         *      view recyclée
         */
        public ListeRecetteViewHolder(View v) {
            super(v);
            textViewTitreRecette =  v.findViewById(R.id.titreRecette);
            textViewDescription= v.findViewById(R.id.descriptionRecette);

        }

    }


}
