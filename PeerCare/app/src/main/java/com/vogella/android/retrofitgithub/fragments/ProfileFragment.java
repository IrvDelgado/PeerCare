package com.vogella.android.retrofitgithub.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vogella.android.retrofitgithub.R;
import com.vogella.android.retrofitgithub.authentication.MainAuthMenu;
import com.vogella.android.retrofitgithub.authentication.signin.Login;
import com.vogella.android.retrofitgithub.authentication.signup.SignUp;
import com.vogella.android.retrofitgithub.dashboard;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private Button btncerrarsesion;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btncerrarsesion= view.findViewById(R.id.cerrarsesion);
        buttonsListeners();
    }

    public void buttonsListeners() {
        btncerrarsesion.setOnClickListener(
                v -> startActivity(new Intent(getActivity(), Login.class))
        );

    }
    //startActivity(new Intent(Login.this, dashboard .class));
}
