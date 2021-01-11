package com.example.monpetitterroir.ui.unerecette;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.monpetitterroir.R;
import com.example.monpetitterroir.databinding.FragmentHomeBinding;
import com.example.monpetitterroir.databinding.FragmentRecetteBinding;
import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.ListIngredients;
import com.example.monpetitterroir.model.Recette;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.RecipeService;
import com.example.monpetitterroir.model.ServicesBuilder;
import com.example.monpetitterroir.ui.home.ListeRecetteAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetteFragment extends Fragment {
    FragmentRecetteBinding binding;

    RecetteAdapter ra;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentRecetteBinding.inflate(inflater,container,false);


        ArrayList<Ingredient> ai =new ArrayList<>();

        this.ra = new RecetteAdapter(ai);


        RecyclerView recyclerViewListeIngredient = binding.recyclerViewIngredients;
        LinearLayoutManager layoutManagerListeIngredients = new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewListeIngredient.setLayoutManager(layoutManagerListeIngredients);



        recyclerViewListeIngredient.setAdapter(this.ra);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Récupération de l'identifiant
        String id = getArguments().get("uid").toString();

        // Appel API pour récupérer la recette par son identifiant
        Call<Recipe> callRecette = ServicesBuilder.INSTANCE.buildService(RecipeService.class)
                .recipeDetail(id, getString(R.string.apiKey));
        callRecette.enqueue(new Callback<Recipe>(){
            @Override
            public void onResponse(@NotNull Call<Recipe> call, @NotNull Response<Recipe> response) {
                // On affiche les informations sur la recette
                ImageView img = view.findViewById(R.id.imgRecette);
                TextView title = view.findViewById(R.id.titreRecette);
                Glide.with(view).load(response.body().getImage()).into(img);
                title.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(@NotNull Call<Recipe> call, @NotNull Throwable t) { }
        });



         Call<ListIngredients> callIngredient = ServicesBuilder.INSTANCE.buildService(RecipeService.class)
                .ingredientDetail(id, getString(R.string.apiKey));
        callIngredient.enqueue(new Callback<ListIngredients>() {
            @Override
            public void onResponse(@NotNull Call<ListIngredients> call, @NotNull Response<ListIngredients> response) {
                ra.refreshList(response.body().getListeIngredients());
            }

            @Override
            public void onFailure(@NotNull Call<ListIngredients> call, @NotNull Throwable t) {
                Log.d("magasin", "onFailure: ");
            }
        });
    }
}
