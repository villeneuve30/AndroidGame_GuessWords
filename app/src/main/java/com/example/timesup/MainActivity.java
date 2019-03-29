package com.example.timesup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   private Button jouer;
   private Button ajoutMot;
   private Button regles;

    final String PREFS_NAME = "MyPrefsFile";
    final String PREF_VERSION_CODE_KEY = "version_code";
    final int DOESNT_EXIST = -1;

    private MotsBDD motsBdd;

    private MediaPlayer bienvenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        motsBdd = new MotsBDD(this);
        checkFirstRun();

        jouer = findViewById(R.id.xml_jouer);
        ajoutMot = findViewById(R.id.xml_ajoutMot);
        regles = findViewById(R.id.xml_regles);

        bienvenue = MediaPlayer.create(this,R.raw.guitare);
        bienvenue.start();

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
    private void checkFirstRun(){

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            motsBdd.afficherTousLesMots();

            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            // TODO This is a new install (or the user cleared the shared preferences)

            motsBdd.insererLesMotsDeBase();
            motsBdd.afficherTousLesMots();
            popUpPremierLancement();


        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }
    @Override
    public void onBackPressed() {
        popUpQuitter();
    }

    private void popUpQuitter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Sortir du jeu");
        builder.setMessage("Voulez-vous vraiment quitter ?");
        builder.setPositiveButton("Quitter",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton("Rester", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void popUpPremierLancement() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("");
        builder.setMessage("Bienvenue, amusez-vous bien dans le TIME'S UP !");
        builder.setPositiveButton("C'est parti !",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
