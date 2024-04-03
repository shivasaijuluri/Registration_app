package com.example.regfire;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendsActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView progressPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        progressBar = findViewById(R.id.progressBar);
//        progressText = findViewById(R.id.progressText);
//        progressPercentage = findViewById(R.id.progressPercentage);
        Button progressButton = findViewById(R.id.progressButton);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_friends);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_logout) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_friends) {
                return true;
            }
            return false;
        });

        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a progress dialog
                ProgressDialog progressDialog = new ProgressDialog(FriendsActivity.this);
                progressDialog.setMax(100); // Set the maximum value of the progress bar
                progressDialog.setMessage("Loading..."); // Set a message for the progress dialog
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // Set the style of the progress dialog to horizontal
                progressDialog.setCancelable(false); // Set cancelable to false to prevent users from dismissing the dialog
                progressDialog.show();

                // Simulate some background work and update the progress bar
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while (progress <= 100) {
                            try {
                                Thread.sleep(50); // Simulate some delay
                                progressDialog.setProgress(progress);
                                progress++;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // Dismiss the progress dialog after the progress is completed
                        progressDialog.dismiss();
                        // Here you can perform any additional actions after the progress is completed
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FriendsActivity.this, "Progress Completed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
