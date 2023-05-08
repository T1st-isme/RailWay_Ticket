package com.example.railwayticket.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.railwayticket.fragment.OnBoarding1Fragment;
import com.example.railwayticket.fragment.OnBoarding2Fragment;
import com.example.railwayticket.fragment.OnBoarding3Fragment;
import com.example.railwayticket.fragment.OnBoarding4Fragment;
import com.example.railwayticket.fragment.Onboarding5Fragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OnBoarding1Fragment();
            case 1:
                return new OnBoarding2Fragment();
            case 2:
                return new OnBoarding3Fragment();
            case 3:
                return new OnBoarding4Fragment();
            case 4:
                return new Onboarding5Fragment();
            default:
                return new OnBoarding1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
