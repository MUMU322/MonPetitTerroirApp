package com.example.monpetitterroir.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.monpetitterroir.R;
import com.example.monpetitterroir.model.Recipe;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListeRecetteAdapter extends RecyclerView.Adapter<ListeRecetteAdapter.ListeRecetteViewHolder> {
    /**
     * L'arrayList des recettes proposées
     */
    private List<Recipe> listeRecette;

    /**
     * Constructeur de l'adapteur
     * @param al l'arraylist des recettes
     */
    public ListeRecetteAdapter(ArrayList<Recipe> al){
        this.listeRecette=al;
    }

    /**
     * Methode onCreateViewHolder du reyclerAdapter
     * @param parent    View group parent
     * @param viewType  Viex type
     * @return RecetteViewHolder, un item de la recyclerView
     */
    @NotNull
    @Override
    public ListeRecetteAdapter.ListeRecetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Création de la vue
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_recette, parent, false);
        return new ListeRecetteViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ListeRecetteAdapter.ListeRecetteViewHolder holder, int position) {
        if (listeRecette.get(position) != null) {
            // Récupération de la recette
            Recipe myRecette = listeRecette.get(position);
            holder.textViewTitreRecette.setText(myRecette.getTitle());
            Glide.with(holder.itemView).load(myRecette.getImage()).into(holder.imageViewRecette);

            // Lors d'un clique sur la recette
            holder.itemView.setOnClickListener(v -> {
                  Bundle bundle = new Bundle();
              //    bundle.putString("uid", String.valueOf(myRecette.getId()));
                  bundle.putString("uid", myRecette.getId());

//                Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_recetteFragment, bundle);

//                ConfirmationAction action =
//                        SpecifyAmountFragmentDirections.confirmationAction();
//                action.setAmount(amount);
//                Navigation.findNavController(view).navigate(action);

//                @Override
//                public void onClick(View view) {
//                    EditText amountTv = (EditText) getView().findViewById(R.id.editTextAmount);
//                    int amount = Integer.parseInt(amountTv.getText().toString());
//                    ConfirmationAction action =
//                            SpecifyAmountFragmentDirections.confirmationAction();
//                    action.setAmount(amount);
//                    Navigation.findNavController(view).navigate(action);
//                }
                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_recetteFragment, bundle);
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

    /**
     * Rafraichissement de la liste
     * @param recipeList Liste avec laquelle rafraichir
     */
    public void refreshList(List<Recipe> recipeList){
        this.listeRecette = recipeList;
        notifyDataSetChanged();
    }

    /**
     * Classe ListeRecetteViewHolder correspondant aux attributs des "tuiles" du recycler view
     */
    public static class ListeRecetteViewHolder extends RecyclerView.ViewHolder {
        /**
         * Le titre de la recette
         */
        public TextView textViewTitreRecette;

        /**
         * La description de la recette
         */
        public TextView textViewDescription;

        /**
         * Image de la recette
         */
        public ImageView imageViewRecette;

        /**
         * Constructeur
         * @param v view recyclée
         */
        public ListeRecetteViewHolder(View v) {
            super(v);
            textViewTitreRecette =  v.findViewById(R.id.recipeTitle);
            imageViewRecette = v.findViewById(R.id.recipeImage);
        }
    }
}
