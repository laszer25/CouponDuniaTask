package com.example.nitya.couponduniatask.parser;

import com.example.nitya.couponduniatask.model.Categories;
import com.example.nitya.couponduniatask.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nitya on 23-May-15.
 */
public class RestaurantJsonParser {

    public static List<Restaurant> parseFeed(String content) {
        try {
            JSONArray jsonMainArray = new JSONArray(content);
            JSONObject jsonMainObject = jsonMainArray.getJSONObject(0);
            JSONObject jsonObject1 = jsonMainObject.getJSONObject("data");


            List<Restaurant> restaurantList = new ArrayList<Restaurant>();
            int lengthList = jsonObject1.length();

            Iterator<String> iterator = jsonObject1.keys();
            JSONObject jsonObject;
            String key;
            Restaurant restaurant;
            JSONObject jsonSubObject;
            JSONArray jsonSubArray;
            while(iterator.hasNext()){
             key = iterator.next();
            jsonObject = jsonObject1.getJSONObject(key);



                restaurant = new Restaurant();
                restaurant.setResponseNumber(Integer.parseInt(key));
                restaurant.setSubFranchiseID(jsonObject.getString("SubFranchiseID"));
                restaurant.setOutletID(jsonObject.getString("OutletID"));
                restaurant.setBrandID(jsonObject.getString("BrandID"));
                restaurant.setAddress(jsonObject.getString("Address"));
                restaurant.setNeighbourhoodID(jsonObject.getString("NeighbourhoodID"));
                restaurant.setCityID(jsonObject.getString("CityID"));
                restaurant.setEmail(jsonObject.getString("Email"));
                restaurant.setTimings(jsonObject.getString("Timings"));
                restaurant.setCityRank(jsonObject.getString("CityRank"));
                restaurant.setLatitude(jsonObject.getString("Latitude"));
                restaurant.setLongitude(jsonObject.getString("Longitude"));
                restaurant.setStreetname(jsonObject.getString("Streetname"));
                restaurant.setBrandName(jsonObject.getString("BrandName"));
                restaurant.setOutletURL(jsonObject.getString("OutletURL"));
                restaurant.setNumCoupons(jsonObject.getString("NumCoupons"));
                restaurant.setNeighbourhoodName(jsonObject.getString("NeighbourhoodName"));
                restaurant.setPhoneNumber(jsonObject.getString("PhoneNumber"));
                restaurant.setCityName(jsonObject.getString("CityName"));
                restaurant.setDistance(jsonObject.getString("Distance"));
                jsonSubArray = jsonObject.getJSONArray("Categories");
                Categories categories[] = new Categories[jsonSubArray.length()];
                for (int j = 0; j < jsonSubArray.length(); j++) {
                    jsonSubObject = jsonSubArray.getJSONObject(j);
                    categories[j] = new Categories();
                    categories[j].setOfflineCategoryID(jsonSubObject.getString("OfflineCategoryID"));
                    categories[j].setName(jsonSubObject.getString("Name"));
                    categories[j].setParentCategoryID(jsonSubObject.getString("ParentCategoryID"));
                    categories[j].setCategoryType(jsonSubObject.getString("CategoryType"));

                }
                restaurant.setCategories(categories);
                restaurant.setLogoURL(jsonObject.getString("LogoURL"));
                restaurant.setCoverURL(jsonObject.getString("CoverURL"));
                restaurantList.add(restaurant);

            }


            return restaurantList;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
