package com.example.regfire;

import static com.example.regfire.R.id.bottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

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
    }
}
