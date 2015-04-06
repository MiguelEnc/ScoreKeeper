package com.example.miguel.scorekeeper;

/**
 * Created by EarlLarry on 04/03/15.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PartidaActual extends ActionBarActivity {

    int puntosA = 0;
    int puntosB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_actual);

        final Intent intent = getIntent();

        final DBConnection connection = new DBConnection(PartidaActual.this);

        Button buttonFinalizar = (Button) findViewById(R.id.buttonFinalizar);
        Button buttonAceptarA = (Button) findViewById(R.id.buttonAceptarA);
        Button buttonAceptarB = (Button) findViewById(R.id.buttonAceptarB);
        final EditText editTextEquipoA = (EditText) findViewById(R.id.editTextEquipoA);
        final EditText editTextEquipoB = (EditText) findViewById(R.id.editTextEquipoB);
        final TextView textViewPuntosA = (TextView)findViewById(R.id.textViewPuntosA);
        final TextView textViewPuntosB = (TextView)findViewById(R.id.textViewPuntosB);

        TextView textViewJuego = (TextView) findViewById(R.id.textViewJuego);
        final TextView textViewEquipoA = (TextView) findViewById(R.id.textViewEquipoA);
        final TextView textViewEquipoB = (TextView) findViewById(R.id.textViewEquipoB);

        //Asignando nombres a los textViews
        textViewJuego.setText(intent.getStringExtra("juego"));
        textViewEquipoA.setText(intent.getStringExtra("equipoA"));
        textViewEquipoB.setText(intent.getStringExtra("equipoB"));

        editTextEquipoA.setText("");
        editTextEquipoB.setText("");

        //Cantidad de partidas creadas al momento
        final int numeroDePartidas = connection.getCantidadDePartidas();

        buttonAceptarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int helpA;

                if (editTextEquipoA.getText().toString().isEmpty()) {

                    crearAlertDialog("No hay datos.");
                    editTextEquipoA.setText("");
                    editTextEquipoB.setText("");

                }else{
                    String ayudaA = editTextEquipoA.getText().toString();
                    helpA = Integer.parseInt(ayudaA);
                    connection.insertDetalle(
                            numeroDePartidas + 1,
                            intent.getStringExtra("equipoA"),
                            intent.getStringExtra("equipoB"),
                            ayudaA,
                            "0"
                    );
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

                    crearAlertDialog("No hay datos.");
                    editTextEquipoA.setText("");
                    editTextEquipoB.setText("");

                }else{
                    String ayudaB = editTextEquipoB.getText().toString();
                    helpB = Integer.parseInt(ayudaB);
                    connection.insertDetalle(
                            numeroDePartidas + 1,
                            intent.getStringExtra("equipoA"),
                            intent.getStringExtra("equipoB"),
                            "0",
                            ayudaB
                    );
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

                connection.insertPartida(
                        intent.getStringExtra("juego"),
                        intent.getStringExtra("equipoA"),
                        intent.getStringExtra("equipoB"),
                        puntosA,
                        puntosB
                );

                if (puntosA > puntosB){
                    crearAlertDialog("El ganador es: "+ textViewEquipoA.getText().toString() +"!");

                }else if (puntosB > puntosA){
                    crearAlertDialog("El ganador es: "+ textViewEquipoB.getText().toString() +"!");

                }else if (puntosA == puntosB){
                    crearAlertDialog("Es un empate!");

                }

            }
        });
    }

    //crea AlertDialog con el texto de parametro
    private void crearAlertDialog(String texto){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(PartidaActual.this);
        builder1.setMessage(texto);
        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        Intent intent = new Intent(PartidaActual.this, MainActivity.class);
                        intent.putExtra("Actualizar", "si");
                        startActivity(intent);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
