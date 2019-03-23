package com.example.timesup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LancementJeu extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    Button demarrer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        demarrer = findViewById(R.id.bouton_demarrer_xml);

        //On récupère les paramètres prédédent
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int totalEquipes = sharedpreferences.getInt("EQUIPES", 2);
        int totalTemps = sharedpreferences.getInt("TEMPS", 15);
        int totalMots = sharedpreferences.getInt("MOTS", 25);
        boolean filtreEnfant = sharedpreferences.getBoolean("FILTRE", false);
        System.out.println(totalEquipes+" "+totalTemps+" "+totalMots+" "+filtreEnfant);

        demarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demarrer.setClickable(false);
                demarrer.setTextColor(Color.BLUE);
                new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        demarrer.setText("début dans : " + millisUntilFinished / 1000);
                    }
                    public void onFinish() {
                        demarrer.setText("GO !");
                        Intent intent = new Intent(LancementJeu.this, Round.class);
                        startActivity(intent);
                    }
                }.start();
            }
        });

    }
}
