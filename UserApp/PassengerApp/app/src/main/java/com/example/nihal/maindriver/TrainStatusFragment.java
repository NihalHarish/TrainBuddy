package com.example.nihal.maindriver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TrainStatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ProgressDialog pDialog;


    TextView lastSeen;
    TextView delay;
    TextView status;
    TextView ETA;


    View layout_view;
    // TODO: Rename and change types and number of parameters
    public static TrainStatusFragment newInstance(String param1, String param2) {
        TrainStatusFragment fragment = new TrainStatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TrainStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout_view = inflater.inflate(R.layout.fragment_train_status, container, false);
        Button statusButton = (Button)layout_view.findViewById(R.id.status_button);
        statusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
        Button alarmButton = (Button)layout_view.findViewById(R.id.alarm_button);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alarmIntent = new Intent(getActivity().getApplicationContext(), AlarmActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getApplicationContext(),
                        12345, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                long thirtySecondsFromNow = System.currentTimeMillis() + 1zzz6ty0 * 1000;
                AlarmManager alarmManager = (AlarmManager)getActivity().getApplicationContext().getSystemService(getActivity().ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, thirtySecondsFromNow,
                        2*6*10000, pendingIntent);
                Toast.makeText(getActivity().getApplication(), "Alarm Set", Toast.LENGTH_LONG).show();
            }
        });
        return layout_view;
    }

    void makeRequest(){
        new QueryStationServer().execute(new ApiConnector());
    }

    private class QueryStationServer extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        String PNR;
        QueryStationServer(){
            this.PNR = PNR;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Fetching Train Status");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].checkTrainStatus();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            pDialog.dismiss();
            Intent intent = new Intent(getActivity().getApplicationContext(), TrainStatusActivity.class);
            startActivity(intent);

        }

    }
}
