package com.example.nihal.maindriver;

/**
 * Created by nihal on 19/4/15.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by prashanth on 4/4/15.
 */


public class ApiConnector {

    public static Bitmap item_images[];
    public static Bitmap item_sample;
    public static String image_name[];
    public static JSONArray image_object;
    public static int image_count=0;

    public String sendStationList(String PNR)
    {
        // URL for getting all customers

        //192.168.0.4
        String jobject;
        String url = "http://meghdeep.pythonanywhere.com/data/station_list/"+PNR;

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpGet get=new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(get);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        //JSONObject jObject = null;
        String entityResponse = "No Response Received";

        if (httpEntity != null) {
            try {
                entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);


            }catch(IOException e){

            }


        }

        return entityResponse;


    }

    public JSONArray checkTrainStatus()
    {
        // URL for getting all customers


        String url = "http://meghdeep.pythonanywhere.com/data/status/6440371381/Jolarpettai/";

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost=new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;


    }
    public String placeOrder()
    {
        // URL for getting all customers

        //192.168.0.4
        String jobject;
        String url = "http://prashantha.zz.mu/insert_order.php?vendor_id=kanchugodu@gmail.com&order_id=00011&name=kp&phone=1111122222&item=pizza&address=zaza&price=30";

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpGet get=new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(get);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        //JSONObject jObject = null;
        String entityResponse = "No Response Received";

        if (httpEntity != null) {
            try {
                entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);


            }catch(IOException e){

            }


        }

        return entityResponse;


    }

    public JSONArray Insert_Orders(String arr[][],int position)
    {
        // URL for getting all customers


        String url = "http://192.168.0.4/insert_order.php?name="+arr[position][1]+"&order_id="+arr[position][0]+
                "&phone="+arr[position][0]+"&item="+arr[position][2]+"&address="+arr[position][0]+"&price="+arr[position][3];

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost=new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;


    }


    public JSONArray getShopList(String Station)
    {
        // URL for getting all customers
        Station = Station.replaceAll(" ", "_");

        String url = "http://prashantha.zz.mu/item_list.php?station="+Station;

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost=new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;


    }


    public JSONArray Get_item()
    {
        // URL for getting all customers

        String image="",image_url;
        String url = "http://192.168.0.4/get_item.php";

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost=new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);
                image_object = new JSONArray(entityResponse);
                //image= jsonArray.getJSONObject(0).getString("image");

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        item_images=new Bitmap[image_object.length()];
        return jsonArray;
    }


    public JSONArray Get_Cart(int choice)
    {
        // URL for getting all customers

        String php_file="vendor_cart_price.php";

        switch (choice){
            case 0:
                php_file="vendor_cart.php";
                break;
            case 1:
                php_file="vendor_cart_price.php";
                break;
            case 2:
                php_file="vendor_cart_item.php";
                break;
        }


        String url = "http://192.168.0.4/"+php_file;

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost=new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;


    }

    public JSONArray sendCurrentPosition(String latitude,String longitude, String id, String car)
    {
        // URL for getting all customers

        //192.168.0.4
        String url = "http://seproject.site11.com/seProject/ShortestDistance.php?longitude=77&latitude=12&id=3&taxi_type=2";

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpGet get=new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(get);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;


    }

    public Bitmap Get_Image(String image)
    {
        // URL for getting all customers
        image_count=0;
        String url = "http://192.168.0.4/get_item_image.php?name="+image;
        Bitmap icon = null;


        try {
            InputStream inputStream = new java.net.URL(url).openStream();
            icon = BitmapFactory.decodeStream(inputStream);
            item_images[image_count]=item_sample;
            image_count++;
            // Toast.makeText(getApplicationContext(), "username and password not matching",
            //       Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return icon;
    }

    public Boolean uploadImageToserver(List<NameValuePair> params) {

        // URL for getting all customers
        String url = "http://192.168.0.4/uploadimage.php";



        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();
            String entityResponse = EntityUtils.toString(httpEntity);

            Log.e("Entity Response  : ", entityResponse);
            return true;

        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Her


        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JSONArray Get_Vendor_Details(String username)
    {
        // URL for getting all customers

        //192.168.0.4
        String url = "http://192.168.0.4/vendor_details.php?user_id="+username;

        // Get HttpResponse Object from url.
        // Get HttpEntity from Http Response Object

        HttpEntity httpEntity = null;

        try
        {

            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            // HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost=new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();



        } catch (ClientProtocolException e) {

            // Signals error in http protocol
            e.printStackTrace();

            //Log Errors Here



        }

        catch (IOException e) {
            e.printStackTrace();
        }


        // Convert HttpEntity into JSON Array
        JSONArray jsonArray = null;

        if (httpEntity != null) {
            try {
                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response  : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;


    }

}
