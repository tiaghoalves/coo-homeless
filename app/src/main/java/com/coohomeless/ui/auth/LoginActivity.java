package com.coohomeless.ui.auth;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.coohomeless.R;
import com.coohomeless.ui.MenuActivity;
import com.coohomeless.ui.SplashScreenActivity;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseAuthActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;

    @BindView(R.id.input_email) EditText inputEmail;
    @BindView(R.id.input_password) EditText inputPassword;
    @BindView(R.id.btn_login) ActionProcessButton btnLogin;
    @BindView(R.id.btn_signup) ActionProcessButton btnSignUp;
    @BindView(R.id.sign_in_google) SignInButton btnSignInGoogle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);

        mAuth = super.getmAuth();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        } else if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getApplicationContext(),
                    "Login Google Realizado: " + acct.getDisplayName(), Toast.LENGTH_SHORT).show();
            onLoginSuccess();
        } else {
            // Signed out, show unauthenticated UI.
            logout();
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void login(String email, String password) {
        if (!validate(email, password)) {
            Toast.makeText(getApplicationContext(), "Login inválido", Toast.LENGTH_SHORT).show();
            onLoginFailed();
            return;
        }
        btnLogin.setEnabled(false);

        // TODO: Implement your own authentication logic
        // On complete call either onLoginSuccess or onLoginFailed
        Toast.makeText(getApplicationContext(), "Redirecionando...", Toast.LENGTH_SHORT).show();
        onLoginSuccess();
    }

    private void logout() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Toast.makeText(getApplicationContext(), "Usuario deslogado", Toast.LENGTH_SHORT).show();
                    }
                });
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

    public boolean validate(String email, String password) {
        boolean valid = true;

        if (email.isEmpty() ) {
            inputEmail.setError("endereço de e-mail inválido");
            valid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("obrigatório");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty()) {
            inputPassword.setError("obrigatório");
            valid = false;
        } else if (password.length() < 6) {
            inputPassword.setError("pelo menos 6 caracteres");
            valid = false;
        } else {
            inputPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login) {
            btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
            btnLogin.setProgress(1);
            login(inputEmail.getText().toString(), inputPassword.getText().toString());

        } else if (id == R.id.btn_signup) {
            btnSignUp.setMode(ActionProcessButton.Mode.ENDLESS);
            btnSignUp.setProgress(1);
            // Start the Signup activity
            Intent toSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivityForResult(toSignUp, REQUEST_SIGNUP);

        } else if (id == R.id.sign_in_google) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);

        } else if (id == R.id.input_email || id == R.id.input_password) {
            btnLogin.setProgress(0);
            btnSignUp.setProgress(0);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
