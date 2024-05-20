package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uibasics.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private DatabaseHelperLogin databaseHelperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "LoginActivity Launched", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelperLogin = new DatabaseHelperLogin(this);

        // Setting up the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.roleSpinner.setAdapter(adapter);

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
    }

    private void login() {
        String user = binding.loginUser.getText().toString();
        String password = binding.loginPassword.getText().toString();
        String selectedRole = binding.roleSpinner.getSelectedItem().toString();

        if (user.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill out all input fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelperLogin.checkUser(user)) {
            if (databaseHelperLogin.checkUserPassword(user, password)) {
                if (selectedRole.equals("Admin") && databaseHelperLogin.isAdmin(user)) {
                    navigateToMainActivity(); // TODO DISTINGUISH BETWEEN ADMIN AND USER CAN DO
                } else if (selectedRole.equals("User")) {
                    navigateToMainActivity(); // Navigate to MainActivity for regular users
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid role selection", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish LoginActivity to prevent user from coming back to it when pressing back button
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void navigateToAdminActivity() {
        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
        startActivity(intent);
        finish(); // Finish LoginActivity to prevent user from coming back to it when pressing back button
    }
}
