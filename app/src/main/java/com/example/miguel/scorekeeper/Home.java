package com.example.miguel.scorekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private List<String> ids = new ArrayList<>();

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Home newInstance(int sectionNumber) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Home() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        DBConnection connection = new DBConnection(Home.this.getActivity());

        Spinner spinnerJuegos = (Spinner) rootView.findViewById(R.id.spinnerJuegosHome);
        final ListView listaPartidas = (ListView) rootView.findViewById(R.id.listViewPartidas);

        //Lista de juegos a mostrar
        spinnerJuegos.setAdapter(listaJuegos(connection));

        //OnItemSelectedListener for Spinner
        spinnerJuegos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                listaPartidas.setAdapter(adapterList(item));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listaPartidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                //item es la opcion que se presiono en el ListView

                Intent intent = new Intent(getActivity(), ListaJugadas.class);



                intent.putExtra("ID_EXTRA", id);

                startActivity(intent);
            }
        });

        //Creando partida default
        if(!connection.partidaExists("juego a", "prueba a", "prueba b")){
            connection.insertPartida("juego a", "prueba a", "prueba b", 3, 2);
        }

        //Aqui se cambia el titulo del fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_section1));

        return rootView;
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
                Home.this.getActivity(),
                android.R.layout.simple_spinner_item,
                lista
        );

        //asigna la forma en la que se presentaran los datos
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    private ArrayAdapter<String> adapterList(String juego){
        DBConnection connection = new DBConnection(Home.this.getActivity());
        List<Partida> partidas = connection.getAllPartidas(juego);
        List<String> items = new ArrayList<>();

        for(int i = 0; i < partidas.size(); i++){
            Partida partida = partidas.get(i);
        //    ids.add(partida.getID);
            items.add(
                    partida.getEquipo_A() + " : " +
                    partida.getPuntajeEquipoA() + "\t\t\t\t" +
                    partida.getEquipo_B() + " : " +
                    partida.getPuntajeEquipoB()
            );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                Home.this.getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );

        return adapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}