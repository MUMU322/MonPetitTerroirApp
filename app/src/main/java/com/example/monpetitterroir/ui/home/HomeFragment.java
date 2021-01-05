package com.example.monpetitterroir.ui.home;

import android.os.Bundle;
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
import com.example.monpetitterroir.model.Recette;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
       // View root = inflater.inflate(R.layout.fragment_home, container, false);


        ArrayList<Recette> alRecette=new ArrayList<Recette>();
        alRecette.add(new Recette("Pancake", "la description"));
        //l'adapter du recycler view
        final ListeRecetteAdapter sa = new ListeRecetteAdapter(alRecette);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //le recycler view
        final RecyclerView recyclerViewListeRecette = root.findViewById(R.id.listeRecette);
        recyclerViewListeRecette.setHasFixedSize(true);
        LinearLayoutManager layoutManagerListeRecette = new LinearLayoutManager(container.getContext());
        recyclerViewListeRecette.setLayoutManager(layoutManagerListeRecette);
        recyclerViewListeRecette.setAdapter(sa);

        return root;
    }
}