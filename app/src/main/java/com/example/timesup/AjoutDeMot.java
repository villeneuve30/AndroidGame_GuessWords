package com.example.timesup;

import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.annotation.ColorLong;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class AjoutDeMot extends AppCompatActivity {

    private EditText inputMot;
    private ListView listeMots;
    private Button boutonAjouter;
    private Switch filtreEnfant;
    private Button boutonSupprimer;
    private TextView motASupprimer;

    private ArrayList<String> listItems=new ArrayList<>();
    private ArrayList<String> tousLesMots = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private int positionItem;

    private MotsBDD motBDD;
    private MediaPlayer songSupprimer;
    private MediaPlayer songAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_de_mot);

        inputMot = findViewById(R.id.input_mot_xml);
        listeMots = findViewById(R.id.listeDeMot_xml);
        boutonAjouter = findViewById(R.id.bouton_ajouter_xml);
        filtreEnfant = findViewById(R.id.filtre_enfant_xml);
        boutonSupprimer = findViewById(R.id.bouton_supprimer_xml);
        motASupprimer = findViewById(R.id.text_motSelected_xml);

        boutonSupprimer.setEnabled(false);


        songSupprimer = MediaPlayer.create(this,R.raw.corbeille);
        songAjouter = MediaPlayer.create(this,R.raw.triangle );
        boutonAjouter.setSoundEffectsEnabled(false);
        boutonSupprimer.setSoundEffectsEnabled(false);


        motBDD = new MotsBDD(this);
        Cursor data = motBDD.getAllData();
        int id;
        while(data.moveToNext()){
            tousLesMots.add(data.getString(1));
            id = Integer.parseInt(data.getString(0));
            if(id > 100) {
                listItems.add(data.getString(1));
            }
        }

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listeMots.setAdapter(adapter);

        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = inputMot.getText().toString();
                if(!mot.equals("") && !mot.equals(" ") && !tousLesMots.contains(mot) && !mot.contains("'") && !mot.contains("\"") && mot.length() <= 10) {
                    songAjouter.start();
                    //Ajouter mot a la listView
                    inputMot.setText("");
                    listItems.add(mot);
                    adapter.notifyDataSetChanged();

                    //Ajouter mot a la BDD
                    Mot nouveauMot = new Mot(mot,filtreEnfant.isChecked());
                    motBDD.open();
                    motBDD.insertMot(nouveauMot);
                    motBDD.close();
                }else{
                    if(!mot.equals("") && !mot.equals(" ")) {
                        Toast.makeText(AjoutDeMot.this, "Tu dois d'abord écrire un mot !",
                                Toast.LENGTH_LONG).show();
                    }
                    if(tousLesMots.contains(mot)){
                        Toast.makeText(AjoutDeMot.this, "Ce mot existe déjà !",
                                Toast.LENGTH_LONG).show();
                    }
                    if(mot.contains("'") && mot.contains("\"")){
                        Toast.makeText(AjoutDeMot.this, "Tu ne peux pas utiliser de caractères spéciaux",
                                Toast.LENGTH_LONG).show();
                    }
                    if(mot.length() > 10){
                        Toast.makeText(AjoutDeMot.this, "Ton mot ne peut avoir plus de 10 caractères",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        listeMots.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                boutonSupprimer.setEnabled(true);

                String selectedFromList = listItems.get(position);

                motASupprimer.setText("Supprimer '"+selectedFromList+"' ?");

                positionItem = position;
            }
        });

        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boutonSupprimer.setEnabled(false);
                songSupprimer.start();

                String selectedFromList =listItems.get(positionItem);
                motBDD.open();
                motBDD.removeMotWithString(selectedFromList);
                motBDD.close();
                motBDD.afficherTousLesMots();

                listItems.remove(positionItem);
                adapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }

}
