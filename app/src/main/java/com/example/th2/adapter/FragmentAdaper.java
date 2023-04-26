package com.example.th2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.th2.fragment.FragmentCat;
import com.example.th2.fragment.FragmentCat2;

public class FragmentAdaper extends FragmentPagerAdapter {
    private int pageNumber;
    public FragmentAdaper(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentCat();
            case 1: return new FragmentCat2();
            default: return new FragmentCat();
        }
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Cat 1";
            case 1: return "Cat 2";
        }
        return null;
    }
}
