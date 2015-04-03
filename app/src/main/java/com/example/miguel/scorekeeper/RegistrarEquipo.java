package com.example.miguel.scorekeeper;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by miguel on 04/02/15.
 */
public class RegistrarEquipo extends android.support.v4.app.Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    Context context = this.getActivity();

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
        final EditText editTextDesc = (EditText) v.findViewById(R.id.editTextDescEquipo);

        final String textoNombre = editTextNombre.getText().toString();
        final String textoDescrip = editTextDesc.getText().toString();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Guardando los datos en la base de datos
                DBConnection connection = new DBConnection(context);
                connection.insertTeam(textoNombre, textoDescrip);

                //Alert dialog, Equipo registrado.
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Equipo agregado.");
                builder1.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                editTextDesc.setText("");
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
                editTextDesc.setText("");
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
