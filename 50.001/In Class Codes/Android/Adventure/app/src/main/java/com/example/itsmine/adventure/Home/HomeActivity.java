package com.example.itsmine.adventure.Home;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.itsmine.adventure.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    static final String BASE_URL = "https://arrivelah.herokuapp.com/?id=";
    static final String BUSSTOP1 = "14239"; // KEPPEL
    static final String BUSSTOP2 = "14381"; // BLK 109

    public static final String NAME_KEY = "Name_Key";



    TextView textview_time123;
    TextView textview_time143;
    TextView textview_time124;
    TextView textview_time123_2;
    TextView textview_time143_2;
    TextView textview_time124_2;
    TextView textView_welcome;
    Button button_refresh;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.mainsharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // set up references
        textView_welcome = (TextView) findViewById(R.id.home_textview_welcome);
        button_refresh = (Button) findViewById(R.id.home_button_refresh);
        textview_time123 = (TextView) findViewById(R.id.home_textview_time123);
        textview_time124 = (TextView) findViewById(R.id.home_textview_time124);
        textview_time143 = (TextView) findViewById(R.id.home_textview_time143);
        textview_time123_2 = (TextView) findViewById(R.id.home_textview_time123_2);
        textview_time124_2 = (TextView) findViewById(R.id.home_textview_time124_2);
        textview_time143_2 = (TextView) findViewById(R.id.home_textview_time143_2);


        // using sharedprefs to retrieve name of user
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String name_text = mPreferences.getString(NAME_KEY, "");

        textView_welcome.setText("Welcome, " + name_text);

        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });


    }

    public void refresh() {
        if (Utils.isNetworkAvailable(HomeActivity.this)) {
            Log.i("logcat","b4 gettimings initial");
            Gettimings gettimings = new Gettimings();
            Log.i("logcat", "at refresh");
            gettimings.execute();
        } else {
            Toast.makeText(HomeActivity.this, "No Network", Toast.LENGTH_LONG).show();
        }
    }

    private URL buildURL(String Busstop) {

        // 14239 - keppel towards outram
        // 14381 - blk 109
        //https://arrivelah.herokuapp.com/?id=14239
        URL url = null;
        String full_url = BASE_URL + Busstop;

        try {
            url = new URL(full_url);
            Log.i("logcat", "at buildURL " + full_url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("logcat", "finishing at buildURL " + url.toString());
        return url;

    }

    class Gettimings extends AsyncTask<Void, String, int[]> {

        @Override
        protected int[] doInBackground(Void... voids) {

            publishProgress("Loading the bus timings");
            String Jsonstring1 = Utils.getJson(buildURL(BUSSTOP1));
            String Jsonstring2 = Utils.getJson(buildURL(BUSSTOP2));
            Log.i("logcat", "at doInBackground");

            // initialise things
            int time124 = 0;
            int time143 = 0;
            int time123 = 0;
            int time123_2 = 0;
            int time124_2 = 0;
            int time143_2 = 0;
            JSONObject service124 = null;
            JSONObject service143 = null;
            JSONObject service123 = null;
            boolean occupied = false;


            try {
                // get the bus 124/143 timings
                JSONObject jsonObject1 = new JSONObject(Jsonstring1);
                JSONArray services = jsonObject1.getJSONArray("services");
                for (int i = 0; i < services.length(); i++){
                    if (services.getJSONObject(i).getString("no").equals("124")){
                        service124 = services.getJSONObject(i);
                    }
                    else if (services.getJSONObject(i).getString("no").equals("143")){
                        service143 = services.getJSONObject(i);
                    }
                }

                Log.i("logcat", "getting 123 service");
                // get bus 123 timing
                JSONObject jsonObject2 = new JSONObject(Jsonstring2);
                //get 123 bus timing (it is the first in index)
                JSONArray services2 = jsonObject2.getJSONArray("services");
                for (int j=0; j<services2.length(); j++){
                    if (services2.getJSONObject(j).getString("no").equals("123") && !occupied){
                        service123 = services2.getJSONObject(j);
                        occupied = true;
                    }
                }

                Log.i("logcat", "getting 123 timings");
                time123 = service123.getJSONObject("next").getInt("duration_ms"); // in ms
                time123 = time123/60/1000; // in min
                time123_2 = service123.getJSONObject("subsequent").getInt("duration_ms");
                time123_2 = time123_2/60/1000;

                Log.i("logcat", "getting 124 timings");
                time124 = service124.getJSONObject("next").getInt("duration_ms"); // in ms
                time124 = time124/60/1000; // in min
                time124_2 = service124.getJSONObject("subsequent").getInt("duration_ms");
                time124_2 = time124_2/60/1000;

                Log.i("logcat", "getting 143 timings");
                time143 = service143.getJSONObject("next").getInt("duration_ms"); // in ms
                time143 = time143/60/1000; // in min
                time143_2 = service143.getJSONObject("subsequent").getInt("duration_ms");
                time143_2 = time143_2/60/1000;

            }catch (JSONException e){
                e.printStackTrace();
                Toast.makeText(HomeActivity.this, "JSON file is faulty", Toast.LENGTH_LONG).show();
            }

            int[] result = {time124, time124_2, time143, time143_2, time123, time123_2};
            return result;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String update = values[0];
            Toast.makeText(HomeActivity.this, update, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(int[] ints) {
            super.onPostExecute(ints);

                        // show the bus timings
            textview_time124.setText(String.valueOf(ints[0] + " mins"));
            textview_time143.setText(String.valueOf(ints[2] + " mins"));
            textview_time123.setText(String.valueOf(ints[4] + " mins"));

            textview_time124_2.setText(String.valueOf(ints[1] + " mins"));
            textview_time143_2.setText(String.valueOf(ints[3] + " mins"));
            textview_time123_2.setText(String.valueOf(ints[5] + " mins"));
        }
    }


}
