package com.example.photochallenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ViewPager in Android is a class that allows the user to flip left and right through pages of data. This class provides the functionality to flip pages in app.
 * <p>
 * It is a widget found in the support library. To use it you‘ll have to put the element inside your XML layout file that’ll contain multiple child views. It is similar to what a ListAdapter does for a ListView.
 */

public class OnBoardingActivity extends Activity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button nextButton;
    private Button backButton;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        nextButton = (Button) findViewById(R.id.button2);
        backButton = (Button) findViewById(R.id.button1);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        backButton.setVisibility(View.INVISIBLE);
        //it works when ever we try to change the page
        mSlideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //when we go to the new page
                addDotsIndicator(position);
                mCurrentPage = position;
                if (mCurrentPage == 0) {
                    backButton.setEnabled(false);
                    nextButton.setEnabled(true);
                    backButton.setVisibility(View.INVISIBLE);
                    nextButton.setText("NEXT");
                } else if (mCurrentPage == mDots.length - 1) {
                    backButton.setEnabled(true);
                    nextButton.setEnabled(true);
                    backButton.setVisibility(View.VISIBLE);
                    backButton.setText("BACK");
                    nextButton.setText("FINISH");
                } else {
                    backButton.setEnabled(true);
                    nextButton.setEnabled(true);
                    backButton.setVisibility(View.VISIBLE);
                    backButton.setText("BACK");
                    nextButton.setText("NEXT");

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //because page always starts at 0
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < 3; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));//it converts the HTML code into the beautiful dots
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            mDotLayout.addView(mDots[i]);

        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}
