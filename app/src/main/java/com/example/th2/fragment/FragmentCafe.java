package com.example.th2.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.th2.MainActivity;
import com.example.th2.R;
import com.example.th2.adapter.FragmentAdaper;
import com.google.android.material.tabs.TabLayout;

public class FragmentCafe extends Fragment{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cafe,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        FragmentManager manager = getChildFragmentManager();
        FragmentAdaper adapter = new FragmentAdaper(manager,2);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.cafe_24);
        tabLayout.setBackgroundColor(Color.BLUE);
        tabLayout.getTabAt(1).setIcon(R.drawable.cafe_24);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0: tabLayout.setTabTextColors(Color.BLACK,getResources().getColor(R.color.yellow));
                        break;
                    case 1: tabLayout.setTabTextColors(Color.RED,getResources().getColor(R.color.teal_200));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
