package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Nom de la base de données
    private static final String DATABASE_NAME = "DatabaseAndroidAnka";
    private static final int DATABASE_VERSION = 2; // Augmentez la version pour ajouter une nouvelle table

    // Table Clients
    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_CLIENT_ID = "id";
    public static final String COLUMN_NOM = "nomclient";
    public static final String COLUMN_CATEGORIE = "categorie";
    public static final String COLUMN_CODE = "codeclient";
    public static final String COLUMN_DATE = "dateraccordement";
    public static final String COLUMN_NUMERO = "numeroserie";
    public static final String COLUMN_VILLAGE = "nomvillage";
    public static final String COLUMN_MODE_PAYMENT = "modepayment";
    public static final String COLUMN_ACTIVITE = "activite";

    private static final String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS + " ("
            + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOM + " TEXT, "
            + COLUMN_CATEGORIE + " TEXT, "
            + COLUMN_CODE + " TEXT, "
            + COLUMN_DATE + " TEXT, "
            + COLUMN_NUMERO + " TEXT, "
            + COLUMN_VILLAGE + " TEXT, "
            + COLUMN_MODE_PAYMENT + " TEXT, "
            + COLUMN_ACTIVITE + " TEXT)";

    // Table Compteur
    public static final String TABLE_COMPTEUR = "compteur";
    public static final String COLUMN_COMPTEUR_ID = "id";
    public static final String COLUMN_NUMEROSERIE = "numeroserie";
    public static final String COLUMN_DATEBRANCHEMENT = "datebranchement";

    private static final String CREATE_TABLE_COMPTEUR = "CREATE TABLE " + TABLE_COMPTEUR + " ("
            + COLUMN_COMPTEUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NUMEROSERIE + " TEXT NOT NULL, "
            + COLUMN_DATEBRANCHEMENT + " TEXT NOT NULL)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTS); // Crée la table Clients
        db.execSQL(CREATE_TABLE_COMPTEUR); // Crée la table Compteur
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL(CREATE_TABLE_COMPTEUR); // Ajoute la table Compteur si elle n'existe pas
        }
    }

    // Méthode pour insérer un compteur
    public boolean insertCompteur(String numeroSerie, String dateBranchement) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMEROSERIE, numeroSerie);
        values.put(COLUMN_DATEBRANCHEMENT, dateBranchement);

        long result = db.insert(TABLE_COMPTEUR, null, values);
        return result != -1; // Retourne true si l'insertion a réussi
    }



}
