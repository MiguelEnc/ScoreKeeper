package com.example.miguel.scorekeeper;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * Created by miguel on 04/03/15.
 */
public class RegistrarPartida extends android.support.v4.app.Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    Context context = this.getActivity();

    public RegistrarPartida() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registrar_partida, container, false);

        Button buttonIniciar = (Button) v.findViewById(R.id.buttonIniciarPartida);
        Spinner spinnerEquipoA = (Spinner) v.findViewById(R.id.spinnerEquipoA);
        Spinner spinnerEquipoB = (Spinner) v.findViewById(R.id.spinnerEquipoB);

        //Coneccion a la base de datos
        DBConnection connection = new DBConnection(context);

        //arrayAdapter para popular el Spinner
        //ArrayAdapter<String> listaJuegos = datosJuegos(connection);

        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ir al Main Activity
                Intent intent = new Intent(v.getContext(), PartidaActual.class);
                v.getContext().startActivity(intent);
                getActivity().finish();
            }
        });

        //Aqui se cambia el titulo del fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_section4));

        return v;
    }

    /*
    private ArrayAdapter<String> datosJuegos(DBConnection connection){
        ArrayAdapter<String> lista = new ArrayAdapter<String>();

        return lista;
    }
    */

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static RegistrarPartida newInstance (int sectionNumber){
        RegistrarPartida frag = new RegistrarPartida();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }


}
