package com.coohomeless.ui.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coohomeless.R;
import com.coohomeless.models.user.UserModel;
import com.coohomeless.ui.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent toLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(toLogin);
                finish();
            }
        });
    }

    public void signup() {
        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        // TODO: Implement your own signup logic here.

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        String name = _nameText.getText().toString();
                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();

                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        UserModel user = new UserModel();
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setFullName(name);

                        onSignupSuccess(user);
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess(UserModel user) {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);

        Intent toLogin = new Intent(SignUpActivity.this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        toLogin.putExtra("user", bundle);

        startActivity(toLogin);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("pelo menos 3 caracteres");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("endereço de e-mail inválido");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() > 6) {
            _passwordText.setError("pelo menos 6 caracteres");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
