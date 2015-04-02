package com.example.miguel.scorekeeper;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarPartida extends android.support.v4.app.Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public RegistrarPartida() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //Aqui se cambia el titulo del fragment
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.title_section4));

        return inflater.inflate(R.layout.fragment_registrar_partida, container, false);
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
