package com.example.nitya.couponduniatask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.nitya.couponduniatask.model.Categories;
import com.example.nitya.couponduniatask.model.Restaurant;
import java.util.List;

/**
 * Created by Nitya on 24-May-15.
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private Context context;

    private List<Restaurant> restaurantList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public RestaurantAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.restaurantList = objects;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.restauant_item,parent,false);

        Restaurant restaurant = restaurantList.get(position);
        TextView name = (TextView) view.findViewById(R.id.restaurantName);
        TextView offers = (TextView) view.findViewById(R.id.offers);
        TextView cusine1 = (TextView) view.findViewById(R.id.cusine1);
        TextView cusine2 = (TextView) view.findViewById(R.id.cusine2);
        TextView cusine3 = (TextView) view.findViewById(R.id.cusine3);
        TextView location = (TextView) view.findViewById(R.id.area);
        TextView distance = (TextView) view.findViewById(R.id.distance);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = (NetworkImageView) view
                .findViewById(R.id.iconImage);
        NetworkImageView temp = new NetworkImageView(context);

        name.setText(restaurant.getBrandName());
        if(restaurant.getNumCoupons() == "1")
            offers.setText(restaurant.getNumCoupons()+ " Offer");
        else
            offers.setText(restaurant.getNumCoupons()+ " Offers");
        Categories categories[] = restaurant.getCategories();
        cusine1.setText(categories[0].getName());
        cusine2.setText(categories[1].getName());
        try {
            cusine3.setText(categories[2].getName());
        }catch (Exception e)
        {
            cusine3.setText("");
        }


        thumbNail.setImageUrl(restaurant.getLogoURL(),imageLoader);



        distance.setText(restaurant.getDistance());
        location.setText(restaurant.getNeighbourhoodName());

        return view;
    }

}
