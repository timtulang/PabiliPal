package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.uibasics.databinding.ActivitySignUpUserBinding;

public class SignUpActivityUser extends AppCompatActivity {

    private ActivitySignUpUserBinding binding;
    private DatabaseHelperLogin databaseHelperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_user);

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

        ImageButton backButtonSignUpUser;
        backButtonSignUpUser = findViewById(R.id.imageButton8);
        backButtonSignUpUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SignUpActivityUser.this, MainActivity.class);
                startActivity(intent);
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
            Toast.makeText(SignUpActivityUser.this, "Please fill in all input fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(SignUpActivityUser.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Always sign up as Admin
        String selectedRole = "User";

        if (databaseHelperLogin.checkUser(user)) {
            Toast.makeText(SignUpActivityUser.this, "User already exists, continue to login", Toast.LENGTH_SHORT).show();
            navigateToLogin();
        } else {
            boolean insert = databaseHelperLogin.insertData(user, password, selectedRole);
            if (insert) {
                Toast.makeText(SignUpActivityUser.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            } else {
                Toast.makeText(SignUpActivityUser.this, "Signup Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish(); // Finish SignUpActivity to prevent user from coming back to it when pressing back button
    }
}
