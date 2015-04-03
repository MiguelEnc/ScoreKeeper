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

import java.util.ArrayList;
import java.util.List;


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
        Spinner spinnerJuegos = (Spinner) v.findViewById(R.id.spinnerJuego);

        //Coneccion a la base de datos
        DBConnection connection = new DBConnection(context);

        //Lista de juegos a mostrar
        spinnerJuegos.setAdapter(listaJuegos(connection));

        //Lista de equipos a mostrar
        spinnerEquipoA.setAdapter(listaEquipos(connection));

        //Lista de equipos a mostrar
        spinnerEquipoB.setAdapter(listaEquipos(connection));


        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: llevar a PartidaEnCurso
            }
        });


        buttonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ir a partida actual
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


    private ArrayAdapter<String> listaJuegos(DBConnection connection){
        ArrayList<String> lista = new ArrayList<>();

        //Carga todos los juegos de la base de datos
        List<Juego> juegos = connection.getAllGames();

        //llena la lista con los nombres de los juegos
        for(int i = 0; i < juegos.size(); i++){
            Juego juego = juegos.get(i);
            lista.add(juego.getNombre().toString());
        }

        //crea el adapter para llenar el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                lista
        );

        //asigna la forma en la que se presentaran los datos
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    private ArrayAdapter<String> listaEquipos(DBConnection connection){
        ArrayList<String> lista = new ArrayList<>();

        //Carga todos los equipos de la base de datos
        List<Equipo> equipos = connection.getAllTeams();

        //llena la lista con los nombres de los equipos
        for(int i = 0; i < equipos.size(); i++){
            Equipo equipo = equipos.get(i);
            lista.add(equipo.getNombre().toString());
        }

        //crea el adapter para llenar el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item,
                lista
        );

        //asigna la forma en la que se presentaran los datos
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }


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
