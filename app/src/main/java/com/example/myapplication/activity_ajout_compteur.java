package com.example.myapplication;

import android.content.Intent; // Ajoutez cette importation pour pouvoir utiliser les Intents
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_ajout_compteur extends AppCompatActivity {

    private EditText numeroserieEditText;
    private EditText datebranchementEditText;
    private Button ajouterCompteurButton;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_compteur);

        // Initialiser les vues
        numeroserieEditText = findViewById(R.id.numeroserie);
        datebranchementEditText = findViewById(R.id.datebranchement);
        ajouterCompteurButton = findViewById(R.id.ajouter_compteur_button);

        // Initialiser SQLiteHelper
        dbHelper = new SQLiteHelper(this);

        // Définir l'action du bouton
        ajouterCompteurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs des champs
                String numeroSerie = numeroserieEditText.getText().toString();
                String dateBranchement = datebranchementEditText.getText().toString();

                // Vérifier si les champs sont remplis
                if (numeroSerie.isEmpty() || dateBranchement.isEmpty()) {
                    Toast.makeText(activity_ajout_compteur.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Appeler la méthode d'insertion
                    boolean isInserted = dbHelper.insertCompteur(numeroSerie, dateBranchement);

                    // Afficher un message selon le résultat
                    if (isInserted) {
                        Toast.makeText(activity_ajout_compteur.this, "Compteur ajouté avec succès", Toast.LENGTH_SHORT).show();

                        // Rediriger vers activity_edit_compteur après l'ajout réussi
                        Intent intent = new Intent(activity_ajout_compteur.this, EditCompteurActivity.class);
                        startActivity(intent); // Lancer l'activité
                        finish(); // Facultatif, pour fermer l'activité actuelle (activity_ajout_compteur)
                    } else {
                        Toast.makeText(activity_ajout_compteur.this, "Échec de l'ajout du compteur", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
