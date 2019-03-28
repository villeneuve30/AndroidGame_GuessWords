package com.example.timesup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ParametrePartie extends AppCompatActivity {

    private Button suivant;
    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    private Button moinsTemps;
    private Button moinsMots;

    private Button plusTemps;
    private Button plusMots;


    private TextView nbTemps;
    private TextView nbMots;

    private Switch filtre;

    private ArrayList<String> tabMots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        //bouton suivant (passage au choix des thèmes)
        suivant = findViewById(R.id.bouton_suivant_xml);

        //Boutons moins
        moinsTemps = findViewById(R.id.bouton_moins_temps_xml);
        moinsMots = findViewById(R.id.bouton_moins_mots_xml);

        //Boutons plus
        plusTemps = findViewById(R.id.bouton_plus_temps_xml);
        plusMots = findViewById(R.id.bouton_plus_mots_xml);

        //label des paramètres
        nbTemps = findViewById(R.id.label_nbTemps_xml);
        nbMots = findViewById(R.id.label_nbMots_xml);

        //switch du filtre enfant
        filtre = findViewById(R.id.switch_filtre_xml);

        final MotsBDD bddTemp = new MotsBDD(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt("NUM_EQUIPE",1);
                editor.putInt("SCORE_EQUIPE_1",0);
                editor.putInt("SCORE_EQUIPE_2",0 );
                editor.putInt("TEMPS",Integer.parseInt(nbTemps.getText().toString()));
                editor.putInt("MOTS", Integer.parseInt(nbMots.getText().toString()));
                editor.putBoolean("FILTRE", filtre.isChecked());
                editor.commit();

                tabMots = bddTemp.tableauRandomDeMot(Integer.parseInt(nbMots.getText().toString()),filtre.isChecked());

                Intent intent = new Intent(ParametrePartie.this, LancementJeu.class);
                intent.putStringArrayListExtra("ArrayList",tabMots );
                startActivity(intent);
                finish();
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
    @Override
    public void onBackPressed() {
        finish();
    }

}
