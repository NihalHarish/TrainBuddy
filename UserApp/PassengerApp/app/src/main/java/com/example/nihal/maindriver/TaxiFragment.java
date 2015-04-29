package com.example.nihal.maindriver;

/**
 * Created by nihal on 21/4/15.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;





import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;


public class TaxiFragment extends Fragment {

    View layout_view;
    ProgressDialog pDialog;
    int car;
    String latitude;
    String longitude;
    String id;
    Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout_view=inflater.inflate(R.layout.taxi_main,container,false);
        button = (Button)layout_view.findViewById(R.id.button);
        final RadioGroup radioGroup = (RadioGroup)layout_view.findViewById(R.id.taxiGroup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch(id) {
                    case R.id.mini:
                            car = 1;
                        break;
                    case R.id.sedan:
                            car = 2;
                        break;
                    case R.id.prime:
                            car=3;
                        break;
                }
                makeRequest(car);
            }
        });

        return layout_view;
    }


    private void makeRequest(int car){

        GPSTracker gps = new GPSTracker(getActivity().getApplicationContext());
        if(gps.canGetLocation()){
            Log.d("inside", "HERE!");
            latitude = Double.toString(gps.getLatitude());
            longitude = Double.toString(gps.getLongitude());
            id = "9";
            processJSONArray();
            gps.stopUsingGPS();
            Toast.makeText(getActivity().getApplicationContext(), "Latitude" + latitude + "Longitude" + longitude, Toast.LENGTH_LONG).show();
        }


    }

    private void processJSONArray(/*JSONArray jsonArray*/){
        String[][] array = new String[5][5];
       /* for(int i=0;i<5;i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                array[i][0]=jsonObject.getString("driver_name");
                array[i][1]=jsonObject.getString("taxi_name");
                array[i][2]=jsonObject.getString("taxi_no");
                array[i][3]=jsonObject.getString("taxi_type");
                array[i][4]=jsonObject.getString("phone");
                array[i][5]=jsonObject.getString("rate");
            }catch (JSONException j){

            }
          }*/
          Intent intent = new Intent(getActivity().getApplicationContext(), Dialog.class);
          //intent.putExtra("latitude", ());
          String carTypes[] = {"Auto", "Mini", "Sedan", "Prime"};
          intent.putExtra("CarType", carTypes[car]);
          intent.putExtra("Number", car);
          startActivity(intent);

    }

    private class QueryServer extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        String Latitude;
        String Longitude;
        String ID;
        String car_type;
        public QueryServer(String lat, String lon, String id, int car){
            Latitude = lat;
            Longitude = lon;
            ID = id;
            car_type = car+"";
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(getActivity().getApplicationContext());
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }


        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].sendCurrentPosition(Latitude, Longitude, ID, car_type);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
          //  pDialog.dismiss();
            processJSONArray(/*jsonArray*/);
        }
    }
}
