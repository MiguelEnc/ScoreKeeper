package com.example.miguel.scorekeeper;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
public class RegistrarEquipo extends android.support.v4.app.Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public RegistrarEquipo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registrar_equipo, container, false);

        Button buttonSave = (Button) v.findViewById(R.id.buttonSaveEquipo);
        Button buttonCancel = (Button) v.findViewById(R.id.buttonCancelEquipo);
        final EditText editTextNombre = (EditText) v.findViewById(R.id.editTextNombreEquipo);
        final EditText editTextDescrip = (EditText) v.findViewById(R.id.editTextDescEquipo);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBConnection connection = new DBConnection(RegistrarEquipo.this.getActivity());

                //Creando equipos default
                if(!connection.teamExists("prueba a")){
                    connection.insertTeam("prueba a","descripcion");
                }
                if(!connection.teamExists("prueba b")){
                    connection.insertTeam("prueba b","descripcion");
                }

                //Guardando los datos en la base de datos
                if(editTextNombre.getText().toString().isEmpty() || editTextDescrip.getText().toString().isEmpty()) {

                    Toast.makeText(RegistrarEquipo.this.getActivity(),
                            "Llenar campos.", Toast.LENGTH_LONG).show();

                }else{
                    //comprobando si el nombre ya existe en la base de datos
                    if(connection.teamExists(editTextNombre.getText().toString())){
                        //el equipo ya existe
                        crearAlertDialog("El equipo ya existe.");
                        editTextNombre.setText("");
                    }else {
                        //el juego no existe, guardar
                        connection.insertTeam(editTextNombre.getText().toString(), editTextDescrip.getText().toString());
                        crearAlertDialog("Equipo agregado.");
                        editTextDescrip.setText("");
                        editTextNombre.setText("");
                    }
                }
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
                .setActionBarTitle(getString(R.string.title_section3));

        return v;
    }

    //crea AlertDialog con el texto de parametro
    private void crearAlertDialog(String texto){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(RegistrarEquipo.this.getActivity());
        builder1.setMessage(texto);
        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static RegistrarEquipo newInstance (int sectionNumber){
        RegistrarEquipo frag = new RegistrarEquipo();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }

}
