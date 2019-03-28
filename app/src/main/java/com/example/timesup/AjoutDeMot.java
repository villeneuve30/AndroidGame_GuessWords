package com.example.timesup;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.ColorLong;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

    private ArrayList<String> listItems=new ArrayList<>();
    private ArrayList<String> tousLesMots = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private int positionItem;
    private boolean isItemSelected;

    private MotsBDD motBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_de_mot);

        inputMot = findViewById(R.id.input_mot_xml);
        listeMots = findViewById(R.id.listeDeMot_xml);
        boutonAjouter = findViewById(R.id.bouton_ajouter_xml);
        filtreEnfant = findViewById(R.id.filtre_enfant_xml);
        boutonSupprimer = findViewById(R.id.bouton_supprimer_xml);

        isItemSelected =false;

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listeMots.setAdapter(adapter);

        motBDD = new MotsBDD(this);
        Cursor data = motBDD.getAllData();
        int id;
        while(data.moveToNext()){
            tousLesMots.add(data.getString(1));
            id = Integer.parseInt(data.getString(0));
            if(id > 101) {
                listItems.add(data.getString(1));
            }
        }

        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = inputMot.getText().toString();
                if(!mot.equals("") && !mot.equals(" ") && !tousLesMots.contains(mot) && !mot.contains("'") && !mot.contains("\"")) {
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
                }

            }
        });

        listeMots.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                for (int i = 0; i < listeMots.getChildCount(); i++) {
                    if(position == i ){
                        listeMots.getChildAt(i).setBackgroundColor(Color.RED);
                    }else{
                        listeMots.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                isItemSelected = true;
                positionItem = position;
            }
        });

        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
