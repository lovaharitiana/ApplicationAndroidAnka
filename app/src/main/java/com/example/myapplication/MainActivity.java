package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner for "categorie"
        Spinner spinner = findViewById(R.id.categorie); // Assure-toi que l'ID correspond à celui défini dans activity_main.xml
        String[] categories = {"Sélectionnez une catégorie", "IP", "UP", "Ménage"}; // Données pour le Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Spinner for "Nom Village"
        Spinner nomVillageSpinner = findViewById(R.id.nomvillage);
        String[] villages = {"Sélectionnez un village", "Marosely", "Ampasindava", "Mangaoka", "Ambatonikolahy", "Ambohimalaza", "Antanamalaza", "Amparaky", "Dondona", "Antsampandrano", "Mangily", "Ifaty", "Beravy", "Anakao", "Andranohinaly", "Ambohimahavelona", "Andavadoaka", "Befandefa", "Ambahikily", "Ankililoaka"};
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villages);
        villageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nomVillageSpinner.setAdapter(villageAdapter);

        // Spinner for "Mode Payment"
        Spinner modePaymentSpinner = findViewById(R.id.modepayment);
        String[] paymentModes = {"Sélectionnez un mode de paiement", "PREP", "POP"};
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentModes);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modePaymentSpinner.setAdapter(paymentAdapter);

    }
}