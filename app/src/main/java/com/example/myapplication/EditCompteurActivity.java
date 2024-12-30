package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EditCompteurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_compteur);

        ListView listView = findViewById(R.id.compteur_list_view);

        // Charger les données depuis SQLite
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        List<Compteur> compteurs = new ArrayList<>();

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
            String query = "SELECT * FROM " + SQLiteHelper.TABLE_COMPTEUR;  // Assurez-vous que le nom de la table est correct
            try (Cursor cursor = db.rawQuery(query, null)) {
                while (cursor.moveToNext()) {
                    String numeroserie = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_NUMEROSERIE));
                    String datebranchement = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_DATEBRANCHEMENT));

                    compteurs.add(new Compteur(numeroserie, datebranchement));
                }
            }
        }

        // Afficher les données dans le ListView
        CompteurAdapter adapter = new CompteurAdapter(this, compteurs);
        listView.setAdapter(adapter);
    }
}
