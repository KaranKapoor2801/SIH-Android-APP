package com.example.workerattendance.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.workerattendance.ClientActivity;
import com.example.workerattendance.Config;
import com.example.workerattendance.LoginSuccessActivity;
import com.example.workerattendance.MainSiteActivity;
import com.example.workerattendance.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    SignInButton button;
    private static final int RC_SIGN_IN=101;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private RelativeLayout mLoadingLayout;
    private RelativeLayout mLoginLayout;
    private static final String TAG = "LogInForConstructor";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoadingLayout=(RelativeLayout)findViewById(R.id.progressRelativeLayoutLogIn);
        mLoadingLayout.setVisibility(View.GONE);
        mLoginLayout=findViewById(R.id.activityLoginRelativeLayout);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null) {
            mLoadingLayout.setVisibility(View.VISIBLE);
            mLoginLayout.setVisibility(View.GONE);
            String curEmail = mAuth.getCurrentUser().getEmail().replace('.','_');
            FirebaseDatabase.getInstance().getReference().child(Config.LOGIN_HISTORY).child(curEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null    ) {
                        emailDoesNotExists();
                        mLoadingLayout.setVisibility(View.GONE);
                    } else {
                        String type = dataSnapshot.child(Config.LOGIN_TYPE).getValue().toString();
                        switch (type) {
                            case Config.CONSTRUCTOR: {
                                //Todo: Start sakshis's activity
                                Intent intent=new Intent(getApplicationContext(), MainSiteActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            }
                            case Config.Client: {
                                //Todo: Start karan's activity
                                Intent intent=new Intent(getApplicationContext(), ClientActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            }
                            case Config.SITE_ENGINEER:
                            {
                                //TODO:Start engineer activity
                                break;
                            }

                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        button=(SignInButton)findViewById(R.id.SignInButtonLogIn);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser mUser=mAuth.getCurrentUser();
                            String email=mUser.getEmail();
                            email = email.replace('.','_');
                            final String finalEmail = email;
                            FirebaseDatabase.getInstance().getReference().child(Config.LOGIN_HISTORY).child(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() == null    ) {
                                        emailDoesNotExists();
                                    } else {
                                        String type = dataSnapshot.child(Config.LOGIN_TYPE).getValue().toString();
                                        switch (type) {
                                            case Config.CONSTRUCTOR: {
                                                //Todo: Start sakshis's activity
                                                Intent intent=new Intent(getApplicationContext(), MainSiteActivity.class);
                                                startActivity(intent);
                                                finish();
                                                break;
                                            }
                                            case Config.Client: {
                                                //Todo: Start karan's activity
                                                break;
                                            }
                                            case Config.SITE_ENGINEER:
                                            {
                                                //TODO:Start engineer activity
                                                break;
                                            }
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            // Sign in success, update UI with the signed-in user's information
                        } else {
                            Toast.makeText(getApplicationContext(),"Task UnSuccess",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onComplete: "+task.getException().getMessage());
                            // If sign in fails, display a message to the user.
                        }

                        // ...
                    }
                });
    }
    private void emailDoesNotExists() {
        //Todo: Start new activity to register
        Intent intent=new Intent(getApplicationContext(),LoginTypeActivity.class);
        startActivity(intent);
        finish();
    }
}

