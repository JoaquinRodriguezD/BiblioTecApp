package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Libros extends AppCompatActivity {

    Intent intent = getIntent();
    String user = intent.getStringExtra(inicio_helper.EXTRA_TEXT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libros);
    }
}