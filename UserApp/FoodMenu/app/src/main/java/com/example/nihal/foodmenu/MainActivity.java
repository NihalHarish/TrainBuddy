package com.example.nihal.foodmenu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {


    private ListView lv;
    private Model[] modelItems;
    private ProgressDialog pDialog;

    private ListView stationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stations);
/*      lv = (ListView) findViewById(R.id.listView1);
        modelItems = new Model[5];
        modelItems[0] = new Model("pizza", 0);
        modelItems[1] = new Model("burger", 1);
        modelItems[2] = new Model("olives", 1);
        modelItems[3] = new Model("orange", 0);
        modelItems[4] = new Model("tomato", 1);
        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        lv.setAdapter(adapter);*/


        /*REMOVE THIS AND INSERT DYNAMIC LIST GENERATOR*/

        String[] stationArray = {"Chennai Central ", "Arakkonam ", "Katpadi Jn ", "Jolarpettai ", "Bangarapet ", "Bangalore East ", "Bangalore Cant ", "Bangalore Cy Jn "};
        makeRequest();
        //buildStationListView(stationArray);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void buildStationListView(final String stationArray[]){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stationArray);
        stationListView = (ListView) findViewById(R.id.stationList);
        stationListView.setAdapter(arrayAdapter);

        stationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "City: " + stationArray[position] + " clicked",
                        Toast.LENGTH_SHORT).show();
                Intent callShops = new Intent(getApplicationContext(), ShopsActivity.class);
                //callShops.putExtra("City", stationArray[position]);
                startActivity(callShops); //Error
            }
        });
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
    private String[] processJSON(String input){
        String[] array = input.split(",");
        for(int i=0;i<array.length;i++) {

            if(i==0)
                array[i]=array[i].substring(2,array[i].length()-1);
            else if(i==array.length-1)
                array[i]=array[i].substring(2,array[i].length()-3);

            else
            {
                array[i]=array[i].substring(2,array[i].length()-1);
            }
        }

        return array;
    }
    private void makeRequest(){
        String PNR = "4519282568";
        new QueryServer(PNR).execute(new ApiConnector());
    }


    private class QueryServer extends AsyncTask<ApiConnector,Long,String>
    {
        String PNR;
        QueryServer(String PNR){
            this.PNR = PNR;
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
        protected String doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].sendStationList(PNR);
        }

        @Override
        protected void onPostExecute(String jsonArray) {
            pDialog.dismiss();

           String stringArray ;

           stringArray=jsonArray.toString();

            Toast.makeText(MainActivity.this, "json"+ stringArray, Toast.LENGTH_LONG).show();
            buildStationListView(processJSON(jsonArray));

        }
    }

}
