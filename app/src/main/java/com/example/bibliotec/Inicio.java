package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    TextView datos;
    Button libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent intent = getIntent();
        String user = intent.getStringExtra(background.EXTRA_TEXT);

        datos = (TextView) findViewById(R.id.datos);
        datos.setText(user);
        inicio_helper bg = new inicio_helper(this);
        bg.execute(user);
    }
/*
    public void libros(View view) {

        inicio_helper bg = new inicio_helper(this);
        bg.execute(user);
    }*/
}