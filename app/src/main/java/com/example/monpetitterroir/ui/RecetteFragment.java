package com.example.monpetitterroir.ui;

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
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.service.RecipeService;
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
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Log.i("ALO MICHEL",savedInstanceState.getString("uid"));
        String id = getArguments().get("uid").toString();
        Log.i("ALO MICHEL 2", id);

        Call callRecette = ServicesBuilder.INSTANCE.buildService(RecipeService.class)
                .recipeDetail(id, getString(R.string.apiKey));
        callRecette.enqueue(new Callback<Recipe>(){
            @Override
            public void onResponse(@NotNull Call<Recipe> call, @NotNull Response<Recipe> response) {
                ImageView img = view.findViewById(R.id.imgRecette);
                TextView title = view.findViewById(R.id.titreRecette);
                Glide.with(view).load(response.body().getImage()).into(img);
                title.setText(response.body().getTitle());
                Log.i("MICHEL RECETTE", response.body().toString());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
            }
        });
    }
}
