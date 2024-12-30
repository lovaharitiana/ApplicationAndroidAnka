package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class CompteurAdapter extends BaseAdapter {
    private Context context;
    private List<Compteur> compteurList;

    public CompteurAdapter(Context context, List<Compteur> compteurList) {
        this.context = context;
        this.compteurList = compteurList;
    }

    @Override
    public int getCount() {
        return compteurList.size();
    }

    @Override
    public Object getItem(int position) {
        return compteurList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_compteur, parent, false);
        }

        final Compteur compteur = compteurList.get(position);

        TextView numeroserieTextView = convertView.findViewById(R.id.text_numeroserie);
        TextView datebranchementTextView = convertView.findViewById(R.id.text_datebranchement);
        Button btnModifier = convertView.findViewById(R.id.btn_modifier);

        numeroserieTextView.setText(compteur.getNumeroserie());
        datebranchementTextView.setText(compteur.getDatebranchement());

        // Show dialog when the "Modifier" button is clicked
        btnModifier.setOnClickListener(v -> {
            // Create the dialog to edit compteur details
            LayoutInflater inflater = LayoutInflater.from(context);
            View dialogView = inflater.inflate(R.layout.activity_dialog_compteur, null);

            EditText editNumeroserie = dialogView.findViewById(R.id.edit_numeroserie);
            EditText editDatebranchement = dialogView.findViewById(R.id.edit_datebranchement);

            // Set current values in the dialog fields
            editNumeroserie.setText(compteur.getNumeroserie());
            editDatebranchement.setText(compteur.getDatebranchement());

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            dialogBuilder.setView(dialogView)
                    .setTitle("Modifier Compteur")
                    .setCancelable(true);

            // Save button click
            Button btnSave = dialogView.findViewById(R.id.btn_save);
            btnSave.setOnClickListener(view -> {
                // Get the updated values
                String newNumeroserie = editNumeroserie.getText().toString();
                String newDatebranchement = editDatebranchement.getText().toString();

                // Update the compteur object
                compteur.setNumeroserie(newNumeroserie);
                compteur.setDatebranchement(newDatebranchement);

                // Notify the adapter that the data has changed
                notifyDataSetChanged();

                // Dismiss the dialog
                dialogBuilder.create().dismiss();
            });

            dialogBuilder.create().show();
        });

        return convertView;
    }
}
