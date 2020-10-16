package com.example.covid2;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, Void> {

    String data = "";
    String active = "";
    String total = "";
    String recovered = "";
    String deaths = "";

    String Wactive = "";
    String Wtotal = "";
    String Wrecovered = "";
    String Wdeaths = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://www.hpb.health.gov.lk/api/get-current-statistical");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

           /* JSONObject reader = new JSONObject(data);
            result = reader.getString("cases");*/

            JSONObject reader = new JSONObject(data);

            JSONObject alldata = reader.getJSONObject("data");

            total = alldata.getString("local_total_cases");
            active = alldata.getString("local_active_cases");
            recovered = alldata.getString("local_recovered");
            deaths = alldata.getString("local_deaths");

            Wtotal = alldata.getString("global_total_cases");
            Wactive = alldata.getString("global_new_cases");
            Wrecovered = alldata.getString("global_recovered");
            Wdeaths = alldata.getString("global_deaths");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.total.setText(this.total);
        MainActivity.active.setText(this.active);
        MainActivity.recovered.setText(this.recovered);
        MainActivity.deaths.setText(this.deaths);

        MainActivity.Wtotal.setText(this.Wtotal);
        MainActivity.Wactive.setText(this.Wactive);
        MainActivity.Wrecovered.setText(this.Wrecovered);
        MainActivity.Wdeaths.setText(this.Wdeaths);

    }
}