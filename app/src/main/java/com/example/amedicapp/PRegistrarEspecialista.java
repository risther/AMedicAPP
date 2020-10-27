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

public class PRegistrarEspecialista extends AppCompatActivity {

    TextView tvnombres,tvapellidos,tvEspecialidad,tvHorainicio,tvHoraFinal,tvContrseña,tvCorreo;
    Button btnRegistrarE;
    String nombres,apellidos,Especialidad,Horainicio,HoraFinal,Contrseña,Correo;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_registrar_especialista);


        tvnombres=(TextView) findViewById(R.id.tvNombre);
        tvapellidos=(TextView) findViewById(R.id.tvApellidos);
        tvEspecialidad=(TextView) findViewById(R.id.tvEpecialidad);
        tvHorainicio=(TextView) findViewById(R.id.tvHorainicio);
        tvHoraFinal=(TextView) findViewById(R.id.tvHoraFinal);
        tvCorreo=(TextView) findViewById(R.id.tvCorreo);
        tvContrseña=(TextView) findViewById(R.id.tvContraseña);
        btnRegistrarE=(Button)  findViewById(R.id.btnRegistrarE);
        mAuth= FirebaseAuth.getInstance();
        incializarFirebase();

        btnRegistrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombres=tvnombres.getText().toString();
                apellidos=tvapellidos.getText().toString();
                Especialidad=tvEspecialidad.getText().toString();
                Horainicio=tvHorainicio.getText().toString();
                HoraFinal=tvHoraFinal.getText().toString();
                Contrseña=tvContrseña.getText().toString();
                Correo=tvCorreo.getText().toString();
                if (tvEspecialidad.equals("") || tvnombres.equals("") || tvapellidos.equals("")|| tvHorainicio.equals("")|| tvHoraFinal.equals("")|| tvContrseña.equals("")|| tvCorreo.equals("")){
                    validacion();
                }else {

                    EUsuarioeEspecialista objUsuario= new EUsuarioeEspecialista();
                    objUsuario.setId(UUID.randomUUID().toString());
                    objUsuario.setNombres(nombres);
                    objUsuario.setApellidos(apellidos);
                    objUsuario.setHorarioInicio(Horainicio);
                    objUsuario.setHorarioFinal(HoraFinal);
                    objUsuario.setEspecialidad(Especialidad);
                    objUsuario.setContraseña(Contrseña);
                    objUsuario.setCorreo(Correo);
                    objUsuario.setTipoUsuarioE("Especialista");
                    databaseReference.child("EUsuarioEspecialista").child(objUsuario.getId()).setValue(objUsuario);

                    Toast.makeText(PRegistrarEspecialista.this,"Agregado",Toast.LENGTH_SHORT).show();
                    //registrarUsuario();


                }
            }
        });

        }



    private void  registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(Correo,Contrseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PRegistrarEspecialista.this,"se pudo registrar",Toast.LENGTH_SHORT);
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
                }else {
                    Toast.makeText(PRegistrarEspecialista.this,"No se pudo registrar",Toast.LENGTH_SHORT);
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
        String espe=tvEspecialidad.getText().toString();
        String horIni=tvHorainicio.getText().toString();
        String horaFI=tvHoraFinal.getText().toString();
        String correo=tvCorreo.getText().toString();
        String contraseña=tvContrseña.getText().toString();
        if (nombre.equals("")){
            tvnombres.setError("Required");
        }
        if (apelldidos.equals("")){
            tvnombres.setError("Required");
        }
        if (espe.equals("")){
            tvnombres.setError("Required");
        }
        if (horIni.equals("")){
            tvnombres.setError("Required");
        }
        if (horaFI.equals("")){
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