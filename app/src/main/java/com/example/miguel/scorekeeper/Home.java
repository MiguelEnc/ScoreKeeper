package com.example.miguel.scorekeeper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miguel.scorekeeper.MainActivity;
import com.example.miguel.scorekeeper.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

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

        ListView listaPartidas = (ListView) rootView.findViewById(R.id.listViewPartidas);
        listaPartidas.setAdapter(adapterList());

        //Aqui se cambia el titulo del fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_section1));

        return rootView;
    }

    private ArrayAdapter<String> adapterList(){
        DBConnection connection = new DBConnection(Home.this.getActivity());
        List<Partida> partidas = connection.getAllPartidas();
        List<String> items = new ArrayList<>();

                for(int i = 0; i < partidas.size(); i++){
                    Partida partida = partidas.get(i);
                    items.add(
                            partida.getJuego() + "\n" +
                                    partida.getEquipo_A() + " : " +
                                    partida.getPuntajeEquipoA() + "\n" +
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