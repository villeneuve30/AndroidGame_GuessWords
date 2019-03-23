package com.example.timesup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Round extends AppCompatActivity {
    private TextView tempsRestant;
    private TextView mot;
    private ArrayList<String> tabMots;
    private Button valider;
    private Button passer;
    private int position;

    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        tempsRestant = findViewById(R.id.tempsRestant_xml);
        mot = findViewById(R.id.mot_xml);
        valider=findViewById(R.id.bouton_valider_xml);
        passer = findViewById(R.id.bouton_passer_xml);

        position = 0;

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        int totalTemps = sharedpreferences.getInt("TEMPS", 15);
        final int totalMots = sharedpreferences.getInt("MOTS", 25);

        MotsBDD bddTemp = new MotsBDD(this);
        tabMots = bddTemp.tableauRandomDeMot(totalMots);

        mot.setText(tabMots.get(position));

        new CountDownTimer(totalTemps*1000, 1000) {
            public void onTick(long millisUntilFinished) {
               tempsRestant.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                //Lancer round suivant ELSE fin partie
            }
        }.start();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabMots.size() > 0) tabMots.remove(position);

                if(tabMots.size() == 0) {
                    mot.setText("FINIIIIII");
                }else{

                    if(position >= tabMots.size()){
                        position = 0;
                    }
                    mot.setText(tabMots.get(position));
                }
            }
        });
        passer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabMots.size() > 0) {
                    position += 1;
                    if (position >= tabMots.size()) {
                        position = 0;
                    }
                    mot.setText(tabMots.get(position));
                }
            }
        });
    }
}
