package com.example.amedicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PRegistrarEspecialidades extends AppCompatActivity {


    TextView tvnombresEspecialista,tvnombresEspecialidad,tvdescripcion;
    Button btnRegistrar;
    String especialista,especialidad,descripcion;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_registrar_especialidades);

        tvnombresEspecialista=(TextView) findViewById(R.id.tvREspecialista);
        tvnombresEspecialidad=(TextView) findViewById(R.id.tvRNombreEspecialidad);
        tvdescripcion=(TextView) findViewById(R.id.tvRDescripcion);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrarEspecialidad);
        mAuth= FirebaseAuth.getInstance();
        incializarFirebase();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                especialista=tvnombresEspecialista.getText().toString();
                especialidad=tvnombresEspecialidad.getText().toString();
                descripcion=tvdescripcion.getText().toString();

                if (especialista.equals("") || especialidad.equals("") || descripcion.equals("")){
                 //   validacion();
                }else {

                 /*   EUsuarioCliente objUsuario= new EUsuarioCliente();
                    objUsuario.setId(UUID.randomUUID().toString());
                    objUsuario.setTipoUsuario("cliente");
                    objUsuario.setDni(dni);
                    objUsuario.setNombres(nombres);
                    objUsuario.setApellidos(apellidos);
                    objUsuario.setNumero(telefono);
                    objUsuario.setContraseña(contraseña);
                    objUsuario.setCorreo(correo);
                    databaseReference.child("EUsuarioCliente").child(objUsuario.getId()).setValue(objUsuario);

                    Toast.makeText(PRegistrarCliente.this,"Agregado",Toast.LENGTH_SHORT).show();
                    registrarUsuario();*/


                }
            }
        });

    }



    private void incializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }
/*
    private  void  validacion(){
        String nombreEspecialidad=tvnombresEspecialidad.getText().toString();
        String nombreEspecialista=tvnombresEspecialista.getText().toString();
        String descripcion=tvdescripsion.getText().toString();

        if (nombreEspecialidad.equals("")){
            tvnombreEspecialidad.setError("Required");
        }
        if (nombreEspecialista.equals("")){
            tvnombreEspecialista.setError("Required");
        }
        if (descripcion.equals("")){
            tvdescripsion.setError("Required");
        }

    }*/
}