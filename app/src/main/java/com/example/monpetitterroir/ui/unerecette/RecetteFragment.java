package com.example.monpetitterroir.ui.unerecette;

import android.os.Bundle;
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
import com.example.monpetitterroir.service.FirebaseService;

public class RecetteFragment extends Fragment {
    public FragmentRecetteBinding binding;
    public FirebaseService service = new FirebaseService();
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
        Recipe recipe = this.service.getRecipe(id);
        ImageView img = view.findViewById(R.id.imgRecette);
        TextView title = view.findViewById(R.id.titreRecette);
        Glide.with(view).load(recipe.getImage()).into(img);
        title.setText(recipe.getTitle());

    }
}
