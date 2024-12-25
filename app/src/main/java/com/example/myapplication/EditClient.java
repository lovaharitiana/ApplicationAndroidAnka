package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class EditClient extends AppCompatActivity {

    private TableLayout tableLayout;
    private TableRow currentRow;  // Variable pour garder une référence à la ligne actuellement modifiée

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
        tableLayout = findViewById(R.id.tableLayout);

        TableRow row = new TableRow(this);

        // Ajouter les colonnes de données
        row.addView(createTextView(nomClient));
        row.addView(createTextView(categorie));
        row.addView(createTextView(codeClient));
        row.addView(createTextView(dateRaccordement));
        row.addView(createTextView(numeroSerie));
        row.addView(createTextView(nomVillage));
        row.addView(createTextView(modePayment));
        row.addView(createTextView(activite));

        // Ajouter un bouton de modification (icône)
        row.addView(createEditIcon(nomClient, categorie, codeClient, dateRaccordement, numeroSerie, nomVillage, modePayment, activite, row));

        // Ajouter la ligne au tableau
        tableLayout.addView(row);
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(16, 16, 16, 16);
        return textView;
    }

    private ImageView createEditIcon(final String nomClient, final String categorie, final String codeClient,
                                     final String dateRaccordement, final String numeroSerie, final String nomVillage,
                                     final String modePayment, final String activite, final TableRow row) {
        // Créer un ImageView pour l'icône de modification
        ImageView editIcon = new ImageView(this);
        editIcon.setImageResource(android.R.drawable.ic_menu_edit);  // Icône par défaut Android
        editIcon.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        ));

        // Ajouter un listener pour réagir au clic sur l'icône de modification
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Afficher un dialog pour modifier les informations du client
                currentRow = row; // Sauvegarder la ligne actuelle pour la mise à jour
                showEditDialog(nomClient, categorie, codeClient, dateRaccordement, numeroSerie, nomVillage, modePayment, activite);
            }
        });

        return editIcon;
    }

    private void showEditDialog(final String nomClient, final String categorie, final String codeClient,
                                final String dateRaccordement, final String numeroSerie, final String nomVillage,
                                final String modePayment, final String activite) {
        // Créer un dialog pour modifier les informations du client
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier le client");

        // Créer un layout personnalisé pour le dialog
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_client, null);

        // Initialiser les champs du dialog avec les données existantes
        final EditText editNomClient = dialogView.findViewById(R.id.editNomClient);
        final EditText editCategorie = dialogView.findViewById(R.id.editCategorie);
        final EditText editCodeClient = dialogView.findViewById(R.id.editCodeClient);
        final EditText editDateRaccordement = dialogView.findViewById(R.id.editDateRaccordement);
        final EditText editNumeroSerie = dialogView.findViewById(R.id.editNumeroSerie);
        final EditText editNomVillage = dialogView.findViewById(R.id.editNomVillage);
        final EditText editModePayment = dialogView.findViewById(R.id.editModePayment);
        final EditText editActivite = dialogView.findViewById(R.id.editActivite);

        // Remplir les champs avec les informations actuelles
        editNomClient.setText(nomClient);
        editCategorie.setText(categorie);
        editCodeClient.setText(codeClient);
        editDateRaccordement.setText(dateRaccordement);
        editNumeroSerie.setText(numeroSerie);
        editNomVillage.setText(nomVillage);
        editModePayment.setText(modePayment);
        editActivite.setText(activite);

        builder.setView(dialogView)
                .setPositiveButton("Enregistrer", (dialog, which) -> {
                    // Récupérer les nouvelles données et les mettre à jour
                    String updatedNomClient = editNomClient.getText().toString();
                    String updatedCategorie = editCategorie.getText().toString();
                    String updatedCodeClient = editCodeClient.getText().toString();
                    String updatedDateRaccordement = editDateRaccordement.getText().toString();
                    String updatedNumeroSerie = editNumeroSerie.getText().toString();
                    String updatedNomVillage = editNomVillage.getText().toString();
                    String updatedModePayment = editModePayment.getText().toString();
                    String updatedActivite = editActivite.getText().toString();

                    // Mettre à jour les données dans le tableau
                    updateTableRow(updatedNomClient, updatedCategorie, updatedCodeClient, updatedDateRaccordement,
                            updatedNumeroSerie, updatedNomVillage, updatedModePayment, updatedActivite);
                })
                .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    // Méthode pour mettre à jour la ligne dans le tableau
    private void updateTableRow(String nomClient, String categorie, String codeClient, String dateRaccordement,
                                String numeroSerie, String nomVillage, String modePayment, String activite) {
        // Mettre à jour chaque TextView de la ligne avec les nouvelles données
        ((TextView) currentRow.getChildAt(0)).setText(nomClient);
        ((TextView) currentRow.getChildAt(1)).setText(categorie);
        ((TextView) currentRow.getChildAt(2)).setText(codeClient);
        ((TextView) currentRow.getChildAt(3)).setText(dateRaccordement);
        ((TextView) currentRow.getChildAt(4)).setText(numeroSerie);
        ((TextView) currentRow.getChildAt(5)).setText(nomVillage);
        ((TextView) currentRow.getChildAt(6)).setText(modePayment);
        ((TextView) currentRow.getChildAt(7)).setText(activite);
    }
}
