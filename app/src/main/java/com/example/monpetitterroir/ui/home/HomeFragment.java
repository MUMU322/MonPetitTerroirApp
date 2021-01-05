package com.example.monpetitterroir.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monpetitterroir.R;
import com.example.monpetitterroir.databinding.FragmentHomeBinding;
import com.example.monpetitterroir.model.Recette;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.model.RecipeService;
import com.example.monpetitterroir.model.ServicesBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static retrofit2.Response.error;


//API Key: e94f7441d6e640bda8d8ba8f19242506
/**
 * Classe correspondant à l'affichage d'accueil (la liste des recettes)
 */
public class HomeFragment extends Fragment {
    /**
     *biding permettant d'éviter de faire des R.id.layout à chaque fois
     */
    public FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        this.binding = FragmentHomeBinding.inflate(inflater, container, false);

        //le recycler view
        RecyclerView recyclerViewListeRecette = binding.listeRecette;
        LinearLayoutManager layoutManagerListeRecette = new LinearLayoutManager(container.getContext());
        recyclerViewListeRecette.setLayoutManager(layoutManagerListeRecette);


        //l'adapter du recycler view
        ListeRecetteAdapter myAdapter = new ListeRecetteAdapter(new ArrayList<Recipe>());
        recyclerViewListeRecette.setAdapter(myAdapter);

        Call callRecette = ServicesBuilder.INSTANCE.buildService(RecipeService.class).recipesList(getString(R.string.apiKey),"apples");

        callRecette.enqueue(new Callback<List<Recipe>>(){

                                @Override
                                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                                    List<Recipe> maList = response.body();
                                    Log.i("MICHEL", response.body().toString());
                                    if(maList != null){
                                        myAdapter.refreshList(maList);
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                                }
                            });




//        val call = ServicesBuilder.buildService(RecipeService::class.java)
//            .recipesList(getString(R.string.apiKey), "apples")

        return binding.getRoot();
    }
}