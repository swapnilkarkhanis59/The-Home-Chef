package com.example.kaustubh.homechef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Kaustubh on 30-Jul-19.
 */

public class UserLogin extends Activity implements View.OnClickListener {

    private static final String TAG = "UserLogin";
    private FirebaseAuth mAuth;
    EditText username,password;

    Button login,register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.userlogin).setOnClickListener(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.userlogin);
        register = findViewById(R.id.userRegister);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if (mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(UserLogin.this,Chefs.class);
            intent.putExtra("ID",mAuth.getUid());
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.userlogin:
                String username1;
                String password1;

                 username1 = username.getText().toString().trim();
                 password1 = password.getText().toString().trim();

                if(username1.isEmpty()|| password1.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the login details",Toast.LENGTH_SHORT).show();
                }

                else
                mAuth.signInWithEmailAndPassword(username1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(UserLogin.this, Chefs.class);
                            intent.putExtra("ID", mAuth.getUid());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case R.id.userRegister:
                Intent intent = new Intent(UserLogin.this,UserRegister.class);
                intent.putExtra("ID",mAuth.getUid());
                startActivity(intent);
                finish();
        }
    }
}

