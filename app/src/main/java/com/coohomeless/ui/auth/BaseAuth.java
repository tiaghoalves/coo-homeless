package com.coohomeless.ui.auth;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BaseAuth extends FragmentActivity {

    private final String TAG = "BaseAuth";

    /* A reference to the Firebase */
    protected static FirebaseAuth mAuth;

    /* Listener for Firebase session changes */
    protected FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mAuth = FirebaseAuth.getInstance();

        this.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.mAuthListener != null) {
            this.mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public FirebaseAuth getmAuth() {
        return this.mAuth;
    }

    public static FirebaseUser getAuthUser(){
        return mAuth.getCurrentUser();
    }
}
