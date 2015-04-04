package com.example.miguel.scorekeeper;

/**
 * Created by EarlLarry on 29-Mar-15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


public class Splash extends Activity {

    private static int splashInterval = 5000;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                // TODO Auto-generated method stub

                Intent i = new Intent(Splash.this, MainActivity.class);
                i.putExtra("Actualizar", "si");
                startActivity(i);

                this.finish();
            }


            private void finish() {

                // TODO Auto-generated method stub


            }
        }, splashInterval);

    };
}

