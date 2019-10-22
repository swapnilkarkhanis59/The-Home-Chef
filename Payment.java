package com.example.kaustubh.homechef;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Payment extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
    }

    public void cash(View view){
        Toast.makeText(getApplicationContext(),"Order has been placed successfully, Please pay on delivery",Toast.LENGTH_SHORT).show();
    }

    public void paytm(View view){
        Toast.makeText(getApplicationContext(),"Order has been placed successfully, Redirecting to Paytm App",Toast.LENGTH_SHORT).show();
        String appPackageName = "net.one97.paytm";
        PackageManager pm = getBaseContext().getPackageManager();
        Intent appstart = pm.getLaunchIntentForPackage(appPackageName);
        if(null!=appstart)
        {
            getBaseContext().startActivity(appstart);
        }
        else {
            Toast.makeText(getBaseContext(), "Install PayTm on your device", Toast.LENGTH_SHORT).show();
        }
    }
}