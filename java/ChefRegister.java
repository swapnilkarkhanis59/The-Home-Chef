package com.example.kaustubh.homechef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaustubh.homechef.SampleClass.Chef;

import com.example.kaustubh.homechef.R;
import com.example.kaustubh.homechef.SampleClass.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Kaustubh on 10-Aug-19.
 */

public class ChefRegister extends Activity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private EditText editTextUserName,editTextUserPhone,editTextUserPassword,editTextUserAddress,editTextUserEmail;
    String id, name, address, phone, password, email;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_register);
        Intent i4 = getIntent();
        Intent i9 = getIntent();

        mAuth = FirebaseAuth.getInstance();
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserAddress= findViewById(R.id.editTextUserAddress);
        editTextUserEmail = findViewById(R.id.editTextUserEmail);
        editTextUserPhone = findViewById(R.id.editTextUserPhone);
        editTextUserPassword = findViewById(R.id.editTextUserPassword);
        databaseReference = FirebaseDatabase.getInstance().getReference("Chefs");

    }

    public void savec(View view) {



        name = editTextUserName.getText().toString().trim();
        phone = editTextUserPhone.getText().toString().trim();
        address = editTextUserAddress.getText().toString().trim();
        email = editTextUserEmail.getText().toString().trim();
        password = editTextUserPassword.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter all information", Toast.LENGTH_LONG).show();
        } else {

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    id = mAuth.getUid();

                    Chef chef = new Chef(id, name, phone, email, address, password);

                    databaseReference.child(id).setValue(chef);

                    Toast.makeText(ChefRegister.this, "Done", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ChefRegister.this,ChefLogin.class);
                    startActivity(intent);
                    finish();

                }
            });


        }
    }

    }
