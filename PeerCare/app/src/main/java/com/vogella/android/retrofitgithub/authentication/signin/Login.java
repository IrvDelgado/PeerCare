package com.vogella.android.retrofitgithub.authentication.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vogella.android.retrofitgithub.R;
import com.vogella.android.retrofitgithub.authentication.MainAuthMenu;
import com.vogella.android.retrofitgithub.authentication.UserControllerAPI;
import com.vogella.android.retrofitgithub.authentication.UserService;
import com.vogella.android.retrofitgithub.authentication.signup.SignUp;
import com.vogella.android.retrofitgithub.common.apiresponse.ApiResponse;
import com.vogella.android.retrofitgithub.common.BackToolbar;
import com.vogella.android.retrofitgithub.common.OwnValidator;
import com.vogella.android.retrofitgithub.common.SaveSharedPreferences;
import com.vogella.android.retrofitgithub.common.user.User;
import com.vogella.android.retrofitgithub.common.user.UserDeserializer;
import com.vogella.android.retrofitgithub.dashboard;
import com.vogella.android.retrofitgithub.menu.Menu;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class Login extends AppCompatActivity {

    private EditText inputEmailText, inputPasswordText;
    private ProgressBar progressBar;
    private Button btnLogin;
    private UserControllerAPI userControllerAPI;
    private UserService userService;
    private CompositeDisposable compositeDisposable;
    private OwnValidator ownValidator;
    private TextView txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dataInit();

        buttonsListeners();
    }

    public void dataInit() {
        compositeDisposable = new CompositeDisposable();
        inputEmailText = findViewById(R.id.email);
        inputPasswordText = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        btnLogin = findViewById(R.id.btn_login);
        txtSignUp= findViewById(R.id.title_text_view3);
        userService = new UserService();
        ownValidator = new OwnValidator();
        userControllerAPI = userService.connectWithLoginApi();
    }

    public void buttonsListeners() {
        btnLogin.setOnClickListener(v ->
        {
            //if (!areFieldsValid()) return;

            startActivity(new Intent(Login.this, dashboard.class));
            //progressBar.setVisibility(View.VISIBLE);

            /*final LoginPayloadRequest loginPayloadRequest = LoginPayloadRequest.builder()
                    .email(inputEmailText.getText().toString())
                    .password(inputPasswordText.getText().toString())
                    .build();
            */
            //sendRequest(loginPayloadRequest);
        });
        txtSignUp.setOnClickListener(
                v -> startActivity(new Intent(Login.this, SignUp.class))
        );
    }

    private boolean areFieldsValid() {
        boolean isEmailValid = ownValidator.validEmail(Login.this, inputEmailText);
        boolean isPasswordValid = ownValidator.validPassword(Login.this, inputPasswordText);

        return isPasswordValid && isEmailValid;
    }

    private void sendRequest(LoginPayloadRequest loginPayloadRequest) {
        compositeDisposable.add(userControllerAPI.login(loginPayloadRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getRepositoriesObserver()));
    }

    private DisposableSingleObserver<ApiResponse> getRepositoriesObserver() {
        return new DisposableSingleObserver<ApiResponse>() {
            @Override
            public void onSuccess(ApiResponse value) {
                Log.i("loginSuccessful", "Inicio de sesión exitoso.");
                UserDeserializer userDeserializer = new UserDeserializer();
                //User user = userDeserializer.deserializeUser((JsonObject) value.getResult());
                //saveInSharedPreferences(user.getEmail(), user.getFirstName(), user.getLastName());
                goToMenu();

            }

            @Override
            public void onError(Throwable e) {
                Log.i("loginUnsuccessful", "Hubo un error en el inicio de sesión");
                e.printStackTrace();
                Toast.makeText(Login.this, "Hubo un error en el inicio de sesión. Intente otra vez.", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void saveInSharedPreferences(String userEmail, String firstName, String lastName) {
        SaveSharedPreferences.setEmail(Login.this, userEmail);
        SaveSharedPreferences.setFirstname(Login.this, firstName);
        SaveSharedPreferences.setLastname(Login.this, lastName);
    }

    private void goToMenu() {
        startActivity(new Intent(Login.this, Menu.class));
        finish();
    }
}
