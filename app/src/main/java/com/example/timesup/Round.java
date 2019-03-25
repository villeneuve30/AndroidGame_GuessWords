package com.example.timesup;

import android.content.Context;
import android.content.Intent;
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

    private int points;

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

        tabMots = getIntent().getStringArrayListExtra("ArrayList");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final int numEquipe = sharedpreferences.getInt("NUM_EQUIPE",1 );
        final int score_equipe_1 = sharedpreferences.getInt("SCORE_EQUIPE_1",0 );
        final int score_equipe_2 = sharedpreferences.getInt("SCORE_EQUIPE_2",0 );
        final int totalTemps = sharedpreferences.getInt("TEMPS", 15);

        if(numEquipe == 1){
            points = score_equipe_1;
        }else{
            points = score_equipe_2;
        }

        mot.setText(tabMots.get(position));

        new CountDownTimer(totalTemps*1000, 1000) {
            public void onTick(long millisUntilFinished) {
               tempsRestant.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(numEquipe == 1){
                    editor.putInt("SCORE_EQUIPE_1",points);
                }else {
                    editor.putInt("SCORE_EQUIPE_2", points);
                }
                if(numEquipe == 1) {
                    editor.putInt("NUM_EQUIPE", 2);
                }else{
                    editor.putInt("NUM_EQUIPE",1);
                }
                editor.commit();

                Intent intent = new Intent(Round.this, LancementJeu.class);
                intent.putStringArrayListExtra("ArrayList",tabMots );
                startActivity(intent);
            }
        }.start();

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabMots.size() > 0) tabMots.remove(position);

                if(tabMots.size() == 0) {
                    Intent intent = new Intent(Round.this, AffichageDesScores.class);
                    startActivity(intent);
                }else{

                    if(position >= tabMots.size()){
                        position = 0;
                    }
                    mot.setText(tabMots.get(position));
                }
                points+=1;
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
