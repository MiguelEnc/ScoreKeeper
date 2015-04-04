package com.example.miguel.scorekeeper;

/**
 * Created by EarlLarry on 04/03/15.
 */
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class PartidaActual extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_actual);

        Button buttonFinalizar = (Button) findViewById(R.id.buttonFinalizar);

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ir al Main Activity
                Intent intent = new Intent(PartidaActual.this, MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

}
