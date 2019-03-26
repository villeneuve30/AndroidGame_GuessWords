package com.example.timesup;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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

    EditText inputMot;
    ListView listeMots;
    Button boutonAjouter;
    Switch filtreEnfant;

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_de_mot);

        inputMot = findViewById(R.id.input_mot_xml);
        listeMots = findViewById(R.id.listeDeMot_xml);
        boutonAjouter = findViewById(R.id.bouton_ajouter_xml);
        filtreEnfant = findViewById(R.id.filtre_enfant_xml);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listeMots.setAdapter(adapter);

        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mot = inputMot.getText().toString();
                if(!mot.equals("") && !mot.equals(" ")) {
                    //Ajouter mot a la listView
                    inputMot.setText("");
                    listItems.add(mot);
                    adapter.notifyDataSetChanged();

                    //Ajouter mot a la BDD
                }else{
                    Toast.makeText(AjoutDeMot.this, "Tu dois d'abord écrire un mot !",
                          Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
