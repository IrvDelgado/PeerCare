package com.vogella.android.retrofitgithub.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vogella.android.retrofitgithub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TareasFragment extends Fragment  {

    private BottomNavigationView mBottomNavigation;

    public TareasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        showSelectedFragment(new tareapendiente());

        return inflater.inflate(R.layout.fragment_tareas, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mBottomNavigation = (BottomNavigationView) view.findViewById(R.id.bottomNavigation2);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.tareapendiente){
                    showSelectedFragment(new tareapendiente());
                }
                else if(item.getItemId()== R.id.tareaaceptada )
                {
                    showSelectedFragment(new tareasaceptadas());
                }
                else if(item.getItemId()== R.id.creartarea){
                    showSelectedFragment(new creartarea());
                }

                return true;
            }
        });

    }

    private void showSelectedFragment(Fragment fragment){
        getFragmentManager().beginTransaction().replace(R.id.container2,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
