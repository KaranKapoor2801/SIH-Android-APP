/*
package com.example.workerattendance;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FireBaseManager {
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseRef;
    public FireBaseManager()
    {
        mAuth=FirebaseAuth.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference();
    }
    public void signInNewUser(final Context mContext, String emailId, String password)
    {
        mAuth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    exceptionToast(mContext,task.getException().getMessage());
                    Log.d("hello", "onComplete: "+task.getException().getMessage());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void exceptionToast(Context mContext,String mExp)
    {
        String NETWORK_ERROR="A network error (such as timeout, interrupted connection or unreachable host) has occurred.";
        String EMAIL_ADDRESS_WRONG_FORMATTED="The email address is badly formatted.";
        String INVALID_PASSWORD="The given password is invalid. [ Password should be at least 6 characters";
        String EMAIL_ALREADY_EXISTS="The email address is already in use by another account.";
        if(EMAIL_ADDRESS_WRONG_FORMATTED.equals(mExp))
        {
            Toast.makeText(mContext,"Wrong Formatted Email Address",Toast.LENGTH_SHORT).show();
        }
        if (INVALID_PASSWORD.equals(mExp))
        {
            Toast.makeText(mContext,"Password Must be at least 6 characters",Toast.LENGTH_SHORT).show();
        }
        if(EMAIL_ALREADY_EXISTS.equals(mExp))
        {
            Toast.makeText(mContext,"Email Already Exists",Toast.LENGTH_SHORT).show();
        }
        if(NETWORK_ERROR.equals(mExp))
        {
            Toast.makeText(mContext,"Please Check Your Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkStringNotEmpty(String check)
    {
        return !check.equals("");
    }
    public void updateConstructorDataOnFireBase(Context mContext,String userUid, DataOfConstructor data)
    {
        mDatabaseRef.child(mContext.getString(R.string.dataofconstructor)).child(userUid).setValue(data);
    }
    public boolean checkStringExist(String filed,String mCheck,Context mContext)
    {
        final boolean[] check = {false};
        mDatabaseRef.child(mContext.getString(R.string.dataofconstructor)).orderByChild(filed).equalTo(mCheck).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             if(dataSnapshot.exists())
                 check[0] =true;
             else
                 check[0]=false;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return check[0];
    }
    public void sendVerifyEmail(FirebaseUser mUser, final Context mContext)
    {
        if(mUser!=null)
        {
            mUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(mContext,"Send Verify Email Please Verify",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(mContext,task.getException()+"",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}
*/
