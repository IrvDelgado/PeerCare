package com.vogella.android.retrofitgithub.authentication.signup;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vogella.android.retrofitgithub.R;
import com.vogella.android.retrofitgithub.authentication.UserControllerAPI;
import com.vogella.android.retrofitgithub.authentication.UserService;
import com.vogella.android.retrofitgithub.authentication.signin.Login;
import com.vogella.android.retrofitgithub.common.BackToolbar;
import com.vogella.android.retrofitgithub.common.OwnValidator;
import com.vogella.android.retrofitgithub.common.user.Role;
import com.vogella.android.retrofitgithub.common.user.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SignUpThirdScreen extends AppCompatActivity {

    private TextView inputEmail, inputFirstName, inputLastName, inputAddress, inputCity, inputZipcode;
    private Button btnSignUp;
    private UserControllerAPI userControllerAPI;
    private UserService userService;
    private CompositeDisposable compositeDisposable;
    private ProgressBar progressBar;
    private UserAddRequest userAddRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_third_screen);

        userAddRequest = (UserAddRequest) getIntent().getSerializableExtra("userAddRequest");

        setToolbar();

        dataInit();

        viewInit();

        buttonListeners();

    }

    public void dataInit() {
        inputEmail = findViewById(R.id.email);
        inputFirstName = findViewById(R.id.firstName);
        inputLastName = findViewById(R.id.lastName);
        inputAddress = findViewById(R.id.address);
        inputCity = findViewById(R.id.city);
        inputZipcode = findViewById(R.id.zipcode);
        btnSignUp = findViewById(R.id.btn_sign_up);
        progressBar = findViewById(R.id.progressBar);
        userService = new UserService();
        compositeDisposable = new CompositeDisposable();
        userControllerAPI = userService.connectWithSignUpApi();
    }

    private void viewInit() {
        inputEmail.setText("Email: " + userAddRequest.getEmail());
        inputFirstName.setText("Nombre: " + userAddRequest.getFirstName());
        inputLastName.setText("Apellido: " + userAddRequest.getLastName());
        inputAddress.setText("Calle y número: " + userAddRequest.getAddress());
        inputCity.setText("Ciudad: " + userAddRequest.getCity());
        inputZipcode.setText("Código Postal: " + userAddRequest.getZipcode());
    }

    private void buttonListeners() {
        btnSignUp.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            sendRequest(userAddRequest);
        });
    }

    private void sendRequest(UserAddRequest userAddRequest) {
        compositeDisposable.add(userControllerAPI.addUser(userAddRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getRepositoriesObserver()));
    }

    private DisposableSingleObserver<User> getRepositoriesObserver() {
        return new DisposableSingleObserver<User>() {

            @Override
            public void onSuccess(User value) {
                Log.i("SignUpThirdScreen", "Error al conectar con el servidor.");
                Toast.makeText(SignUpThirdScreen.this, "¡Éxito! Ahora verífique su cuenta.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.i("SignUpThirdScreen", "Error al conectar con el servidor.");
                e.printStackTrace();
                Toast.makeText(SignUpThirdScreen.this, "Hubo un error. Intente de nuevo más tarde.", Toast.LENGTH_LONG).show();
            }
        };
    }

    public void setToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Confirme sus datos");
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationOnClickListener(v -> startActivity(new Intent(this, SignUpSecondScreen.class)));
    }
}
