package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        // Initialisation des vues
        EditText nomClient = findViewById(R.id.nomclient);
        Spinner categorieSpinner = findViewById(R.id.categorie);
        EditText codeClient = findViewById(R.id.codeclient);
        EditText dateRaccordement = findViewById(R.id.dateraccordement);
        EditText numeroSerie = findViewById(R.id.numeroserie);
        Spinner nomVillageSpinner = findViewById(R.id.nomvillage);
        Spinner modePaymentSpinner = findViewById(R.id.modepayment);
        EditText activite = findViewById(R.id.activite);
        Button ajouterButton = findViewById(R.id.ajouter_button);

        // Spinner pour "categorie"
        String[] categories = {"Sélectionnez une catégorie", "IP", "UP", "Ménage"};
        ArrayAdapter<String> categorieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorieSpinner.setAdapter(categorieAdapter);

        // Spinner pour "Nom Village"
        String[] villages = {"Sélectionnez un village", "Marosely", "Ampasindava", "Mangaoka", "Ambatonikolahy"};
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villages);
        villageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nomVillageSpinner.setAdapter(villageAdapter);

        // Spinner pour "Mode Payment"
        String[] paymentModes = {"Sélectionnez un mode de paiement", "PREP", "POP"};
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentModes);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modePaymentSpinner.setAdapter(paymentAdapter);

        // Action pour le bouton Ajouter
        ajouterButton.setOnClickListener(v -> {
            String nom = nomClient.getText().toString().trim();
            String categorie = categorieSpinner.getSelectedItem().toString();
            String code = codeClient.getText().toString().trim();
            String date = dateRaccordement.getText().toString().trim();
            String numero = numeroSerie.getText().toString().trim();
            String village = nomVillageSpinner.getSelectedItem().toString();
            String modePayment = modePaymentSpinner.getSelectedItem().toString();
            String activiteValue = activite.getText().toString().trim();

            // Vérification des champs
            if (nom.isEmpty() || categorie.equals("Sélectionnez une catégorie") || code.isEmpty() ||
                    date.isEmpty() || numero.isEmpty() || village.equals("Sélectionnez un village") ||
                    modePayment.equals("Sélectionnez un mode de paiement") || activiteValue.isEmpty()) {
                Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
            } else {
                // Envoyer les données à EditClient
                Intent intent = new Intent(MainActivity.this, EditClient.class);
                intent.putExtra("nomclient", nom);
                intent.putExtra("categorie", categorie);
                intent.putExtra("codeclient", code);
                intent.putExtra("dateraccordement", date);
                intent.putExtra("numeroserie", numero);
                intent.putExtra("nomvillage", village);
                intent.putExtra("modepayment", modePayment);
                intent.putExtra("activite", activiteValue);
                startActivity(intent);
            }
        });
    }
}
