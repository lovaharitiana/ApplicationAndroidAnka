package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EditClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);

        // Récupérer les données de l'intent
        String nomClient = getIntent().getStringExtra("nomclient");
        String categorie = getIntent().getStringExtra("categorie");
        String codeClient = getIntent().getStringExtra("codeclient");
        String dateRaccordement = getIntent().getStringExtra("dateraccordement");
        String numeroSerie = getIntent().getStringExtra("numeroserie");
        String nomVillage = getIntent().getStringExtra("nomvillage");
        String modePayment = getIntent().getStringExtra("modepayment");
        String activite = getIntent().getStringExtra("activite");

        // Ajouter les données dans un tableau
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        TableRow row = new TableRow(this);

        // Ajouter les colonnes
        row.addView(createTextView(nomClient));
        row.addView(createTextView(categorie));
        row.addView(createTextView(codeClient));
        row.addView(createTextView(dateRaccordement));
        row.addView(createTextView(numeroSerie));
        row.addView(createTextView(nomVillage));
        row.addView(createTextView(modePayment));
        row.addView(createTextView(activite));

        tableLayout.addView(row);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(16, 16, 16, 16);
        return textView;
    }
}
