package com.example.miguel.scorekeeper;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartidaActual extends Fragment {


    public PartidaActual() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_registrar_juego, container, false);

        Button buttonSave = (Button) v.findViewById(R.id.buttonAgregar);
        Button buttonFinalizar = (Button) v.findViewById(R.id.buttonFinalizar);
        final EditText editTextEquipoA = (EditText) v.findViewById(R.id.editTextEquipoA);
        final EditText editTextEquipoB = (EditText) v.findViewById(R.id.editTextEquipoB);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEquipoA.setText("");
                editTextEquipoB.setText("");

                //ir al Main Activity
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
                getActivity().finish();

            }
        });


        return inflater.inflate(R.layout.fragment_partida_actual, container, false);
    }


}
