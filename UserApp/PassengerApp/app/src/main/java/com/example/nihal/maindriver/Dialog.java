package com.example.nihal.maindriver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Dialog extends Activity {
    String driverNames[] = {"Ramesh", "Raghav", "Meghdeep", "Manju"};
    String taxiNumbers[] = {"KA=07", "KA-04", "KA-09", "KA-06"};
    String driverNumbers[] = {"12345", "6789", "984433", "84366"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Intent intent = getIntent();
        int no = intent.getIntExtra("Number", 0);
        TextView carTypeText = (TextView)findViewById(R.id.TaxiType);
        carTypeText.setText("Taxi Type:" + intent.getStringExtra("CarType"));
        TextView driverNameText = (TextView)findViewById(R.id.DriverName);
        driverNameText.setText(driverNames[no]);
        TextView taxiNumberText = (TextView)findViewById(R.id.TaxiNo);
        taxiNumberText.setText(taxiNumbers[no]);
        TextView driverNumberText = (TextView)findViewById(R.id.DriverNo);
        driverNumberText.setText(driverNumbers[no]);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
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
}
