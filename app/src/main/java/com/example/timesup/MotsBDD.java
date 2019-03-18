package com.example.timesup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public int removeMotWithID(int id){
        return bdd.delete(TABLE_MOTS, COL_ID + " = " +id,null);
    }

    public Mot getMotWithTexte(String texte){
        Cursor c = bdd.query(TABLE_MOTS, new String[]{COL_ID, COL_MOT, COL_FILTRE},COL_MOT + " LIKE \"" + texte +"\"", null, null, null, null);
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
}
