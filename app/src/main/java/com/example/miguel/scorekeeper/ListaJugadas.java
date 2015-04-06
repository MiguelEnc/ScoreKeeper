package com.example.miguel.scorekeeper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListaJugadas extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugadas);
        String id = "";

        ListView listaJugadas = (ListView) findViewById(R.id.listViewDetalle);
        Button buttonVolver = (Button) findViewById(R.id.buttonVolver);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            id = String.valueOf(extras.get("ID_EXTRA"));
        }
        listaJugadas.setAdapter(adapterList(id));

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaJugadas.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private ArrayAdapter<String> adapterList(String id){
        DBConnection connection = new DBConnection(this);
        ArrayList<String> jugadas = connection.getDetalleDePartidaByID(id);
        List<String> items = new ArrayList<>();

        for(int i = 0; i < jugadas.size(); i++){
            String jugada = jugadas.get(i);
            items.add(jugada);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );

        return adapter;
    }
}
