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
import com.example.monpetitterroir.service.FirebaseService;
import com.example.monpetitterroir.service.RecipeService;
import com.example.monpetitterroir.model.ServicesBuilder;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.RegEx;

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
    public FirebaseService service = new FirebaseService();
    public List<Recipe> recipes = new ArrayList<>();
    public static final String TAG = "HomeFragment";

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

        this.recipes = this.service.listRecipes();
        Log.i(TAG, "onCreateView: " + this.recipes.toString());
        myAdapter.refreshList(this.recipes);

        return binding.getRoot();
    }
}