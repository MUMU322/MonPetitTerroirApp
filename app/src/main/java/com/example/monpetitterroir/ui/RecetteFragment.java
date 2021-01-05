package com.example.monpetitterroir.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.monpetitterroir.R;
import com.example.monpetitterroir.databinding.FragmentRecetteBinding;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.RecipeService;
import com.example.monpetitterroir.model.ServicesBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetteFragment extends Fragment {
    public FragmentRecetteBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent = requireActivity().getIntent();
        String id = intent.getStringExtra("id");

        Call callRecette = ServicesBuilder.INSTANCE.buildService(RecipeService.class)
                .recipeDetail(id, getString(R.string.apiKey));

        callRecette.enqueue(new Callback<List<Recipe>>(){
            @Override
            public void onResponse(@NotNull Call<List<Recipe>> call, @NotNull Response<List<Recipe>> response) {
                assert response.body() != null;
                Log.i("MICHEL RECETTE", response.body().toString());
            }

            @Override
            public void onFailure(@NotNull Call<List<Recipe>> call, @NotNull Throwable t) {
            }
        });

        binding = FragmentRecetteBinding.inflate(inflater,container,false);
        return binding.getRoot();
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
