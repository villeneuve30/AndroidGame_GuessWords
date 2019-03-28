package com.example.timesup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.timesup.SQLiteDataBaseHelper.CREATE_BDD;

public class MotsBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "mots.db";

    private static final String TABLE_MOTS = "table_mots";

    private static final String COL_ID ="ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_MOT = "MOT";
    private static final int NUM_COL_MOT = 1;
    private static final String COL_FILTRE = "FILTRE";
    private static final int NUM_COL_FILTRE = 2;

    private static final String[] tableauMots = {"Mur","Silence","Mouette","Printemps","Ordinateur","Crabe",
            "Chien","Tambour","Triangle","Pluie","Poivre","Bonbons","Clé","Anneau","Œufs",
            "Route","Tomate","Cuisinier","Famille","Voler","Loup","Chemise","Lune","Vent","Arbre","Papillon",
            "Tigre","Fantôme","Cheval","Casserole","Crayon","Professeur","Botte","Nuage",
            "Abeille","Loupe","Poulet","Camion","Araignée","Pêcheur","Livre","Lit","Ballon",
            "Rire","Enfant","Montagne","Rapide","Chèvre","Banane","Mer","Soldat","Robinet","Grand-mère","École",
            "Manchot","Oignon","Serviette","Valise","Argent","Échelle","Brosse","Parapluie","Yaourt",
            "Éclairage","Fenêtre","Nourriture","Couche","Girafe","Étoile","Serpent","Ouïe","Malade","Sale",
            "Laitue","Suivre","Burger","Sifflet","Laid","Glace","Guitare","Étudiant","Téléphone","Fromage","Trottoir",
            "Baignoire","Casque","Charpentier","Rivière","Maillot de bain","Ciseaux","Propre","Poussin","Baleine",
            "Trompette","Diapositive","Panda","Hélicoptère","Cloche","Poulpe","Escargot"};

    private SQLiteDatabase bdd;
    private SQLiteDataBaseHelper maBaseSQLite;

    public MotsBDD(Context context){
        maBaseSQLite = new SQLiteDataBaseHelper(context, NOM_BDD,null,VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }
    public void close(){
        bdd.close();
    }
    public SQLiteDatabase getBDD(){
        return bdd;
    }
    public long insertMot(Mot mot){
        ContentValues values = new ContentValues(); //HashMap
        //ajout des valeurs :
        int fauxBoolean;
        if(mot.isFiltre_bool()){
            fauxBoolean = 1;
        }else{
            fauxBoolean = 0;
        }
        values.put(COL_MOT,mot.getTexte());
        values.put(COL_FILTRE, fauxBoolean);

        //insertion du mot dans la bdd
        return bdd.insert(TABLE_MOTS,null,values);
    }

    /*public int updateMot(int id, Mot mot){
        //Mise a jour d'un mot
        //Mais ici on ne s'en sert pas
        //un mot pourrat soit être ajouté, soit supprimé pour simplifier l'ajout de mot
    }*/

    public int removeMotWithString(String nomMot){
        return this.bdd.delete(TABLE_MOTS, COL_MOT + " like '" + nomMot +"'",null);
    }

    public Mot getMotWithTexte(String texte){
        Cursor c = bdd.query(TABLE_MOTS, new String[]{COL_ID, COL_MOT, COL_FILTRE},COL_MOT + " LIKE '" + texte +"'", null, null, null, null);
        return cursorToMot(c);
    }

    public Mot cursorToMot(Cursor c) {
        if (c.getCount() == 0)
            return null;

        c.moveToFirst();

        boolean filtre;
        if (c.getInt(NUM_COL_FILTRE) == 1) {
            filtre = true;
        } else {
            filtre = false;
        }

        Mot mot = new Mot(c.getString(NUM_COL_MOT), filtre);
        c.close();
        return mot;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        Cursor c =db.rawQuery("select * from "+TABLE_MOTS,null );
        return c;
    }
    public void afficherTousLesMots(){
        Cursor data= this.getAllData();
        if(data.getCount()==0){
            System.out.println("pas de mot dans la base");
        }else{
            System.out.println(data.getCount()+" mots dans la base");
        }
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext()) {
            buffer.append("Id : " + data.getString(0) + "\n");
            buffer.append("Mot : " + data.getString(1) + "\n");
            buffer.append("Filtre enfant : " + data.getString(2) + "\n");
        }
        System.out.println(buffer.toString());
        System.out.println("taille tableau de base : "+tableauMots.length);
    }

    public ArrayList<String> tableauRandomDeMot(int nbMots,Boolean filtre){

        ArrayList<String> tabMot = new ArrayList<>();
        ArrayList<String> tabBDD = new ArrayList<>();

        Cursor data = this.getAllData();
        while(data.moveToNext()){
            if(filtre) {
                if (data.getString(2).equals("0")) {
                    tabBDD.add(data.getString(1));
                }
            }else{
                tabBDD.add(data.getString(1));
            }
        }

        Random rand = new Random();
        int randomPosition;

        for(int i=0;i<nbMots;i++){
            randomPosition = rand.nextInt(tabBDD.size());

            tabMot.add(tabBDD.get(randomPosition));

            tabBDD.remove(randomPosition);
        }
        return tabMot;
    }

    public void insererLesMotsDeBase(){
        this.open();
        for(int i=0;i<tableauMots.length;i++){
            this.insertMot(new Mot(tableauMots[i],false));
        }
        this.close();
    }
}
