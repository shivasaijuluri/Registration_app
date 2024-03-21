package com.example.regfire;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class notification extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t =findViewById(R.id.notificataion_text);
        String n=getIntent().getStringExtra("Message");
        t.setText(n);
    }
}