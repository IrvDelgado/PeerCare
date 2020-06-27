package com.vogella.android.retrofitgithub.common;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vogella.android.retrofitgithub.R;

public class BackToolbar {

    public BackToolbar(AppCompatActivity activity, AppCompatActivity destinationActivity) {
        Toolbar myToolbar = activity.findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Atrás");
        activity.setSupportActionBar(myToolbar);

        myToolbar.setNavigationIcon(ContextCompat.getDrawable(activity, R.drawable.ic_arrow_back_white_24dp));
        myToolbar.setNavigationOnClickListener(v -> activity.startActivity(new Intent(activity, destinationActivity.getClass())));
    }
}
