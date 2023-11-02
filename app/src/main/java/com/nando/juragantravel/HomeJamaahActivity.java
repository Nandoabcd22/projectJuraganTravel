package com.nando.juragantravel;

import static com.nando.juragantravel.R.id.nav_view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeJamaahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_jamaah);
        loadFragment(new dashboardFragment());

        BottomNavigationView navigation = findViewById(nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            if (item.getItemId() == R.id.home) {
                fragment = new dashboardFragment();
            } else if (item.getItemId() == R.id.search) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.cartId) {
                fragment = new SettingFragment();
            } else if (item.getItemId() == R.id.profile) {
                fragment = new ProfileFragment();
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        if (fragment == null) {
            return false;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_navbar, fragment);
        transaction.commit();

        return true;
    }
}