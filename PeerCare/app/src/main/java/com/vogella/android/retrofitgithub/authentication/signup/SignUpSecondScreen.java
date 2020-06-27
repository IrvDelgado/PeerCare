package com.vogella.android.retrofitgithub.authentication.signup;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vogella.android.retrofitgithub.R;
import com.vogella.android.retrofitgithub.authentication.MainAuthMenu;
import com.vogella.android.retrofitgithub.common.BackToolbar;
import com.vogella.android.retrofitgithub.common.OwnValidator;

public class SignUpSecondScreen extends AppCompatActivity {

    private EditText inputEmail, inputConfirmEmail, inputPassword, inputConfirmPassword;
    private Button btnNext;
    private UserAddRequest userAddRequest;
    private OwnValidator ownValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_second_screen);

        userAddRequest = (UserAddRequest) getIntent().getSerializableExtra("userAddRequest");

        setToolbar();

        dataInit();

        buttonListeners();

    }

    public void dataInit(){
        ownValidator = new OwnValidator();
        btnNext = findViewById(R.id.next);
        inputEmail = findViewById(R.id.email);
        inputConfirmEmail = findViewById(R.id.confirmEmail);
        inputPassword = findViewById(R.id.password);
        inputConfirmPassword = findViewById(R.id.confirmPassword);
    }

    private void buttonListeners() {

        btnNext.setOnClickListener(view -> {
            if(!areFieldsValid()) return;

            userAddRequest.setEmail(inputEmail.getText().toString());
            userAddRequest.setPassword(inputEmail.getText().toString());

            startNewActivity();

        });
    }

    public boolean areFieldsValid(){
        boolean isEmailValid = ownValidator.validEmail(SignUpSecondScreen.this, inputEmail);
        boolean areEmailsTheSame = ownValidator.validateWhetherTheyAreEqual(
                SignUpSecondScreen.this, inputEmail.getText().toString(), inputConfirmEmail.getText().toString());
        boolean isPasswordValid = ownValidator.validPassword(SignUpSecondScreen.this, inputPassword);
        boolean arePasswordsTheSame = ownValidator.validateWhetherTheyAreEqual(
                SignUpSecondScreen.this, inputPassword.getText().toString(), inputConfirmPassword.getText().toString());

        if (!areEmailsTheSame)
            Toast.makeText(this, getString(R.string.emailsNotTheSame), Toast.LENGTH_SHORT).show();

        if (!arePasswordsTheSame)
            Toast.makeText(this, getString(R.string.passwordsNotTheSame), Toast.LENGTH_SHORT).show();

        return isEmailValid && areEmailsTheSame && isPasswordValid && arePasswordsTheSame;
    }

    private void startNewActivity(){
        Intent intent = new Intent(SignUpSecondScreen.this, SignUpThirdScreen.class);
        intent.putExtra("userAddRequest", userAddRequest);
        startActivity(intent);
    }

    public void setToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Registro - Segundo paso");
        setSupportActionBar(myToolbar);

        myToolbar.setNavigationOnClickListener(v -> startActivity(new Intent(this, SignUp.class)));
    }

}
