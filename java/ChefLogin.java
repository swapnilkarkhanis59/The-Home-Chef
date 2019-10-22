package com.example.kaustubh.homechef;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by Kaustubh on 30-Jul-19.
 */

public class ChefLogin extends Activity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private static final String TAG = "ChefLogin";
    public EditText username,password;
    public Button buttonLogin,buttonRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_login);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.chefLogin);
        buttonRegister = findViewById(R.id.chefRegister);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(ChefLogin.this,ChefPage.class);
            intent.putExtra("ID",mAuth.getUid());
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.chefLogin:
                String username1=null;
                String password1=null;

                 username1 = username.getText().toString().trim();
                 password1 = password.getText().toString().trim();

                if (username1.isEmpty() || password1.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Please enter the login details",Toast.LENGTH_SHORT).show();
                }

                else

                mAuth.signInWithEmailAndPassword(username1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(ChefLogin.this, ChefPage.class);
                            intent.putExtra("ID", mAuth.getUid());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid login credentials", Toast.LENGTH_SHORT).show();
                        }
                    }});
                        break;

            case R.id.chefRegister:

                Intent intent = new Intent(ChefLogin.this,ChefRegister.class);
                intent.putExtra("ID",mAuth.getUid());
                startActivity(intent);
                finish();

                break;
        }
    }

}
