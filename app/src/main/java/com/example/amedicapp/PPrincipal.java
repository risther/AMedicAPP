package com.example.amedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class PPrincipal extends AppCompatActivity {

    ImageView btnReservar;
    Button btnCerrarSesion;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_principal);

        btnCerrarSesion =(Button) findViewById(R.id.btnCerrarSesion);
        btnReservar =(ImageView) findViewById(R.id.btnReservar);
        mAuth=FirebaseAuth.getInstance();

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PPrincipal.this,PLogin.class));
                finish();
            }
        });

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PPrincipal.this,PEspecialidades.class));
                finish();
            }
        });

    }
}