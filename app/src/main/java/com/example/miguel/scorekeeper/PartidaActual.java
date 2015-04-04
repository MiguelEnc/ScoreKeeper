package com.example.miguel.scorekeeper;

/**
 * Created by EarlLarry on 04/03/15.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class PartidaActual extends ActionBarActivity {

    int puntosA = 0;
    int puntosB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_actual);

        DBConnection connection = new DBConnection(PartidaActual.this);

        Button buttonFinalizar = (Button) findViewById(R.id.buttonFinalizar);
        Button buttonAceptarA = (Button) findViewById(R.id.buttonAceptarA);
        Button buttonAceptarB = (Button) findViewById(R.id.buttonAceptarB);
        final EditText editTextEquipoA = (EditText) findViewById(R.id.editTextEquipoA);
        final EditText editTextEquipoB = (EditText) findViewById(R.id.editTextEquipoB);
        final TextView textViewPuntosA = (TextView)findViewById(R.id.textViewPuntosA);
        final TextView textViewPuntosB = (TextView)findViewById(R.id.textViewPuntosB);

        editTextEquipoA.setText("");
        editTextEquipoB.setText("");


        buttonAceptarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int helpA;

                if (editTextEquipoA.getText().toString().isEmpty()) {

                    //Alert dialog, Equipo registrado.
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PartidaActual.this);
                    builder1.setMessage("No hay datos.");
                    builder1.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    editTextEquipoA.setText("");
                                    editTextEquipoB.setText("");
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else{

                    String ayudaA = editTextEquipoA.getText().toString();
                    helpA = Integer.parseInt(ayudaA);
                    puntosA += helpA;
                    textViewPuntosA.setText("");
                    textViewPuntosA.setText("" + puntosA);
                    editTextEquipoA.setText("");

                }

            }
        });

        buttonAceptarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int helpB;

                if(editTextEquipoB.getText().toString().isEmpty()){

                    //Alert dialog, Equipo registrado.
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PartidaActual.this);
                    builder1.setMessage("No hay datos.");
                    builder1.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    editTextEquipoA.setText("");
                                    editTextEquipoB.setText("");
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }else{

                    String ayudaB = editTextEquipoB.getText().toString();
                    helpB = Integer.parseInt(ayudaB);
                    puntosB += helpB;
                    textViewPuntosB.setText("");
                    textViewPuntosB.setText(""+puntosB);
                    editTextEquipoB.setText("");
                }



            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextEquipoA.setText("");
                editTextEquipoB.setText("");

                //ir al Main Activity
                Intent intent = new Intent(PartidaActual.this, MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_partida_actual, menu);
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
