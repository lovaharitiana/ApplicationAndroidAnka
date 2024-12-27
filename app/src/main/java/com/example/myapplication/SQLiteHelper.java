package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    // Spécifiez le nom de la base de données
    private static final String DATABASE_NAME = "DatabaseAndroidAnka"; // Nom de la base de données
    private static final int DATABASE_VERSION = 1;

    // Table Clients
    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nomclient";
    public static final String COLUMN_CATEGORIE = "categorie";
    public static final String COLUMN_CODE = "codeclient";
    public static final String COLUMN_DATE = "dateraccordement";
    public static final String COLUMN_NUMERO = "numeroserie";
    public static final String COLUMN_VILLAGE = "nomvillage";
    public static final String COLUMN_MODE_PAYMENT = "modepayment";
    public static final String COLUMN_ACTIVITE = "activite";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CLIENTS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOM + " TEXT, "
            + COLUMN_CATEGORIE + " TEXT, "
            + COLUMN_CODE + " TEXT, "
            + COLUMN_DATE + " TEXT, "
            + COLUMN_NUMERO + " TEXT, "
            + COLUMN_VILLAGE + " TEXT, "
            + COLUMN_MODE_PAYMENT + " TEXT, "
            + COLUMN_ACTIVITE + " TEXT)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE); // Crée la table si elle n'existe pas
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        onCreate(db); // Re-crée la table lors de la mise à jour
    }
}
