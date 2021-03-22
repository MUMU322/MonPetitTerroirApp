package com.example.monpetitterroir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Récupération des préférences
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        // Si c'est son premier lancement, alors on redirige vers le SpashScreen
        boolean isFirstTime = sharedPref.getBoolean("isFirstTime", true);
        if (isFirstTime) {

            // On sauvegarde qu'il ai déjà fait son premier lancement
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            // Redirection vers l'activité
            startActivity(new Intent(MainActivity.this, SplashScreenActivity.class));
        }
    }

}