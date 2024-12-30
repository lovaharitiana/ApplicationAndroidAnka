package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {
    public ClientAdapter(Context context, List<Client> clients) {
        super(context, 0, clients);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Client client = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_client, parent, false);
        }

        // Liaison des vues
        TextView textNom = convertView.findViewById(R.id.text_nom);
        TextView textCategorie = convertView.findViewById(R.id.text_categorie);
        TextView textCode = convertView.findViewById(R.id.text_code);
        TextView textDate = convertView.findViewById(R.id.text_date);
        TextView textNumero = convertView.findViewById(R.id.text_numero);
        TextView textVillage = convertView.findViewById(R.id.text_village);
        TextView textModePayment = convertView.findViewById(R.id.text_mode_payment);
        TextView textActivite = convertView.findViewById(R.id.text_activite);
        Button btnModifier = convertView.findViewById(R.id.btn_modifier);

        // Affectation des données
        textNom.setText("Nom : " + client.getNom());
        textCategorie.setText("Catégorie : " + client.getCategorie());
        textCode.setText("Code : " + client.getCode());
        textDate.setText("Date : " + client.getDate());
        textNumero.setText("NuméroSérie : " + client.getNumero());
        textVillage.setText("Village : " + client.getVillage());
        textModePayment.setText("Paiement : " + client.getModePayment());
        textActivite.setText("Activité : " + client.getActivite());

        // Configuration du bouton Modifier
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez l'AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                // Définissez le layout personnalisé
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View dialogView = inflater.inflate(R.layout.activity_dialog_client, null);
                builder.setView(dialogView);

                // Récupérez les vues du dialog pour les remplir avec les données du client
                EditText editNom = dialogView.findViewById(R.id.edit_nom);
                Spinner spinnerCategorie = dialogView.findViewById(R.id.spinner_categorie);
                EditText editCode = dialogView.findViewById(R.id.edit_code);
                EditText editDate = dialogView.findViewById(R.id.edit_date);
                EditText editNumero = dialogView.findViewById(R.id.edit_numero);
                Spinner spinnerVillage = dialogView.findViewById(R.id.spinner_village);
                Spinner spinnerPayment = dialogView.findViewById(R.id.spinner_payment);
                EditText editActivite = dialogView.findViewById(R.id.edit_activite);

                // Remplissez les champs avec les données actuelles du client
                editNom.setText(client.getNom());

                // Définir les options pour les Spinners
                String[] categories = {"Sélectionnez une catégorie", "IP", "UP", "Ménage"};
                ArrayAdapter<String> categorieAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
                categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategorie.setAdapter(categorieAdapter);

                String[] villages = {"Sélectionnez un village", "Marosely", "Ampasindava", "Mangaoka", "Ambatonikolahy"};
                ArrayAdapter<String> villageAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, villages);
                villageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerVillage.setAdapter(villageAdapter);

                String[] paymentModes = {"Sélectionnez un mode de paiement", "PREP", "POP"};
                ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paymentModes);
                paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPayment.setAdapter(paymentAdapter);

                // Vérifiez si la catégorie existe dans le tableau avant de sélectionner un index
                String currentCategorie = client.getCategorie();
                if (currentCategorie != null && !currentCategorie.isEmpty()) {
                    int categorieIndex = ((ArrayAdapter<String>) spinnerCategorie.getAdapter()).getPosition(currentCategorie);
                    if (categorieIndex >= 0) {
                        spinnerCategorie.setSelection(categorieIndex);
                    }
                }

                // Vérifiez si le village existe dans le tableau avant de sélectionner un index
                String currentVillage = client.getVillage();
                if (currentVillage != null && !currentVillage.isEmpty()) {
                    int villageIndex = ((ArrayAdapter<String>) spinnerVillage.getAdapter()).getPosition(currentVillage);
                    if (villageIndex >= 0) {
                        spinnerVillage.setSelection(villageIndex);
                    }
                }

                // Vérifiez si le mode de paiement existe dans le tableau avant de sélectionner un index
                String currentPayment = client.getModePayment();
                if (currentPayment != null && !currentPayment.isEmpty()) {
                    int paymentIndex = ((ArrayAdapter<String>) spinnerPayment.getAdapter()).getPosition(currentPayment);
                    if (paymentIndex >= 0) {
                        spinnerPayment.setSelection(paymentIndex);
                    }
                }

                // Remplir les autres champs
                editCode.setText(client.getCode());
                editDate.setText(client.getDate());
                editNumero.setText(client.getNumero());
                editActivite.setText(client.getActivite());

                builder.setTitle("Modifier Client")
                        .setPositiveButton("Sauvegarder", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Récupérez les nouvelles valeurs des Spinners et des EditText
                                String categorie = spinnerCategorie.getSelectedItem().toString();
                                String village = spinnerVillage.getSelectedItem().toString();
                                String payment = spinnerPayment.getSelectedItem().toString();

                                String nom = editNom.getText().toString();
                                String code = editCode.getText().toString();
                                String date = editDate.getText().toString();
                                String numero = editNumero.getText().toString();
                                String activite = editActivite.getText().toString();

                                // Mettre à jour l'objet client avec les nouvelles valeurs
                                client.setNom(nom);
                                client.setCategorie(categorie);
                                client.setCode(code);
                                client.setDate(date);
                                client.setNumero(numero);
                                client.setVillage(village);
                                client.setModePayment(payment);
                                client.setActivite(activite);

                                // Actualiser l'affichage de la ListView
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Annuler", null);

                builder.create().show();

            }
        });

        return convertView;
    }
}
