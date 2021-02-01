package com.example.monpetitterroir.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monpetitterroir.databinding.FragmentHomeBinding;
import com.example.monpetitterroir.model.Recipe;
import com.example.monpetitterroir.service.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Classe correspondant à l'affichage d'accueil (la liste des recettes)
 */
public class HomeFragment extends Fragment {
    /**
     * Binding permettant d'éviter de faire des R.id.layout à chaque fois
     */
    public FragmentHomeBinding binding;
    public FirebaseService service = new FirebaseService();
    public List<Recipe> recipes = new ArrayList<>();
    public static final String TAG = "HomeFragment";
    ListeRecetteAdapter myAdapter = new ListeRecetteAdapter(new ArrayList<>());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        // Le recycler view
        RecyclerView recyclerViewListeRecette = binding.listeRecette;
        LinearLayoutManager layoutManagerListeRecette = new LinearLayoutManager(container.getContext());
        recyclerViewListeRecette.setLayoutManager(layoutManagerListeRecette);

        // L'adapter du recycler view
        recyclerViewListeRecette.setAdapter(myAdapter);

        this.service.listRecipes(recipes -> {
            myAdapter.refreshList(recipes);
        });
        return binding.getRoot();
    }
}