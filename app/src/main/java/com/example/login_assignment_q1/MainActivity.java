package com.example.login_assignment_q1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    @SuppressLint({"MissingInflatedId", "ResourceType"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new SplashFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragementContainer, fragment).commit();

    }
}