package com.example.nihal.maindriver;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    public static ListView mDrawerList,order_list,menu_list;
    private ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout login_layout,order_list_layout,signup_layout,empty_login;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private MyAdapter adapter;
    public static MyAdapter2 adapter2,menu_adapter;
    private View inflatedView;
    private TextView text,login_option,loginPopup;
    private EditText Sign_username,Sign_name,Sign_pass,Sign_phone,Sign_cpass,
            Login_email,Login_pass;
    private View myview;
    private ImageView profile;
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static ImageView floatin,floatout;

    String SENDER_ID = "903857909255";

    private Button Signup,Gosignup;
    ImageButton Login;

    public static boolean Selected=true,logged_in=true;


    public static ActionBar actionBar;
    String regid;
    public  static  String user_name="",user_pass="",user_id="";
    public  static int total_order,order_count=0,pos,selected_position=0;
    public static String orders[][];
    GoogleCloudMessaging gcm;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_layout= (RelativeLayout) findViewById(R.id.login_layout);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        adapter=new MyAdapter(this);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        Button login = (Button) findViewById(R.id.login_button);
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectItem(1);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        //Selected=true;
        //setTitle(mPlanetTitles[position]);
        switch (position) {
            case 0:

                mDrawerLayout.closeDrawers();
                break;
            case 1:
                if(logged_in==true){
                    HomeFragment homeFragment = new HomeFragment();

                    Bundle args = new Bundle();
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //ExampleFragments fragment = new ExampleFragments();
                    fragmentTransaction.replace(R.id.content_frame, homeFragment, "Item_Fragment");
                    fragmentTransaction.commit();
                    login_layout.setVisibility(View.INVISIBLE);

                    Selected = false;
                    selected_position = position;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Need To Login",
                            Toast.LENGTH_SHORT).show();
                }
                mDrawerLayout.closeDrawers();
                break;
            case 2:
                if(logged_in==true) {
                    FoodMenu fragment = new FoodMenu();

                    Bundle args = new Bundle();

                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //ExampleFragments fragment = new ExampleFragments();
                    fragmentTransaction.replace(R.id.content_frame, fragment, "Item_Fragment");
                    fragmentTransaction.commit();
                    login_layout.setVisibility(View.INVISIBLE);

                    Selected = false;
                    selected_position = position;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Need To Login",
                            Toast.LENGTH_SHORT).show();
                }
                mDrawerLayout.closeDrawers();
                break;
            case 3:
                if(logged_in==true){
                    TaxiFragment taxiFragment = new TaxiFragment();
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //ExampleFragments fragment = new ExampleFragments();
                    fragmentTransaction.replace(R.id.content_frame, taxiFragment, "Item_Fragment");
                    fragmentTransaction.commit();
                    login_layout.setVisibility(View.INVISIBLE);

                }
                mDrawerLayout.closeDrawers();
                break;
            case 4:
                if(logged_in==true){
                    TrainStatusFragment trainStatusFragment = new TrainStatusFragment();
                    android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    //ExampleFragments fragment = new ExampleFragments();
                    fragmentTransaction.replace(R.id.content_frame, trainStatusFragment, "Item_Fragment");
                    fragmentTransaction.commit();
                    login_layout.setVisibility(View.INVISIBLE);
                }
                mDrawerLayout.closeDrawers();
                break;
        }
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
    class MyAdapter extends BaseAdapter {

        public View myview;
        private Context context;
        String[] mPlanetTitles;
        int []images={R.drawable.myorder1,R.drawable.food1,R.drawable.icon_taxi,R.drawable.aboutus,R.drawable.logout1};

        public MyAdapter(Context context){
            this.context=context;
            mPlanetTitles=context.getResources().getStringArray(R.array.planets_array);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return mPlanetTitles[position-1];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row=null;
            if (convertView==null){
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(position==0) {
                    row = inflater.inflate(R.layout.full_layout, parent, false);
                    myview = inflater.inflate(R.layout.full_layout, parent, false);
                }

                else{
                    row = inflater.inflate(R.layout.custom_row, parent, false);
                }
                // Log.d("Hey-----------------------------------------------------------","Working------------------");

            }
            else{
                row=convertView;

            }
            if(position!=0 && position<6) {
                TextView title_text = (TextView) row.findViewById(R.id.textView1);
                ImageView title_image = (ImageView) row.findViewById(R.id.imageView1);
                //  Log.d("Postion=========================================================",": "+position);
                title_text.setText(mPlanetTitles[position-1]);
                title_image.setImageResource(images[position-1]);
            }


            return row;
        }
    }



    class MyAdapter2 extends BaseAdapter{

        private Context context;
        String[] mPlanetTitles;
        int i=0;
        int []images={R.drawable.myorder1,R.drawable.food1,R.drawable.detail1,R.drawable.logout1,R.drawable.aboutus};

        public MyAdapter2(Context context){
            this.context=context;
            mPlanetTitles=context.getResources().getStringArray(R.array.orders);
        }

        @Override
        public int getCount() {
            return orders.length;
        }

        @Override
        public Object getItem(int position) {
            return orders[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row=null;
            if (convertView==null){
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


                row = inflater.inflate(R.layout.show_order, parent, false);

                // Log.d("Hey-----------------------------------------------------------","Working------------------");

            }
            else{
                row=convertView;

            }

            TextView order_id = (TextView) row.findViewById(R.id.order_id_title);
            TextView order_user = (TextView) row.findViewById(R.id.order_user);
            TextView order_item = (TextView) row.findViewById(R.id.item_name);
            order_id.setText("Order ID: " + orders[position][0]);
            order_user.setText("customer: " + orders[position][1]);
            order_item.setText("Item: " + orders[position][2]);
            // Log.d("values: ->>>>>>>>>>>>"," "+orders[position][0]);



            return row;
        }
    }
}
