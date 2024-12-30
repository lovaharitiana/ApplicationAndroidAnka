package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EditClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        ListView listView = findViewById(R.id.client_list_view);

        // Charger les données depuis SQLite
        SQLiteHelper dbHelper = new SQLiteHelper(this);
        List<Client> clients = new ArrayList<>();

        try (SQLiteDatabase db = dbHelper.getReadableDatabase()) {
            String query = "SELECT * FROM " + SQLiteHelper.TABLE_CLIENTS + " ORDER BY ROWID DESC LIMIT 1";
            try (Cursor cursor = db.rawQuery(query, null)) {
                if (cursor.moveToFirst()) {
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_NOM));
                    String categorie = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_CATEGORIE));
                    String code = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_CODE));
                    String date = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_DATE));
                    String numero = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_NUMERO));
                    String village = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_VILLAGE));
                    String modePayment = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_MODE_PAYMENT));
                    String activite = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_ACTIVITE));

                    clients.add(new Client(nom, categorie, code, date, numero, village, modePayment, activite));
                }

            }
        }

        // Afficher les données dans le ListView
        ClientAdapter adapter = new ClientAdapter(this, clients);
        listView.setAdapter(adapter);
    }
}
