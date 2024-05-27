package com.example.uibasics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uibasics.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private DatabaseHelperLogin databaseHelperLogin;
    private String selectedRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.roleSpinner.setAdapter(adapter);
        binding.roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRole = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default role, can be changed as needed
                selectedRole = "User";
            }
        });
    }

    private void signUp() {
        String user = binding.signupUser.getText().toString();
        String password = binding.signupPassword.getText().toString();
        String confirmPassword = binding.signupConfirm.getText().toString();

        if (user.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please fill in all input fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelperLogin.checkUser(user)) {
            Toast.makeText(SignUpActivity.this, "User already exists, continue to login", Toast.LENGTH_SHORT).show();
            navigateToLogin();
        } else {
            boolean insert = databaseHelperLogin.insertData(user, password, selectedRole);
            if (insert) {
                Toast.makeText(SignUpActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            } else {
                Toast.makeText(SignUpActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish(); // Finish SignUpActivity to prevent user from coming back to it when pressing back button
    }
}
