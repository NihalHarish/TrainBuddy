package com.example.nihal.maindriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MenuActivity extends ActionBarActivity {

    ListView menuList;
    //TextView totalText;
    private int total;
    Context context;
    static TextView costText;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        String[] foodList = {"Pizza", "Burger", "French Fries", "Coke", "Pumpkin", "Idli"};
        createMenu(foodList);
        costText = (TextView)findViewById(R.id.totalTextView);
        costText.setText("0");
        costText.setVisibility(View.INVISIBLE);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
    }



    private void createMenu(String[] foodList){
        menuList = (ListView) findViewById(R.id.menuListView);
        Model[] modelItems = new Model[foodList.length];
        for(int i = 0; i< foodList.length;i++)
            modelItems[i] = new Model(foodList[i], 0, 10);

        CustomAdapter adapter = new CustomAdapter(this, modelItems);
        menuList.setAdapter(adapter);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
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

    public void makeRequest(){
        new QueryStationServer().execute(new ApiConnector());
    }

    private class QueryStationServer extends AsyncTask<ApiConnector,Long,String>
    {

        QueryStationServer(){

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getApplication());
            pDialog.setMessage("Sending Order");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
//            pDialog.show();
        }


        @Override
        protected String doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].placeOrder();
        }

        @Override
        protected void onPostExecute(String jsonArray) {
            pDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Order Placed", Toast.LENGTH_LONG).show();
            /*String stringArray ;

            stringArray=jsonArray.toString();
            buildStationListView(processJSON(jsonArray));*/

        }
    }
}
