package com.example.nihal.foodmenu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ShopsActivity extends ActionBarActivity {

    ListView shopView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops);
        String[] stringArray = {"Shop 1", "Shop 2", "Shop 3", "Shop 4", "Shop 5", "Shop 6", "Shop 7"};
        buildShopListView(stringArray);

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
                callMenu.putExtra("Shop", shopArray[position]);
                startActivity(callMenu);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shops, menu);
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
