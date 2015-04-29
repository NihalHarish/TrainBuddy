package com.example.nihal.maindriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by nihal on 19/4/15.
 */
public class FoodMenu extends Fragment {

    public static int value=0;
    public static ListView menu_list;
    public View inflatedView;
    public static int total_order;
    public static String orders[][];
    TextView loginPopup;
    private ListView lv;
    private Model[] modelItems;
    private ProgressDialog pDialog;

    private ListView stationListView;
    TextView promptView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.stations,container,false);
        promptView = (TextView)view.findViewById(R.id.prompt_text);
        promptView.setVisibility(View.INVISIBLE);
        stationListView= (ListView) view.findViewById(R.id.stationList);
        makeRequest(HomeFragment.PNRValue);
        return view;

    }

    private void buildStationListView(final String stationArray[]){
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stationArray);


        stationListView.setAdapter(arrayAdapter);

        stationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        "City: " + stationArray[position] + " clicked",
                        Toast.LENGTH_SHORT).show();
                Intent callShops = new Intent(getActivity().getApplicationContext(), ShopsActivity.class);
                callShops.putExtra("StationId", stationArray[position]);
                startActivity(callShops); //Error
            }
        });
    }



    private String[] processJSON(String input){
        String[] array = input.split(",");
        for(int i=0;i<array.length;i++) {

            if(i==0)
                array[i]=array[i].substring(2,array[i].length()-1);
            else if(i==array.length-1)
                array[i]=array[i].substring(2,array[i].length() - 3);

            else
            {
                array[i]=array[i].substring(2,array[i].length()-1);
            }
        }

        return array;
    }
    private void makeRequest(long pnr){
        String PNR = pnr+"";
        if(PNR.length()<3){
            Toast.makeText(getActivity().getApplicationContext(), "Please Set a Valid PNR", Toast.LENGTH_LONG).show();
            AlertBox alertBox = new AlertBox();
            promptView.setText("Check Set PNR");
            promptView.setVisibility(View.VISIBLE);
        }
        else
            new QueryStationServer(PNR).execute(new ApiConnector());
    }


    private class QueryStationServer extends AsyncTask<ApiConnector,Long,String>
    {
        String PNR;
        QueryStationServer(String PNR){
            this.PNR = PNR;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Obtaining Station List");
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
            buildStationListView(processJSON(jsonArray));

        }
    }

}
