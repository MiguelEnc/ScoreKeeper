package com.example.miguel.scorekeeper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListaJugadas extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jugadas);
        String j = "";

        final ListView listaJugadas = (ListView) findViewById(R.id.listViewPartidas);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            j = (String) extras.get("ID_EXTRA");
        }

        listaJugadas.setAdapter(adapterList(j));

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
                this,
                android.R.layout.simple_spinner_item,
                lista
        );

        //asigna la forma en la que se presentaran los datos
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    private ArrayAdapter<String> adapterList(String juego){
        DBConnection connection = new DBConnection(this);
        List<Partida> partidas = connection.getAllPartidas(juego);
        List<String> items = new ArrayList<>();

        for(int i = 0; i < partidas.size(); i++){
            Partida partida = partidas.get(i);
            items.add(
                    partida.getEquipo_A() + " : " +
                            partida.getPuntajeEquipoA() + "\n" +
                            partida.getEquipo_B() + " : " +
                            partida.getPuntajeEquipoB()
            );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );

        return adapter;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_jugadas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
