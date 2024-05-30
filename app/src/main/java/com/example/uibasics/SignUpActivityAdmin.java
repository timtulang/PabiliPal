package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.uibasics.databinding.ActivitySignUpBinding;

public class SignUpActivityAdmin extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private DatabaseHelperLogin databaseHelperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        databaseHelperLogin = new DatabaseHelperLogin(this);

        // Set up the spinner
        setupSpinner();

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void setupSpinner() {
        // No need to disable spinner and set default role to "Admin" since it's not selectable
    }

    private void signUp() {
        String user = binding.signupUser.getText().toString();
        String password = binding.signupPassword.getText().toString();
        String confirmPassword = binding.signupConfirm.getText().toString();

        if (user.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(SignUpActivityAdmin.this, "Please fill in all input fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(SignUpActivityAdmin.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Always sign up as Admin
        String selectedRole = "Admin";

        if (databaseHelperLogin.checkUser(user)) {
            Toast.makeText(SignUpActivityAdmin.this, "User already exists, continue to login", Toast.LENGTH_SHORT).show();
            navigateToLogin();
        } else {
            boolean insert = databaseHelperLogin.insertData(user, password, selectedRole);
            if (insert) {
                Toast.makeText(SignUpActivityAdmin.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            } else {
                Toast.makeText(SignUpActivityAdmin.this, "Signup Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish(); // Finish SignUpActivity to prevent user from coming back to it when pressing back button
    }
}
