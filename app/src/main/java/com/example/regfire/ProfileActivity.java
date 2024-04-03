package com.example.regfire;

import static com.example.regfire.R.id.bottomNavigationView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    String message = "Hello, this is your notification in detail!!!";

    TextView profileName, profileEmail, profileUsername, profilePassword, profileDOB;
    TextView titleName, titleUsername, titleDOB;
    public static String nameUser, emailUser, usernameUser, passwordUser, dobUser;

    Dialog dialog;
    Button btnDialogCancel,btnDialogLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileUsername);
        profilePassword = findViewById(R.id.profilePassword);
        profileDOB = findViewById(R.id.profileDOB);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUsername);
        titleDOB = findViewById(R.id.titleDOB);

        showAllUserData();

        titleName.setText(nameUser);
        titleUsername.setText(usernameUser);
        profileName.setText(nameUser);
        profileEmail.setText(emailUser);
        profileUsername.setText(usernameUser);
        profilePassword.setText(passwordUser);
        profileDOB.setText(dobUser);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("StuReg");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                return true;
            } else if (item.getItemId() == R.id.bottom_friends) {
                startActivity(new Intent(getApplicationContext(), FriendsActivity.class));
                finish();
                return true;
            }
//            else if (item.getItemId() == R.id.bottom_editprofile) {
//                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                finish();
//                return true;
//            }
            else if (item.getItemId() == R.id.bottom_logout) {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
                dialog.show();
                return true;
            }
            return false;
        });

       Button notificationButton = findViewById(R.id.notificationButton);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mynotification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });


        dialog = new Dialog(ProfileActivity.this);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_box));
        dialog.setCancelable(false);

        btnDialogLogout=dialog.findViewById(R.id.button_exit);
        btnDialogCancel=dialog.findViewById(R.id.button_cancel);

        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnDialogLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                dialog.dismiss();

            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mynotification")
                .setSmallIcon(R.drawable.baseline_message_24)
                .setContentTitle("Notification")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        Intent intent = new Intent(this, notification.class);
        intent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.notify(1, builder.build());
    }


    public void showAllUserData() {

        Intent intent = getIntent();
        if (intent.hasExtra("FROM_ACTIVITY_1")){
            nameUser = intent.getStringExtra("name");
            emailUser = intent.getStringExtra("email");
            usernameUser = intent.getStringExtra("username");
            passwordUser = intent.getStringExtra("password");
            dobUser = intent.getStringExtra("dob");
        }
        // Set the retrieved DOB
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_toolbar, menu);
        return true;
    }


}
