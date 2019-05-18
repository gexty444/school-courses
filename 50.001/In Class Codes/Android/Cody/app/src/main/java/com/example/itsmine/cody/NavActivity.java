package com.example.itsmine.cody;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class NavActivity extends AppCompatActivity {

    private FavouritesFragment favfrag;
    private CustomiseFragment cusfrag;
    private CustomiseActFrag actfrag;
    private BlankFragment blankfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        favfrag = new FavouritesFragment();
        cusfrag = new CustomiseFragment();
        actfrag = new CustomiseActFrag();
        blankfrag = new BlankFragment();


        BottomNavigationView bottomnav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.nav_fav:
                        setFragment(actfrag);
                        return true;

                    case R.id.nav_home:
                        setFragment(blankfrag);
                        return true;

                    default:
                        return false;
                }
            }
        });

    }

    private void setFragment(Fragment frag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,frag).commit();
    }


}
