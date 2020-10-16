package com.example.covid2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button click;
    public static TextView active;
    public static TextView total;
    public static TextView recovered;
    public static TextView deaths;

    public static TextView Wactive;
    public static TextView Wtotal;
    public static TextView Wrecovered;
    public static TextView Wdeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);

        active = (TextView) findViewById(R.id.active);
        total = (TextView) findViewById(R.id.sltotal);
        recovered = (TextView) findViewById(R.id.recovered);
        deaths = (TextView) findViewById(R.id.deaths);

        Wactive = (TextView) findViewById(R.id.Wactive);
        Wtotal = (TextView) findViewById(R.id.Wtotal);
        Wrecovered = (TextView) findViewById(R.id.Wrecovered);
        Wdeaths = (TextView) findViewById(R.id.Wdeaths);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------------------------------check connectivity---------------------------//

                if (isConnected()) {
                    //Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "අන්තර් ජාලයට සම්බන්ධ කරන්න..", Toast.LENGTH_SHORT).show();
                }
                //-------------------
                fetchData process = new fetchData();
                process.execute();
            }
        });

    }


    //-------------check connection status----------//
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    //--------------------------------------//
}
