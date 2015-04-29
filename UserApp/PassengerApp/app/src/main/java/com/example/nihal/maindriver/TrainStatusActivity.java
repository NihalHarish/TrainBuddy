package com.example.nihal.maindriver;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class TrainStatusActivity extends ActionBarActivity {
    TextView lastSeen;
    TextView delay;
    TextView status;
    TextView ETA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_status);
        lastSeen = (TextView)findViewById(R.id.lastseen);
        delay = (TextView)findViewById(R.id.delay);
        status = (TextView)findViewById(R.id.status);
        ETA = (TextView)findViewById(R.id.ETA);
        lastSeen.setText("Last Seen: N/A");
        delay.setText("Delay: No Delay");
        status.setText("Status: Not Started");
        ETA.setText("Expected Arrival: 8:20PM");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_train_status, menu);
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
