package com.coohomeless.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.coohomeless.R;
import com.coohomeless.models.user.UserModel;
import com.coohomeless.ui.MenuActivity;
import com.dd.processbutton.iml.ActionProcessButton;
import com.strongloop.android.loopback.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.input_email) EditText inputEmail;
    @BindView(R.id.input_password) EditText inputPassword;
    @BindView(R.id.btn_login) ActionProcessButton btnLogin;
    @BindView(R.id.btn_signup) ActionProcessButton btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
                btnLogin.setProgress(1);

                login();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignUp.setMode(ActionProcessButton.Mode.ENDLESS);
                btnSignUp.setProgress(1);

                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }

        btnLogin.setEnabled(false);

        // TODO: Implement your own authentication logic here.
        new Handler().postDelayed(new Runnable() {
            public void run() {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                // On complete call either onLoginSuccess or onLoginFailed
                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    onLoginFailed();
                } else {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    onLoginSuccess();
                }
            }
        }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        Intent toMenu = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(toMenu);
        finish();
    }

    public void onLoginFailed() {
        btnLogin.setProgress(-1);
        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("endereço de e-mail inválido");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("pelo menos 6 caracteres");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

}
