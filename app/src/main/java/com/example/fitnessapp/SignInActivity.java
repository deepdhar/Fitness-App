package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView registerButton = findViewById(R.id.makeAccountButton);
        registerButton.setOnClickListener(view -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });

        ImageView backButton = findViewById(R.id.backButtonSignIn);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        TextView signInButton = findViewById(R.id.signInButton2);
        signInButton.setOnClickListener(view -> {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        });
    }
}