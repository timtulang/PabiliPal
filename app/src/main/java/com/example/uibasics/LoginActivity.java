package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uibasics.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DatabaseHelperLogin databaseHelperLogin;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelperLogin = new DatabaseHelperLogin(this);


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp();
            }
        });

        // Check if any admin exists and hide signup redirect if true
        if (databaseHelperLogin.isAnyAdmin()) {
            binding.signupRedirectText.setVisibility(View.GONE);
        } else {
            binding.signupRedirectText.setVisibility(View.VISIBLE);
        }
    }

    private void login() {
        String user = binding.loginUser.getText().toString().trim();
        String password = binding.loginPassword.getText().toString().trim();




        if (user.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill out all input fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelperLogin.checkUser(user)) {
            if (databaseHelperLogin.checkUserPassword(user, password)) {
                navigateToAdminActivity();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, ProductCart.class);
        startActivity(intent);
        finish();
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivityAdmin.class);
        startActivity(intent);
    }

    private void navigateToAdminActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}