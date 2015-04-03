package com.example.miguel.scorekeeper;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_registrar_juego, container, false);

        Button buttonSave = (Button) v.findViewById(R.id.buttonSaveJuego);
        Button buttonCancel = (Button) v.findViewById(R.id.buttonCancelJuego);
        final EditText editTextNombre = (EditText) v.findViewById(R.id.editTextNombreJuego);
        final EditText editTextDesc = (EditText) v.findViewById(R.id.editTextDescJuego);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                .setActionBarTitle(getString(R.string.title_section2));

        return inflater.inflate(R.layout.fragment_registrar_juego, container, false);
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
