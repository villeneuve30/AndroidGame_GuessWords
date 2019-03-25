package com.example.timesup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AffichageDesScores extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    TextView gagnant;
    TextView score_1;
    TextView score_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_des_scores);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final int score_equipe_1 = sharedpreferences.getInt("SCORE_EQUIPE_1",0 );
        final int score_equipe_2 = sharedpreferences.getInt("SCORE_EQUIPE_2",0 );

        score_1 = findViewById(R.id.score_equipe_1_xml);
        score_2 = findViewById(R.id.score_equipe_2_xml);

        score_1.setText(""+score_equipe_1);
        score_2.setText(""+score_equipe_2);

        gagnant = findViewById(R.id.titre_gagnant_xml);

        if(score_equipe_1 > score_equipe_2){
            gagnant.setText("L'EQUIPE 1 GAGNE !");
        }
        if(score_equipe_1 < score_equipe_2){
            gagnant.setText("L'EQUIPE 2 GAGNE");
        }
        if(score_equipe_1 == score_equipe_2){
            gagnant.setText("EGALITE !");
        }
    }
}
