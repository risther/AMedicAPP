package com.example.amedicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PEspecialidades extends AppCompatActivity {


    private List<EEspecialidades> especialidades;



    private  void initializarDatos(){
        especialidades = new ArrayList<>();





        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    EEspecialidades especialidades2=snapshot.getValue(EEspecialidades.class);
                    especialidades.add(especialidades2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_especialidades);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

       /* rating =(RatingBar)findViewById(R.id.ratingBar3);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this,"Usted Califico la pelicula con "+rating,Toast.LENGTH_LONG);
            }
        });*/


        initializarDatos();
        AdaptadorEspecialidades rvAdapter = new AdaptadorEspecialidades(especialidades,getApplicationContext());
        recyclerView.setAdapter(rvAdapter);
    }
}