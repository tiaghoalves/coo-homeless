package com.coohomeless.ui.auth;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coohomeless.R;
import com.coohomeless.adapters.ApiAdapter;
import com.coohomeless.models.contributor.ContributorModel;
import com.coohomeless.models.organization.OrganizationModel;
import com.coohomeless.repository.ContributorRepository;
import com.coohomeless.repository.OrganizationRepository;
import com.coohomeless.ui.utils.MaskType;
import com.coohomeless.ui.utils.MaskUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.ImmutableMap;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.util.Patterns.*;

public class SignUpActivity extends BaseAuth implements View.OnClickListener {

    private static final String TAG = "SignUpActivity";

    private RestAdapter adapter;
    private String userType;

    @BindView(R.id.input_name) EditText nameText;
    @BindView(R.id.input_email) EditText emailText;
    @BindView(R.id.input_password) EditText passwordText;
    @BindView(R.id.btn_signup) Button signupButton;
    @BindView(R.id.rb_contributor) RadioButton rbContributor;
    @BindView(R.id.rb_ong) RadioButton rbOng;
    @BindView(R.id.rg_user_type) RadioGroup rgUserType;
    @BindView(R.id.input_cpf) EditText cpfText;
    @BindView(R.id.input_cnpj) EditText cnpjText;
    @BindView(R.id.input_endereco) EditText enderecoText;
    @BindView(R.id.input_fone) EditText foneText;
    @BindView(R.id.link_login) TextView loginLink;
    @BindView(R.id.contributor_data) LinearLayout containerContributor;
    @BindView(R.id.ong_data) LinearLayout containerOng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        // 1. Grab the shared RestAdapter instance.
        ApiAdapter apiAdapter = (ApiAdapter) getApplicationContext();
        this.adapter = apiAdapter.getApiAdapter();

        cpfText.addTextChangedListener(MaskUtil.insert(cpfText, MaskType.CPF));
        cnpjText.addTextChangedListener(MaskUtil.insert(cnpjText, MaskType.CNPJ));
        foneText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        signupButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);
        rgUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton button = group.findViewById(checkedId);
                userType = button.getText().toString();
                updateUi(userType);
            }
        });

        mAuth = FirebaseAuth.getInstance();
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

        mAuth.createUserWithEmailAndPassword(email, password)
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

        if (this.userType.equals("Colaborador")) {
            saveContributor();
        } else if (this.userType.equals("Organização")) {
            saveOrganization();
        }

        Intent toLogin = new Intent(SignUpActivity.this, LoginActivity.class);
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

        if (email.isEmpty() || !EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("endereço de e-mail inválido");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            passwordText.setError("pelo menos 6 caracteres");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (containerContributor.getVisibility() == View.VISIBLE) {
            String cpf = cpfText.getText().toString();

            if (cpf.isEmpty()) {
                cpfText.setError("CPF inválido");
                valid = false;
            } else {
                cpfText.setError(null);
            }
        }

        if (containerOng.getVisibility() == View.VISIBLE) {
            String cnpj = cnpjText.getText().toString();
            String phone = foneText.getText().toString();
            String endereco = enderecoText.getText().toString();

            if (cnpj.isEmpty()) {
                cnpjText.setError("CPF inválido");
                valid = false;
            } else {
                cnpjText.setError(null);
            }

            if (endereco.isEmpty()) {
                enderecoText.setError("Endereço inválido");
                valid = false;
            }

            if (foneText.isDirty() || !PHONE.matcher(phone).matches()) {
                foneText.setError("Telefone inválido");
                valid = false;
            }

        }

        if (!rbOng.isChecked() && !rbContributor.isChecked()) {
            valid = false;
        }

        return valid;
    }

    private void saveContributor() {
        ContributorRepository contributorRepo = this.adapter.createRepository(ContributorRepository.class);
        final ContributorModel contributor = contributorRepo.createObject(ImmutableMap.of("name", "Contributor"));

        contributor.setName(this.nameText.getText().toString());
        contributor.setEmail(this.emailText.getText().toString());
        contributor.setCpf(cpfText.getText().toString());

        contributor.save(new VoidCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "SALVOOOOO !", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Colaborador=> "+ contributor.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("SampleSlide", "Deu ruuim Contributor model.", t);
                Toast.makeText(getApplicationContext(), "Deu ruuim Contributor model.", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveOrganization() {
        OrganizationRepository ongRepo = this.adapter.createRepository(OrganizationRepository.class);
        final OrganizationModel organization = ongRepo.createObject(ImmutableMap.of("name", "Organization"));

        organization.setName(this.nameText.getText().toString());
        organization.setDescOrganization(this.nameText.getText().toString());
        organization.setEmail(this.emailText.getText().toString());
        organization.setCnpj(cnpjText.getText().toString());
        organization.setEndereco(enderecoText.getText().toString());
        organization.setFone(foneText.getText().toString());

        organization.save(new VoidCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "SALVOOOOO !", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "ONG=> "+ organization.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("SampleSlide", "Deu ruuim pra salva ONG model.", t);
                Toast.makeText(getApplicationContext(), "Deu ruuim ONG model.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateUi(String userType) {
        if (userType.equals("Colaborador")) {
            containerContributor.setVisibility(View.VISIBLE);
            containerOng.setVisibility(View.GONE);
        } else if (userType.equals("Organização")) {
            containerOng.setVisibility(View.VISIBLE);
            containerContributor.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_signup) {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            signUp(email, password);

        } else if (id == R.id.link_login){
            Intent toLogin = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(toLogin);
            finish();
        }
    }
}
