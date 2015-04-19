package com.example.nihal.foodmenu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


public class MenuActivity extends ActionBarActivity {

    ListView menuList;
    //TextView totalText;
    private int total;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        String[] foodList = {"Pizza", "Burger", "French Fries", "Coke", "Pumpkin"};
        createMenu(foodList);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

/*    public View updateTotal(View convertView, ViewGroup parent){
        View view = convertView;
        TextView totalText = (TextView) view.findViewById(R.id.totalTextView);
        CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox)view).isChecked()){
                    total +=
                    totalTextView
                }
            }
        });
        return view;
    }*/

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
