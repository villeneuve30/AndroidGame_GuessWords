package com.example.timesup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ParametrePartie extends AppCompatActivity {
    Button moinsEquipe;
    Button moinsTemps;
    Button moinsMots;
    Button plusEquipe;
    Button plusTemps;
    Button plusMots;

    TextView nbEquipe;
    TextView nbTemps;
    TextView nbMots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        //Boutons moins
        moinsEquipe = findViewById(R.id.bouton_moins_equipe_xml);
        moinsTemps = findViewById(R.id.bouton_moins_equipe_xml);
        moinsMots = findViewById(R.id.bouton_moins_equipe_xml);

        //Boutons plus
        plusEquipe = findViewById(R.id.bouton_plus_equipe_xml);
        plusTemps = findViewById(R.id.bouton_plus_temps_xml);
        plusMots = findViewById(R.id.bouton_plus_mots_xml);

        //label des paramètres
        nbEquipe = findViewById(R.id.label_nbEquipe_xml);
        nbTemps = findViewById(R.id.label_nbTemps_xml);
        nbMots = findViewById(R.id.label_nbMots_xml);


    }
}
