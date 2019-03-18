package com.example.timesup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button son;
    Button vibration;

    Button jouer;
    Button ajoutMot;
    Button regles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        MotsBDD motBdd = new MotsBDD(this);
        Mot mot1 = new Mot("Girafe",false);
        Mot oldMot = motBdd.getMotWithTexte("Girafe");

        if(oldMot == null){
            System.out.println("Ce mot n'existe pas");
        }else{
            System.out.println("Ce mot existe");
        }

        motBdd.insertMot(mot1);

        if(motBdd.getMotWithTexte(mot1.getTexte()) == null){
            System.out.println("Ce mot n'existe pas");
        }else{
            System.out.println("Ce mot existe");
        }*/

        son = findViewById(R.id.xml_son);
        vibration = findViewById(R.id.xml_vibration);

        jouer = findViewById(R.id.xml_jouer);
        ajoutMot = findViewById(R.id.xml_ajoutMot);
        regles = findViewById(R.id.xml_regles);

        son.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //active/désactive son
            }
        });
        vibration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //active/désactive vibration
            }
        });

        jouer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ParametrePartie.class);
                startActivity(intent);
            }
        });

        ajoutMot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AjoutDeMot.class);
                startActivity(intent);
            }
        });

        regles.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReglesDuJeu.class);
                startActivity(intent);
            }
        });
    }
}
