package com.example.amedicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PRegistrarCliente extends AppCompatActivity {

    TextView tvdni,tvnombres,tvapellidos,tvtelefono,tvcorreo,tvContraseña;
    Button btnRegistrarCli;
    String dni,nombres,apellidos,telefono,correo,contraseña;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_registrar);

        tvdni=(TextView) findViewById(R.id.tvDNI);
        tvnombres=(TextView) findViewById(R.id.tvNombre);
        tvapellidos=(TextView) findViewById(R.id.tvApellidos);
        tvtelefono=(TextView) findViewById(R.id.tvTelefono);
        tvcorreo=(TextView) findViewById(R.id.tvCorreo);
        tvContraseña=(TextView) findViewById(R.id.tvContraseña);
        btnRegistrarCli=(Button) findViewById(R.id.btnRegistrarCliente);
        mAuth= FirebaseAuth.getInstance();
        incializarFirebase();

        btnRegistrarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nombres=tvnombres.getText().toString();
                 apellidos=tvapellidos.getText().toString();
                 dni=tvdni.getText().toString();
                 telefono=tvtelefono.getText().toString();
                 correo=tvcorreo.getText().toString();
                 contraseña=tvContraseña.getText().toString();

                if (tvdni.equals("") || tvnombres.equals("") || tvapellidos.equals("")|| tvtelefono.equals("")|| tvcorreo.equals("")|| tvContraseña.equals("")){
                    validacion();
                }else {

                    EUsuarioCliente objUsuario= new EUsuarioCliente();
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
                    registrarUsuario();


                }
            }
        });

    }

    private void  registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PRegistrarCliente.this,"se pudo registrar",Toast.LENGTH_SHORT);
                    /*Map<String, Object> map= new HashMap<>();
                    map.put("name",name);
                    map.put("correo",correo);
                    map.put("contraseña",contraseña);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabasse.child("User").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                             if (task.isSuccessful()){
                                 startActivity(new Intent(MainActivity.this,actividad.class));
                                 finish();
                             }else {
                                 Toast.makeText(MainActivity.this,"No se pudo registrar correctamente",Toast.LENGTH_SHORT);
                             }
                        }
                    });*/
                    mAuth.signOut();
                }else {
                    Toast.makeText(PRegistrarCliente.this,"No se pudo registrar",Toast.LENGTH_SHORT);
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

    private  void  validacion(){
        String nombre=tvnombres.getText().toString();
        String apelldidos=tvapellidos.getText().toString();
        String dni=tvdni.getText().toString();
        String telefono=tvtelefono.getText().toString();
        String correo=tvcorreo.getText().toString();
        String contraseña=tvContraseña.getText().toString();
        if (nombre.equals("")){
            tvnombres.setError("Required");
        }
        if (apelldidos.equals("")){
            tvnombres.setError("Required");
        }
        if (dni.equals("")){
            tvnombres.setError("Required");
        }
        if (telefono.equals("")){
            tvnombres.setError("Required");
        }
        if (correo.equals("")){
            tvnombres.setError("Required");
        }
        if (contraseña.equals("")){
            tvnombres.setError("Required");
        }
    }
}