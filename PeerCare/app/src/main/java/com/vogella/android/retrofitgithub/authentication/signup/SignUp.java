package com.vogella.android.retrofitgithub.authentication.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.vogella.android.retrofitgithub.R;
import com.vogella.android.retrofitgithub.authentication.MainAuthMenu;
import com.vogella.android.retrofitgithub.authentication.UserService;
import com.vogella.android.retrofitgithub.authentication.signin.Login;
import com.vogella.android.retrofitgithub.common.BackToolbar;
import com.vogella.android.retrofitgithub.common.OwnValidator;
import com.vogella.android.retrofitgithub.common.user.Role;

public class SignUp extends AppCompatActivity {

    private EditText inputFirstName, inputLastName, inputAddress, inputCity, inputZipcode;
    private Button btnNext;
    private OwnValidator ownValidator;
    private UserAddRequest userAddRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setToolbar();

        dataInit();

        userAddRequest = UserAddRequest.builder().build();

        buttonListeners();

    }

    private void dataInit() {
        ownValidator = new OwnValidator();
        btnNext = findViewById(R.id.next);
        inputFirstName = findViewById(R.id.firstName);
        inputLastName = findViewById(R.id.lastName);
        inputAddress = findViewById(R.id.address);
        inputCity = findViewById(R.id.city);
        inputZipcode = findViewById(R.id.zipcode);

    }

    private void buttonListeners() {
        btnNext.setOnClickListener(v ->
        {
            if (!areFieldsValid()) return;

            userAddRequest = UserAddRequest.builder()
                    .firstName(inputFirstName.getText().toString())
                    .lastName(inputLastName.getText().toString())
                    .dateOfBirth("2000-01-01'T'05:05:05")
                    .address(inputAddress.getText().toString())
                    .city(inputCity.getText().toString())
                    .zipcode(inputZipcode.getText().toString())
                    .role(Role.ELDERLY)
                    .build();

            startNewActivity();

        });
    }

    private boolean areFieldsValid() {
        boolean isFirstNameValid = ownValidator.isFieldNotEmpty(SignUp.this, inputFirstName);
        boolean isLastNameValid = ownValidator.isFieldNotEmpty(SignUp.this, inputLastName);
        boolean isAddressValid = ownValidator.isFieldNotEmpty(SignUp.this, inputAddress);
        boolean isCityValid = ownValidator.isFieldNotEmpty(SignUp.this, inputCity);
        boolean isZipcodeValid = ownValidator.isFieldNotEmpty(SignUp.this, inputZipcode);

        return isFirstNameValid && isLastNameValid && isAddressValid && isCityValid && isZipcodeValid;
    }

    private void startNewActivity() {
        Intent intent = new Intent(SignUp.this, SignUpSecondScreen.class);
        intent.putExtra("userAddRequest", userAddRequest);
        startActivity(intent);
    }

    public void setToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Registro - Primer paso");
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationOnClickListener(v -> startActivity(new Intent(this, MainAuthMenu.class)));
    }
}
