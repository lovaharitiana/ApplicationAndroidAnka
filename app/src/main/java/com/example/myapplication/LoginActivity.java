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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialiser les vues
        EditText nomUtilisateur = findViewById(R.id.nomutilisateur);
        Spinner fonctionSpinner = findViewById(R.id.fonction);
        Spinner villageSpinner = findViewById(R.id.village);
        Button connexionButton = findViewById(R.id.connexion_button);

        // Spinner pour "Fonction"
        String[] fonctions = {"Sélectionnez une fonction", "Administrateur", "Agent Commercial", "Agent d'exploitation"};
        ArrayAdapter<String> fonctionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fonctions);
        fonctionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fonctionSpinner.setAdapter(fonctionAdapter);

        // Spinner pour "Village"
        String[] villages = {"Sélectionnez un village", "Marosely", "Ampasindava", "Mangaoka", "Ambatonikolahy"};
        ArrayAdapter<String> villageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villages);
        villageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        villageSpinner.setAdapter(villageAdapter);

        // Action pour le bouton "Se connecter"
        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = nomUtilisateur.getText().toString().trim();
                String fonction = fonctionSpinner.getSelectedItem().toString();
                String village = villageSpinner.getSelectedItem().toString();

                // Validation des champs
                if (nom.isEmpty() || fonction.equals("Sélectionnez une fonction") || village.equals("Sélectionnez un village")) {
                    Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                } else {
                    // Logique de redirection en fonction des conditions
                    if (nom.contains("Hoby") && fonction.equals("Agent Commercial")) {
                        // Rediriger vers activity_main.xml
                        Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentMain);
                        finish(); // Ferme l'activité de login pour éviter que l'utilisateur puisse revenir en arrière
                    } else if (nom.contains("Olivia") && fonction.equals("Agent d'exploitation")) {
                        // Rediriger vers activity_ajout_compteur.xml
                        Intent intentAjoutCompteur = new Intent(LoginActivity.this, activity_ajout_compteur.class);
                        startActivity(intentAjoutCompteur);
                        finish(); // Ferme l'activité de login pour éviter que l'utilisateur puisse revenir en arrière
                    } else {
                        // Si les conditions ne sont pas remplies
                        Toast.makeText(LoginActivity.this, "Nom d'utilisateur ou fonction incorrects.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
