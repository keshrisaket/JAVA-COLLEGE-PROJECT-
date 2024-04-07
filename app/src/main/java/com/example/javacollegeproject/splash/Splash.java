package com.example.javacollegeproject.splash;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.javacollegeproject.R;
import com.example.javacollegeproject.dahsboard.Dashboard;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iNext=new Intent(Splash.this,Dashboard.class);
                startActivity(iNext);
                finish();
            }
        },4000);

    }
}