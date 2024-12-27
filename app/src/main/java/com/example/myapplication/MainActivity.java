package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        EditText nomClient = findViewById(R.id.nomclient);
        Spinner categorieSpinner = findViewById(R.id.categorie);
        EditText codeClient = findViewById(R.id.codeclient);
        EditText dateRaccordement = findViewById(R.id.dateraccordement);
        EditText numeroSerie = findViewById(R.id.numeroserie);
        Spinner nomVillageSpinner = findViewById(R.id.nomvillage);
        Spinner modePaymentSpinner = findViewById(R.id.modepayment);
        EditText activite = findViewById(R.id.activite);
        Button ajouterButton = findViewById(R.id.ajouter_button);

        // Spinner for "categorie"
        String[] categories = {"Sélectionnez une catégorie", "IP", "UP", "Ménage"};
        ArrayAdapter<String> categorieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorieSpinner.setAdapter(categorieAdapter);

        // Spinner for "Nom Village"
        String[] villages = {"Sélectionnez un village", "Marosely", "Ampasindava", "Mangaoka", "Ambatonikolahy"};
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villages);
        villageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nomVillageSpinner.setAdapter(villageAdapter);

        // Spinner for "Mode Payment"
        String[] paymentModes = {"Sélectionnez un mode de paiement", "PREP", "POP"};
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentModes);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modePaymentSpinner.setAdapter(paymentAdapter);

        // Action for "Ajouter" button
        ajouterButton.setOnClickListener(v -> {
            String nom = nomClient.getText().toString().trim();
            String categorie = categorieSpinner.getSelectedItem().toString();
            String code = codeClient.getText().toString().trim();
            String date = dateRaccordement.getText().toString().trim();
            String numero = numeroSerie.getText().toString().trim();
            String village = nomVillageSpinner.getSelectedItem().toString();
            String modePayment = modePaymentSpinner.getSelectedItem().toString();
            String activiteValue = activite.getText().toString().trim();

            // Field validation
            if (nom.isEmpty() || categorie.equals("Sélectionnez une catégorie") || code.isEmpty() ||
                    date.isEmpty() || numero.isEmpty() || village.equals("Sélectionnez un village") ||
                    modePayment.equals("Sélectionnez un mode de paiement") || activiteValue.isEmpty()) {
                Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            } else {
                // Execute database insertion in AsyncTask
                new InsertClientTask().execute(nom, categorie, code, date, numero, village, modePayment, activiteValue);
            }
        });
    }

    // AsyncTask for inserting data into the database
    private class InsertClientTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String nom = params[0];
            String categorie = params[1];
            String code = params[2];
            String date = params[3];
            String numero = params[4];
            String village = params[5];
            String modePayment = params[6];
            String activite = params[7];

            SQLiteHelper dbHelper = new SQLiteHelper(MainActivity.this);
            try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
                // Insert data into SQLite database
                ContentValues values = new ContentValues();
                values.put(SQLiteHelper.COLUMN_NOM, nom);
                values.put(SQLiteHelper.COLUMN_CATEGORIE, categorie);
                values.put(SQLiteHelper.COLUMN_CODE, code);
                values.put(SQLiteHelper.COLUMN_DATE, date);
                values.put(SQLiteHelper.COLUMN_NUMERO, numero);
                values.put(SQLiteHelper.COLUMN_VILLAGE, village);
                values.put(SQLiteHelper.COLUMN_MODE_PAYMENT, modePayment);
                values.put(SQLiteHelper.COLUMN_ACTIVITE, activite);

                long rowId = db.insert(SQLiteHelper.TABLE_CLIENTS, null, values);
                if (rowId != -1) {
                    return "Client ajouté avec succès";  // Success message
                } else {
                    return "Erreur lors de l'ajout du client";  // Error message
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Erreur lors de l'ajout du client: " + e.getMessage();  // Return error message
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();  // Show result or error message
        }
    }
}
