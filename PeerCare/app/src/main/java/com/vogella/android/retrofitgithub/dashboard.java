package com.vogella.android.retrofitgithub;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.vogella.android.retrofitgithub.fragments.HomeFragment;
import com.vogella.android.retrofitgithub.fragments.NotificacionFragment;
import com.vogella.android.retrofitgithub.fragments.TareasFragment;

public class dashboard extends AppCompatActivity {

    BottomNavigationView mBottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        showSelectedFragment(new HomeFragment());

        mBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.menu_home){
                    showSelectedFragment(new HomeFragment());
                }
                else if(item.getItemId()== R.id.menu_calendar )
                {
                    showSelectedFragment(new TareasFragment());
                }
                else if(item.getItemId()== R.id.menu_notificacion){
                    showSelectedFragment(new NotificacionFragment());
                }

                return true;
            }
        });
    }

    private void showSelectedFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
