package com.example.myshoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myshoppingapp.MainActivity2;
import com.example.myshoppingapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressbar=findViewById(R.id.progressbar);
        progressbar.setVisibility(View.GONE);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            progressbar.setVisibility(View.VISIBLE);
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }

    public void Registration(View view) {
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));

    }
}