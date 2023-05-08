package com.example.railwayticket.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.railwayticket.R;
import com.example.railwayticket.adapter.ViewPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class FirstActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private CircleIndicator circleIndicator;
    private RelativeLayout relativeLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpager.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initView ()
    {
        viewpager = findViewById(R.id.view_paper);
        circleIndicator = findViewById(R.id.circle_indicator);

    }
}