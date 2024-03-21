package com.example.regfire;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification); // Inflate the layout

        TextView t = findViewById(R.id.notificataion_text); // Corrected ID
        String n = getIntent().getStringExtra("message"); // Corrected key
        t.setText(n);
    }
}
