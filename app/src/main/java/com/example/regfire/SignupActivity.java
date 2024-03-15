package com.example.regfire;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword, signupDOB;
    TextView loginRedirectText;

    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupDOB = findViewById(R.id.signup_dob); // Initialize the new EditText
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database= FirebaseDatabase.getInstance();
                reference= database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String dob = signupDOB.getText().toString(); // Retrieve date of birth
                String username =  signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name , email, dob, username, password); // Pass dob to HelperClass constructor
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignupActivity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signupDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    // Method to show the date picker dialog
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the EditText with the selected date
                        String dob = dayOfMonth + "/" + (month + 1) + "/" + year;
                        signupDOB.setText(dob);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}
