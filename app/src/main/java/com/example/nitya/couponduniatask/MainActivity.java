package com.example.nitya.couponduniatask;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nitya.couponduniatask.model.Restaurant;
import com.example.nitya.couponduniatask.parser.RestaurantJsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {
    TextView textView;
    ProgressBar progressBar;
    ListView listView;
    List<Restaurant> restaurantList;
    Location location;
    RestaurantAdapter restaurantAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restaurantList = new ArrayList<Restaurant>();
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);


        boolean connected = checkForNetwork();

        if (connected) {
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.isIndeterminate();
            LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            DataConnectionHandler handler = new DataConnectionHandler();
            handler.execute("http://staging.couponapitest.com/task_data.txt");
        } else {
            textView.setText("There is no network connection");
            textView.setTextSize(20);


        }


    }

    private boolean checkForNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    void showRestaruantList() {
        listView.setAdapter(restaurantAdapter);
    }


    private void DetectLocation() {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (null != listAddresses && listAddresses.size() > 0) {
                String locationName = listAddresses.get(0).getAddressLine(1);
                String[] locationArea = locationName.split(",");

                textView.setText(locationArea[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Restaurant> SortRestaurants(List<Restaurant> restaurantList) {
        Collections.sort(restaurantList, new RestaurantComparator());
        return restaurantList;
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

    class RestaurantComparator implements Comparator<Restaurant> {

        @Override
        public int compare(Restaurant lhs, Restaurant rhs) {


            Location lhsLocation = new Location("LHS");

            lhsLocation.setLongitude(Double.parseDouble(lhs.getLongitude()));
            lhsLocation.setLatitude(Double.parseDouble(lhs.getLatitude()));


            Location rhsLocation = new Location("RHS");

            rhsLocation.setLongitude(Double.parseDouble(rhs.getLongitude()));
            rhsLocation.setLatitude(Double.parseDouble(rhs.getLatitude()));


            float lhsDistance = location.distanceTo(lhsLocation);
            float rhsDistance = location.distanceTo(rhsLocation);
            if (lhsDistance < 1000)
                lhs.setDistance(Integer.toString(Float.floatToIntBits(lhsDistance)) + " m");
            else {
                double distance = lhsDistance / 1000.0;
                DecimalFormat decimalFormat = new DecimalFormat("####.##");

                lhs.setDistance(decimalFormat.format(distance) + " km");
            }

            if (rhsDistance < 1000)
                rhs.setDistance(Integer.toString(Float.floatToIntBits(rhsDistance)) + " m");
            else {
                double distance = rhsDistance / 1000.00;
                DecimalFormat decimalFormat = new DecimalFormat("####.##");

                rhs.setDistance(decimalFormat.format(distance) + " km");
            }


            if (lhsDistance > rhsDistance)
                return 1;
            else
                return -1;

        }
    }


    class DataConnectionHandler extends AsyncTask<String, Void, List<Restaurant>> {
        String returnString = "";

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(List<Restaurant> s) {
            //super.onPostExecute(s);
            DetectLocation();

            List<Restaurant> sortedList = SortRestaurants(restaurantList);


            restaurantAdapter = new RestaurantAdapter(MainActivity.this, R.layout.restauant_item, restaurantList);



            progressBar.setVisibility(View.INVISIBLE);
            showRestaruantList();

        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected List<Restaurant> doInBackground(String... params) {

            String returnString = "";
            DefaultHttpClient httpClient = new DefaultHttpClient();
            String requestUrl = params[0];


            try {
                URI requestUri = new URI(requestUrl);
                HttpGet request = new HttpGet();
                request.setURI(requestUri);
                HttpResponse response = httpClient.execute(request);
                returnString = EntityUtils.toString(response.getEntity());
                restaurantList = RestaurantJsonParser.parseFeed("[" + returnString + "]");
            } catch (Exception e) {

            } finally {

                return restaurantList;
            }
        }
    }
}




