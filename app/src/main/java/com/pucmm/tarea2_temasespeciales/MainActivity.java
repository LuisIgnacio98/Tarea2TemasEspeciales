package com.pucmm.tarea2_temasespeciales;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int REQUEST_CODE = 200;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView continueTextView = (TextView) findViewById(R.id.continueTextView);
        TextView cancelTextView = (TextView) findViewById(R.id.cancelTextView);
        Switch storageSwitch = (Switch) findViewById(R.id.storageSwitch);
        Switch locationSwitch = (Switch) findViewById(R.id.locationSwitch);
        Switch cameraSwitch = (Switch) findViewById(R.id.camaraSwitch);
        Switch phoneSwitch = (Switch) findViewById(R.id.phoneSwitch);
        Switch contactsSwitch = (Switch) findViewById(R.id.contactsSwitch);

        int camera = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
        int phone = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);
        int storage = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int location = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int contacts = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);

        if (camera == PackageManager.PERMISSION_GRANTED) {
            cameraSwitch.setChecked(true);
            cameraSwitch.setClickable(false);
        }
        if (phone == PackageManager.PERMISSION_GRANTED) {
            phoneSwitch.setChecked(true);
            phoneSwitch.setClickable(false);
        }
        if (storage == PackageManager.PERMISSION_GRANTED) {
            storageSwitch.setChecked(true);
            storageSwitch.setClickable(false);
        }
        if (location == PackageManager.PERMISSION_GRANTED) {
            locationSwitch.setChecked(true);
            locationSwitch.setClickable(false);
        }
        if (contacts == PackageManager.PERMISSION_GRANTED) {
            contactsSwitch.setChecked(true);
            contactsSwitch.setClickable(false);
        }

        continueTextView.setOnClickListener(v -> {
            boolean permisosAceptados = false;
            ArrayList<String> permisos = new ArrayList<>();


            if (storageSwitch.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            if (cameraSwitch.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.CAMERA);
                }
            }
            if (phoneSwitch.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.CALL_PHONE);
                }
            }
            if (contactsSwitch.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.READ_CONTACTS);
                }
            }
            if (locationSwitch.isChecked()) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    permisos.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }

            Intent intent = new Intent(v.getContext(), PermissionActivity.class);
            Log.wtf("PERMISOS", permisos.toString());

            permisosAceptados = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
            if (permisos.size() > 0) {
                ActivityCompat.requestPermissions(MainActivity.this, permisos.toArray(new String[0]), REQUEST_CODE);
            } else if (permisosAceptados)
                startActivity(intent);
        });

        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permisos Concedido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PermissionActivity.class);
                startActivity(intent);
            }
        }
    }
}