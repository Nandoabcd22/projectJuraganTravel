package com.nando.juragantravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.nando.juragantravel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new BerandaFragment());

        binding.navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.etBeranda){
                replaceFragment(new BerandaFragment());
            } else if (item.getItemId() == R.id.etPaket) {
                replaceFragment(new PaketIbadahFragment());
            } else if (item.getItemId() == R.id.etSholat) {
                replaceFragment(new SholatFragment());
            } else if (item.getItemId() == R.id.etPemesanan){
                replaceFragment(new PemesananFragment());
            } else if (item.getItemId() == R.id.etAkun){
                replaceFragment(new ProfilFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_navbar,fragment);
        fragmentTransaction.commit();
    }
}