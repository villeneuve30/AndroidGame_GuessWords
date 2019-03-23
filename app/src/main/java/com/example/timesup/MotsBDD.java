package com.example.timesup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    }

    public void ajout100PremiersMots(){
        this.insertMot(new Mot("Mur",false));
        this.insertMot(new Mot("Silence",false));
        this.insertMot(new Mot("Mouette",false));
        this.insertMot(new Mot("Printemps",false));
        this.insertMot(new Mot("Ordinateur",false));
        this.insertMot(new Mot("Crabe",false));
        this.insertMot(new Mot("Chien",false));
        this.insertMot(new Mot("Tambour",false));
        this.insertMot(new Mot("Triangle",false));
        this.insertMot(new Mot("Pluie",false));
        this.insertMot(new Mot("Poivre",false));
        this.insertMot(new Mot("Bonbons",false));
        this.insertMot(new Mot("Clé",false));
        this.insertMot(new Mot("Anneau",false));
        this.insertMot(new Mot("Œufs",false));
        this.insertMot(new Mot("Route",false));
        this.insertMot(new Mot("Tomate",false));
        this.insertMot(new Mot("Cuisinier",false));
        this.insertMot(new Mot("Famille",false));
        this.insertMot(new Mot("Voler",false));
        this.insertMot(new Mot("Loup",false));
        this.insertMot(new Mot("Chemise",false));
        this.insertMot(new Mot("Lune",false));
        this.insertMot(new Mot("Vent",false));
        this.insertMot(new Mot("Arbre",false));
        this.insertMot(new Mot("Papillon",false));
        this.insertMot(new Mot("Tigre",false));
        this.insertMot(new Mot("Fantôme",false));
        this.insertMot(new Mot("Cheval",false));
        this.insertMot(new Mot("Casserole",false));
        this.insertMot(new Mot("Crayon",false));
        this.insertMot(new Mot("Professeur",false));
        this.insertMot(new Mot("Botte",false));
        this.insertMot(new Mot("Nuage",false));
        this.insertMot(new Mot("Abeille",false));
        this.insertMot(new Mot("Loupe",false));
        this.insertMot(new Mot("Poulet",false));
        this.insertMot(new Mot("Camion",false));
        this.insertMot(new Mot("Araignée",false));
        this.insertMot(new Mot("Pêcheur",false));
        this.insertMot(new Mot("Livre",false));
        this.insertMot(new Mot("Lit",false));
        this.insertMot(new Mot("Ballon",false));
        this.insertMot(new Mot("Rire",false));
        this.insertMot(new Mot("Enfant",false));
        this.insertMot(new Mot("Montagne",false));
        this.insertMot(new Mot("Rapide",false));
        this.insertMot(new Mot("Chèvre",false));
        this.insertMot(new Mot("Banane",false));
        this.insertMot(new Mot("Mer",false));
        this.insertMot(new Mot("Soldat",false));
        this.insertMot(new Mot("Robinet",false));
        this.insertMot(new Mot("Grand-mère",false));
        this.insertMot(new Mot("École",false));
        this.insertMot(new Mot("Manchot",false));
        this.insertMot(new Mot("Oignon",false));
        this.insertMot(new Mot("Serviette",false));
        this.insertMot(new Mot("Valise",false));
        this.insertMot(new Mot("Argent",false));
        this.insertMot(new Mot("Échelle",false));
        this.insertMot(new Mot("Brosse",false));
        this.insertMot(new Mot("Parapluie",false));
        this.insertMot(new Mot("Yaourt",false));
        this.insertMot(new Mot("Éclairage",false));
        this.insertMot(new Mot("Fenêtre",false));
        this.insertMot(new Mot("Nourriture",false));
        this.insertMot(new Mot("Couche",false));
        this.insertMot(new Mot("Girafe",false));
        this.insertMot(new Mot("Étoile",false));
        this.insertMot(new Mot("Serpent",false));
        this.insertMot(new Mot("Ouïe",false));
        this.insertMot(new Mot("Malade",false));
        this.insertMot(new Mot("Sale",false));
        this.insertMot(new Mot("Laitue",false));
        this.insertMot(new Mot("Suivre",false));
        this.insertMot(new Mot("Burger",false));
        this.insertMot(new Mot("Sifflet",false));
        this.insertMot(new Mot("Laid",false));
        this.insertMot(new Mot("Glace",false));
        this.insertMot(new Mot("Guitare",false));
        this.insertMot(new Mot("Étudiant",false));
        this.insertMot(new Mot("Téléphone",false));
        this.insertMot(new Mot("Fromage",false));
        this.insertMot(new Mot("Trottoir",false));
        this.insertMot(new Mot("Baignoire",false));
        this.insertMot(new Mot("Casque",false));
        this.insertMot(new Mot("Charpentier",false));
        this.insertMot(new Mot("Rivière",false));
        this.insertMot(new Mot("Maillot de bain",false));
        this.insertMot(new Mot("Ciseaux",false));
        this.insertMot(new Mot("Propre",false));
        this.insertMot(new Mot("Poussin",false));
        this.insertMot(new Mot("Baleine",false));
        this.insertMot(new Mot("Trompette",false));
        this.insertMot(new Mot("Diapositive",false));
        this.insertMot(new Mot("Panda",false));
        this.insertMot(new Mot("Hélicoptère",false));
        this.insertMot(new Mot("Cloche",false));
        this.insertMot(new Mot("Poulpe",false));
        this.insertMot(new Mot("Escargot",false));
        this.insertMot(new Mot("Citrouille",false));
    }
}
