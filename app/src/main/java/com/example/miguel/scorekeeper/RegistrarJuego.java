package com.example.miguel.scorekeeper;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;


/**
 * Created by miguel on 04/02/15.
 */
public class RegistrarJuego extends android.support.v4.app.Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public RegistrarJuego() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registrar_juego, container, false);

        Button buttonSave = (Button) v.findViewById(R.id.buttonSaveJuego);
        Button buttonCancel = (Button) v.findViewById(R.id.buttonCancelJuego);
        final EditText editTextNombre = (EditText) v.findViewById(R.id.editTextNombreJuego);
        final EditText editTextDescrip = (EditText) v.findViewById(R.id.editTextDescJuego);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Guardando los datos en la base de datos
                DBConnection connection = new DBConnection(RegistrarJuego.this.getActivity());
                if(editTextNombre.getText().toString() != "" || editTextDescrip.getText().toString() != "")
                    connection.insertGame(editTextNombre.getText().toString(), editTextDescrip.getText().toString());

                //Alert dialog, Juego registrado.
                AlertDialog.Builder builder1 = new AlertDialog.Builder(RegistrarJuego.this.getActivity());
                builder1.setMessage("Juego agregado.");
                builder1.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                editTextDescrip.setText("");
                                editTextNombre.setText("");
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextDescrip.setText("");
                editTextNombre.setText("");

                //ir al Main Activity
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
                getActivity().finish();
            }
        });

        //Aqui se cambia el titulo del fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_section2));

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static RegistrarJuego newInstance (int sectionNumber){
        RegistrarJuego frag = new RegistrarJuego();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }

}
