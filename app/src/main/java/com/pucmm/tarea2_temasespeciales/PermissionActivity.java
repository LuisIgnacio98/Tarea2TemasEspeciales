package com.pucmm.tarea2_temasespeciales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView cameraTextView = findViewById(R.id.cameraTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);
        TextView storageTextView = findViewById(R.id.storageTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView contactsTextView = findViewById(R.id.contactsTextView);


        cameraTextView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            } else {
                Toast.makeText(this, "Please request permission", Toast.LENGTH_SHORT).show();
            }

        });
        phoneTextView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent phoneIntent = new Intent(Intent.ACTION_CALL_BUTTON);
                startActivity(phoneIntent);
            } else {
                Toast.makeText(this, "Please request permission", Toast.LENGTH_SHORT).show();
            }
        });
        storageTextView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent storageIntent = new Intent(Intent.ACTION_VIEW);
                storageIntent.setType("image/*");
                storageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(storageIntent);
            } else {
                Toast.makeText(this, "Please request permission", Toast.LENGTH_SHORT).show();
            }
        });
        locationTextView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(locationIntent);
            } else {
                Toast.makeText(this, "Please request permission", Toast.LENGTH_SHORT).show();
            }
        });
        contactsTextView.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                Intent contactsIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivity(contactsIntent);
            } else {
                Toast.makeText(this, "Please request permission", Toast.LENGTH_SHORT).show();
            }

        });
    }

}