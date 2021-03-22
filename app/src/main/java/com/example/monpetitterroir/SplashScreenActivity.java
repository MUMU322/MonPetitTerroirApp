package com.example.monpetitterroir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // On désactive l'affichage de l'action bar
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    /**
     * Redirection de l'utilisateur vers l'activité principale
     * @param view Vue utilisée
     */
    public void letsGo(View view) {
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
    }
}