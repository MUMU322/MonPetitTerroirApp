package com.example.monpetitterroir.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monpetitterroir.R;
import com.example.monpetitterroir.databinding.FragmentHomeBinding;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.RecipeService;
import com.example.monpetitterroir.model.ServicesBuilder;
import com.example.monpetitterroir.service.FirebaseService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Classe correspondant à l'affichage d'accueil (la liste des recettes)
 */
public class HomeFragment extends Fragment {
    /**
     * Biding permettant d'éviter de faire des R.id.layout à chaque fois
     */
    public FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Le recycler view
        RecyclerView recyclerViewListeRecette = binding.listeRecette;
        LinearLayoutManager layoutManagerListeRecette = new LinearLayoutManager(container.getContext());
        recyclerViewListeRecette.setLayoutManager(layoutManagerListeRecette);

        // L'adapter du recycler view
        ListeRecetteAdapter myAdapter = new ListeRecetteAdapter(new ArrayList<>());
        recyclerViewListeRecette.setAdapter(myAdapter);

        // Récupération de la liste des recettes

        FirebaseService firebaseService=new FirebaseService();
        Log.e("TAG", "onCreateView: "+firebaseService.listRecipes());
       // myAdapter.refreshList(firebaseService.listRecipes());


        Call callRecette = ServicesBuilder.INSTANCE.buildService(RecipeService.class).recipesList(getString(R.string.apiKey),"apples");

        callRecette.enqueue(new Callback<List<Recipe>>(){
                                @Override
                                public void onResponse(@NotNull Call<List<Recipe>> call, @NotNull Response<List<Recipe>> response) {
                                    List<Recipe> maList = response.body();
                                    if (maList != null) {
                                       Log.i("MICHutrè(dèuEL", response.body().toString());
                                        myAdapter.refreshList(maList);
                                    }
                                }

                                @Override
                                public void onFailure(@NotNull Call<List<Recipe>> call, @NotNull Throwable t) { }
                            });
        return binding.getRoot();
    }
}