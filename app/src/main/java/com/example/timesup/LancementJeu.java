package com.example.timesup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class LancementJeu extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    private CountDownTimer timer;

    private ArrayList<String> tabMots;
    private Button demarrer;

    private TextView titreEquipe;
    private Boolean lancementEnCour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        demarrer = findViewById(R.id.bouton_demarrer_xml);
        titreEquipe = findViewById(R.id.numero_equipe_xml);

        tabMots = getIntent().getStringArrayListExtra("ArrayList");
        lancementEnCour = false;

        //On récupère les paramètres prédédent
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int numEquipe = sharedpreferences.getInt("NUM_EQUIPE",1);
        int totalTemps = sharedpreferences.getInt("TEMPS", 15);
        int totalMots = sharedpreferences.getInt("MOTS", 25);
        boolean filtreEnfant = sharedpreferences.getBoolean("FILTRE", false);
        System.out.println(numEquipe +" "+totalTemps+" "+totalMots+" "+filtreEnfant);

        titreEquipe.setText("Equipe "+numEquipe+" à vous de jouer ! ");

        demarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demarrer.setClickable(false);
                demarrer.setTextColor(Color.BLUE);
                demarrerTimer();
                lancementEnCour = true;
            }
        });

    }

    private void demarrerTimer() {
        timer = new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                demarrer.setText("début dans : " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                demarrer.setText("GO !");
                Intent intent = new Intent(LancementJeu.this, Round.class);
                intent.putStringArrayListExtra("ArrayList",tabMots );
                startActivity(intent);
                finish();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if(this.timer != null){
            this.timer.cancel();
        }
        popUp();
    }

    private void popUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Quitter");
        builder.setMessage("Voulez-vous revenir au menu ?");
        builder.setPositiveButton("Quitter",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton("Reprendre", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(lancementEnCour)
                demarrerTimer();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
