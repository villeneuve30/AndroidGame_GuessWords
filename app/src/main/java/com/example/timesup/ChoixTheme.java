package com.example.timesup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChoixTheme extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_theme);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int totalEquipes = sharedpreferences.getInt("EQUIPES", 2);
        int totalTemps = sharedpreferences.getInt("TEMPS", 15);
        int totalMots = sharedpreferences.getInt("MOTS", 25);
        boolean filtreEnfant = sharedpreferences.getBoolean("FILTRE", false);

        System.out.println(totalEquipes+" "+totalTemps+" "+totalMots+" "+filtreEnfant);
    }
}
