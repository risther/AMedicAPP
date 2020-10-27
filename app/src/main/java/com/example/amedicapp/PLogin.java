package com.example.amedicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PLogin extends AppCompatActivity {
    TextView tvcorre,tvcontraseña;
    Button btnEnviar;

    private  String email="";
    private  String password="";

    private FirebaseAuth mAutn;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_login);

        tvcontraseña=(TextView) findViewById(R.id.tvContraseña);
        tvcorre=(TextView) findViewById(R.id.tvUsuario  );
        btnEnviar=(Button) findViewById(R.id.btnEnviar);
        mAutn=FirebaseAuth.getInstance();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=tvcorre.getText().toString();
                password=tvcontraseña.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(PLogin.this,"Complete ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser(){
        mAutn.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(PLogin.this,PPrincipal.class));
                    finish();

                   // tipoUsuario();

                }else {
                    Toast.makeText(PLogin.this,"No se pudo inciar sesion",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void tipoUsuario() {

        databaseReference.child("EUsuarioCliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot objSnpshot :snapshot.getChildren()){
                    EUsuarioCliente us= objSnpshot.getValue(EUsuarioCliente.class);
                    String tipoUsusari=us.getTipoUsuario();

                    if (tipoUsusari.equals("cliente")){
                        startActivity(new Intent(PLogin.this,PPrincipal.class));
                        finish();
                    }else{
                        databaseReference.child("EUsuarioEspecialista").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                for (DataSnapshot objSnpshot :snapshot.getChildren()){
                                    EUsuarioCliente us= objSnpshot.getValue(EUsuarioCliente.class);
                                    String tipoUsusari=us.getTipoUsuario();

                                    if (tipoUsusari.equals("especialista")){
                                        startActivity(new Intent(PLogin.this,PEspecialista.class));
                                        finish();
                                    }else{

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (mAutn.getCurrentUser()!=null){
            startActivity(new Intent(PLogin.this,PPrincipal.class));
            finish();
        }
    }
}