package com.example.itsmine.cody;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomiseActFrag extends Fragment {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewpager;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_customise_act, container, false);


        mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        //Set up ViewPager with sections Adapter
        mViewpager = (ViewPager) view.findViewById(R.id.container);
        setupViewPager(mViewpager);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewpager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getChildFragmentManager());
        adapter.addFragment(new CustomiseFragment(), "Customise");
        adapter.addFragment(new FavouritesFragment(), "Favourites");
        viewPager.setAdapter(adapter);
    }
}
