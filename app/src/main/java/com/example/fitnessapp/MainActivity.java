package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(view -> {
            Intent i = new Intent(this, SignInActivity.class);
            startActivity(i);
        });

        TextView registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(view -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

    }
}