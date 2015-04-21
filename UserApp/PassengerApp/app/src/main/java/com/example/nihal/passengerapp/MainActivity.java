package com.example.nihal.passengerapp;



import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import org.json.JSONArray;


public class MainActivity extends ActionBarActivity  {


    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeRequest();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void makeRequest(){

        GPSTracker gps = new GPSTracker(MainActivity.this);
        if(gps.canGetLocation()){
            Log.d("inside", "HERE!");
            String latitude = Double.toString(gps.getLatitude());
            String longitude = Double.toString(gps.getLongitude());
            String id = "9";
            new QueryServer(latitude, longitude, id).execute(new ApiConnector());
            gps.stopUsingGPS();
            Toast.makeText(MainActivity.this, "Latitude"+latitude+"Longitude"+longitude, Toast.LENGTH_LONG).show();
        }


    }

    private class QueryServer extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        String Latitude;
        String Longitude;
        String ID;
        public QueryServer(String lat, String lon, String id){
            Latitude = lat;
            Longitude = lon;
            ID = id;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].sendCurrentPosition(Latitude, Longitude, ID);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            pDialog.dismiss();
            //login_check(jsonArray);
        }
    }
}
