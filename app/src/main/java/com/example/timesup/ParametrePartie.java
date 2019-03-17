package com.example.timesup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class ParametrePartie extends AppCompatActivity {

    Button suivant;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    Button moinsEquipe;
    Button moinsTemps;
    Button moinsMots;
    Button plusEquipe;
    Button plusTemps;
    Button plusMots;

    TextView nbEquipe;
    TextView nbTemps;
    TextView nbMots;

    Switch filtre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        //bouton suivant (passage au choix des thèmes)
        suivant = findViewById(R.id.bouton_suivant_xml);

        //Boutons moins
        moinsEquipe = findViewById(R.id.bouton_moins_equipe_xml);
        moinsTemps = findViewById(R.id.bouton_moins_temps_xml);
        moinsMots = findViewById(R.id.bouton_moins_mots_xml);

        //Boutons plus
        plusEquipe = findViewById(R.id.bouton_plus_equipe_xml);
        plusTemps = findViewById(R.id.bouton_plus_temps_xml);
        plusMots = findViewById(R.id.bouton_plus_mots_xml);

        //label des paramètres
        nbEquipe = findViewById(R.id.label_nbEquipe_xml);
        nbTemps = findViewById(R.id.label_nbTemps_xml);
        nbMots = findViewById(R.id.label_nbMots_xml);

        //switch du filtre enfant
        filtre = findViewById(R.id.switch_filtre_xml);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        /*int totalEquipes = sharedpreferences.getInt("EQUIPES", 2);
        int totalTemps = sharedpreferences.getInt("TEMPS", 15);
        int totalMots = sharedpreferences.getInt("MOTS", 25);
        boolean filtreEnfant = sharedpreferences.getBoolean("FILTRE", false);

        System.out.println(totalEquipes+" "+totalTemps+" "+totalMots+" "+filtreEnfant);*/

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt("EQUIPES", Integer.parseInt(nbEquipe.getText().toString()));
                editor.putInt("TEMPS",Integer.parseInt(nbTemps.getText().toString()));
                editor.putInt("MOTS", Integer.parseInt(nbMots.getText().toString()));
                editor.putBoolean("FILTRE", filtre.isChecked());
                editor.commit();

                Intent intent = new Intent(ParametrePartie.this, ChoixTheme.class);
                startActivity(intent);
            }
        });
        moinsEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chiffreEquipe = Integer.parseInt(nbEquipe.getText().toString());
                if(chiffreEquipe > 2){
                    chiffreEquipe = chiffreEquipe -1;
                    nbEquipe.setText(String.valueOf(chiffreEquipe));
                }
            }
        });
        plusEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chiffreEquipe = Integer.parseInt(nbEquipe.getText().toString());
                if(chiffreEquipe < 4){
                    chiffreEquipe = chiffreEquipe +1;
                    nbEquipe.setText(String.valueOf(chiffreEquipe));
                }
            }
        });
        moinsTemps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chiffreTemps = Integer.parseInt(nbTemps.getText().toString());
                if(chiffreTemps >= 30){
                    chiffreTemps = chiffreTemps - 15;
                    nbTemps.setText(String.valueOf(chiffreTemps));
                }
            }
        });
        plusTemps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chiffreTemps = Integer.parseInt(nbTemps.getText().toString());
                if(chiffreTemps <= 45 ){
                    chiffreTemps = chiffreTemps + 15;
                    nbTemps.setText(String.valueOf(chiffreTemps));
                }
            }
        });
        moinsMots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chiffreMots = Integer.parseInt(nbMots.getText().toString());
                if(chiffreMots >= 50){
                    chiffreMots = chiffreMots - 25;
                    nbMots.setText(String.valueOf(chiffreMots));
                }
            }
        });
        plusMots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chiffreMots = Integer.parseInt(nbMots.getText().toString());
                if(chiffreMots <= 75){
                    chiffreMots = chiffreMots + 25;
                    nbMots.setText(String.valueOf(chiffreMots));
                }
            }
        });

    }
}
