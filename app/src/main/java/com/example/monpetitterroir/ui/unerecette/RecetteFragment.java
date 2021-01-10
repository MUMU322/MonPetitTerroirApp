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

import com.bumptech.glide.Glide;
import com.example.monpetitterroir.R;
import com.example.monpetitterroir.databinding.FragmentRecetteBinding;
import com.example.monpetitterroir.model.Ingredient;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.RecipeService;
import com.example.monpetitterroir.model.ServicesBuilder;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetteFragment extends Fragment {
    public FragmentRecetteBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecetteBinding.inflate(inflater,container,false);
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

        // Appel API pour récupérer les ingrédients
        Call<Ingredient> callIngredient = ServicesBuilder.INSTANCE.buildService(RecipeService.class)
                .ingredientDetail(id, getString(R.string.apiKey));
        callIngredient.enqueue(new Callback<Ingredient>() {
            @Override
            public void onResponse(@NotNull Call<Ingredient> call, @NotNull Response<Ingredient> response) {
                Log.i("michel ingredient", response.body().toString());
            }

            @Override
            public void onFailure(@NotNull Call<Ingredient> call, @NotNull Throwable t) { }
        });
    }
}
