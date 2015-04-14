package com.example.nihal.userapp;

import android.app.Activity;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapTest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapTest extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GoogleMap mMap;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapTest.
     */
    // TODO: Rename and change types and number of parameters
    public static MapTest newInstance(String param1, String param2) {
        MapTest fragment = new MapTest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MapTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_test, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(12, 77)).title("Marker"));
        String [] cityList= {"Bangalore, Karnataka, India", "Chennai, Tamil Nadu, India"};
        putMarker("Bangalore, Karnataka, India");
        putMarker("Chennai, Tamil Nadu, India");
        putPolyLines(cityList);
    }

    public Address getLatitudeAndLongitudeFromGoogleMapForAddress(String searchedAddress, Address location){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try {

            address = coder.getFromLocationName(searchedAddress, 2);
            if (address == null) {
                Log.d("Map Error", "############Address not correct #########");
            }
            location = address.get(0);

            Log.d("Map Error", "Address Latitude : "+ location.getLatitude()+ "Address Longitude : "+ location.getLongitude());
            return location;

        }catch(Exception e){
            Log.d("Map Error", "MY_ERROR : ############Address Not Found");
            return location;
        }
    }

    private void putMarker(String cityName){
        Address location = new Address(new Locale("English"));
        try{
            location = getLatitudeAndLongitudeFromGoogleMapForAddress(cityName, location);
            mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title(cityName));

        }catch(Exception ex) {
            Toast.makeText(getApplicationContext(), (String) "Could not get Latitude and Longitude", Toast.LENGTH_SHORT).show();
        }
    }

    private void putPolyLines(String[] cityList){
        Address location1 = new Address(new Locale("English"));
        Address location2 = new Address(new Locale("English"));
        location1 = getLatitudeAndLongitudeFromGoogleMapForAddress(cityList[0], location1);
        location2 = getLatitudeAndLongitudeFromGoogleMapForAddress(cityList[1], location2);
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(location1.getLatitude()
                        , location1.getLongitude())
                        , new LatLng(location2.getLatitude()
                        , location2.getLongitude()))
                .width(5).color(Color.RED));
    }




}
