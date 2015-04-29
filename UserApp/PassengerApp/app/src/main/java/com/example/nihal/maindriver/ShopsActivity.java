package com.example.nihal.maindriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShopsActivity extends ActionBarActivity {

    ListView shopView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops);
        String[] stringArray = {"Shop 1", "Shop 2", "Shop 3", "Shop 4", "Shop 5", "Shop 6", "Shop 7"};
        Intent i = getIntent();
        String stationName = i.getStringExtra("StationId");
        makeRequest(stationName.trim());
        //buildShopListView(stringArray);
        //makeRequest("Jolarpettai");

    }

    private void buildShopListView(final String shopArray[]) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shopArray);
        shopView = (ListView) findViewById(R.id.shopList);
        shopView.setAdapter(arrayAdapter);
        shopView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ShopsActivity.this,
                        "Shop: " + shopArray[position] + " clicked",
                        Toast.LENGTH_SHORT).show();
                Intent callMenu = new Intent(getApplicationContext(), MenuActivity.class);
                //callMenu.putExtra("Shop", shopArray[position]);
                startActivity(callMenu);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_shops, menu);
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

    private void makeRequest(String stationName){

        new QueryShopList(stationName).execute(new ApiConnector());
    }
    private class QueryShopList extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        String stationName;
        QueryShopList(String stationName){
            this.stationName = stationName;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShopsActivity.this);
            pDialog.setMessage("Obtaining Shop List");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].getShopList(stationName);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            pDialog.dismiss();
            String[][] array = new String[5][5];
            for(int i=0;i<5;i++){
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    array[i][0]=jsonObject.getString("name");
                    array[i][1]=jsonObject.getString("vendor_id");
                    array[i][2]=jsonObject.getString("discription");

                }catch (JSONException j){

                }
            }
            String[] nameArray = new String[array.length];
            for(int i=0;i<nameArray.length;i++)
                nameArray[i] = array[i][2];

            buildShopListView(nameArray);

            Toast.makeText(ShopsActivity.this, "json"+ array, Toast.LENGTH_LONG).show();


        }
    }
}
