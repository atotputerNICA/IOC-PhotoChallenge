package com.example.photochallenge;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by RajeshAatrayan on 09-07-2018.
 */

/**
 * SliderAdapter just like custom ArrayAdapter we use
 */
public class SliderAdapter extends PagerAdapter {
    /**
     * When you implement a PagerAdapter, you must override the following methods at minimum:
     */


    Context context;
    LayoutInflater layoutInflater;

    //constructor
    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.shutter,
            R.drawable.love,
            R.drawable.jigsaw
    };
    public String[] slide_headings = {
            "SHOOT", "FEEDBACK", "INSPIRE"

    };
    public String[] slide_description = {
            "Challenge yourself. Have fun. Stay motivated.\n" +
                    "Start off as a Newbie and work your way up to Expert status.",
            "Submit your photos to daily photo challenges and receive feedback from people like you who simply love taking photos.",
            "Receive awesome insights and improve your skills." + "Experiment with your photos and find out which of them are WOW."
    };


    /**
     * 1. instantiateItem(ViewGroup, int): This method should create the page for the given position passed to it as an argument. We inflate() our layout resource to create the hierarchy of view objects and then set resource for the ImageView in it. Finally, the inflated view is added to the container (which should be the ViewPager) and return it as well.
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_img);
        TextView slide_head = (TextView) view.findViewById(R.id.slide_head);
        TextView slide_desc = (TextView) view.findViewById(R.id.slide_desc);
        slideImageView.setImageResource(slide_images[position]);
        slide_head.setText(slide_headings[position]);
        slide_desc.setText(slide_description[position]);
        container.addView(view);
        return view;
    }

    /*
        2. destroyItem(ViewGroup, int, Object): Removes the page from the container for the given position. We simply removed object using removeView but could’ve also used removeViewAt() by passing it the position.
     */

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    /**
     * 3. getCount(): This method should return the number of views available, i.e., number of pages to be displayed/created in the ViewPager.
     *
     * @return
     */
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    /**
     * 4. isViewFromObject(View, Object): The object returned by instantiateItem() is a key/identifier. This method checks whether the View passed to it (representing the page) is associated with that key or not. It is required by a PagerAdapter  to function properly.
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }
}
