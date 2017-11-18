package com.coohomeless.ui.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coohomeless.R;
import com.coohomeless.adapters.ApiAdapter;
import com.coohomeless.models.user.UserModel;
import com.coohomeless.repository.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.strongloop.android.loopback.RestAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseAuth implements View.OnClickListener {

    private static final String TAG = "SignUpActivity";

//    private FirebaseAuth mAuth;
    private RestAdapter adapter;
    private UserRepository userRepository;

    @BindView(R.id.input_name) EditText nameText;
    @BindView(R.id.input_email) EditText emailText;
    @BindView(R.id.input_password) EditText passwordText;
    @BindView(R.id.btn_signup) Button signupButton;
    @BindView(R.id.link_login) TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        // 1. Grab the shared RestAdapter instance.
        ApiAdapter apiAdapter = (ApiAdapter) getApplicationContext();
        this.adapter = apiAdapter.getApiAdapter();

        signupButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);

        super.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void signUp(String email, String password) {
        if (!validate()) {
            onSignupFailed();
            return;
        }
        signupButton.setEnabled(false);

        super.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            onSignupSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            onSignupFailed();
                        }
                    }
                });

    }

    private void sendEmailVerification() {
        // Send verification email
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this,
                                        "Verification email sent to " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e(TAG, "sendEmailVerification", task.getException());
                                Toast.makeText(SignUpActivity.this,
                                        "Failed to send verification email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        sendEmailVerification();

//        FirebaseUser user = mAuth.getCurrentUser();
        this.userRepository = this.adapter.createRepository(UserRepository.class);
        UserModel userModel = userRepository.createObject(ImmutableMap.of("name", "User"));

        // Date format to String from Calendar
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        Date date = new Date();

        userModel.setCreatedAt(dateFormat.format(new Date()));
        userModel.setUpdatedAt(dateFormat.format(new Date()));
        userModel.setFullName(this.nameText.getText().toString());
        userModel.setEmail(this.emailText.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putSerializable("userModel", userModel);

        Intent toLogin = new Intent(SignUpActivity.this, LoginActivity.class);
        toLogin.putExtras(bundle);
        startActivity(toLogin);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Dados inválidos", Toast.LENGTH_LONG).show();
        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("pelo menos 3 caracteres");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("endereço de e-mail inválido");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() > 6) {
            passwordText.setError("pelo menos 6 caracteres");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_signup) {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            signUp(email, password);

        } else if (id == R.id.link_login){
            // Finish the registration screen and return to the Login activity
            Intent toLogin = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(toLogin);
            finish();
        }
    }
}
