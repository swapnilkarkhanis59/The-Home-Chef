package com.example.kaustubh.homechef;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chefloginb(View view){
        Intent i1 = new Intent(getApplicationContext(),ChefLogin.class);
        startActivity(i1);
    }

    public void userloginb(View view){
        Intent i2 = new Intent(getApplicationContext(),UserLogin.class);
        startActivity(i2);
    }

}
