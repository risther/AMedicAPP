package com.example.amedicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class PLogin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvcorre,tvcontraseña;
    Button btnEnviar;
    Spinner spinnerT;
    private  String email="";
    private  String password="";

    private FirebaseAuth mAutn;
    DatabaseReference databaseReference;
    String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_login);

        tvcontraseña=(TextView) findViewById(R.id.tvContraseña);
        tvcorre=(TextView) findViewById(R.id.tvUsuario  );
        btnEnviar=(Button) findViewById(R.id.btnEnviar);
        mAutn=FirebaseAuth.getInstance();
        spinnerT=(Spinner) findViewById(R.id.spinner);
        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipoUsuario,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tipo=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=tvcorre.getText().toString();
                password=tvcontraseña.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()){
                    Toast.makeText(PLogin.this,"LLEGA 1"+ tipo,Toast.LENGTH_SHORT).show();

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


                    if (tipo.equals("Usuario")) {


                            startActivity(new Intent(PLogin.this,PPrincipal.class));
                            finish();

                    }
                    if (tipo.equals("Especialista")) {

                            startActivity(new Intent(PLogin.this,PprincipalE.class));
                            finish();

                    }
                    if (tipo.equals("Administrador")) {
                        startActivity(new Intent(PLogin.this,PRegistrarEspecialista.class));
                        finish();
                    }
                }else {
                    Toast.makeText(PLogin.this,"No se pudo inciar sesion",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*Boolean n;
    private Boolean validarUsuario() {
        Toast.makeText(PLogin.this,"2",Toast.LENGTH_SHORT).show();
        databaseReference.child("EUsuarioCliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Toast.makeText(PLogin.this,"3",Toast.LENGTH_SHORT).show();
                for (DataSnapshot objSnpshot :snapshot.getChildren()){
                    EUsuarioCliente us= objSnpshot.getValue(EUsuarioCliente.class);
                    Toast.makeText(PLogin.this,"4",Toast.LENGTH_SHORT).show();
                    if (us.getTipoUsuario()=="cliente"){
                        n=true;
                    }
                    Toast.makeText(PLogin.this,"Llego al if",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return n;
    }*/

    @Override
    protected void onStart(){
        super.onStart();
        if (mAutn.getCurrentUser()!=null){
            startActivity(new Intent(PLogin.this,PPrincipal.class));
            finish();
        }
    }

  /*  private Boolean validarEspecialista() {

        databaseReference.child("EUsuarioEspecialista").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot objSnpshot :snapshot.getChildren()){
                    EUsuarioeEspecialista us= objSnpshot.getValue(EUsuarioeEspecialista.class);

                    if (us.getTipoUsuarioE()=="especialista"){
                        n=true;
                    }
                    Toast.makeText(PLogin.this,"Llego al if",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return n;
    }*/

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}