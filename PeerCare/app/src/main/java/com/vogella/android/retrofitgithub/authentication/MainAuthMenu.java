package com.vogella.android.retrofitgithub.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.vogella.android.retrofitgithub.R;
import com.vogella.android.retrofitgithub.authentication.signin.Login;
import com.vogella.android.retrofitgithub.authentication.signup.SignUp;

public class MainAuthMenu extends AppCompatActivity {
    private Button btnLogin, btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auth_menu);

        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_sign_up);

        buttonsListeners();
    }

    public void buttonsListeners() {
        btnSignUp.setOnClickListener(
                v -> startActivity(new Intent(MainAuthMenu.this, SignUp.class))
        );
        btnLogin.setOnClickListener(v -> startActivity(new Intent(MainAuthMenu.this, Login.class)));
    }

}
