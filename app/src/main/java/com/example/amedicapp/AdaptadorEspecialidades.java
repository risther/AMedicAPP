package com.example.amedicapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorEspecialidades extends RecyclerView.Adapter<AdaptadorEspecialidades.PersonaViewHolder> {

    List<EEspecialidades> especialidad;
    Context context;

    public AdaptadorEspecialidades(List<EEspecialidades> personas, Context context) {
        this.especialidad = personas;
        this.context = context;
    }
    @NonNull
    @Override
    public AdaptadorEspecialidades.PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_p_especialidades,parent, false);

        PersonaViewHolder personaViewHolder = new PersonaViewHolder(view);

        return personaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEspecialidades.PersonaViewHolder holder, int position) {
        holder.nombreEspeciliadad.setText(especialidad.get(position).nombreEspecilidad);
        holder.nombreEspecilista.setText(especialidad.get(position).nombreEspecilista);
        holder.descripcion.setText(especialidad.get(position).descripcion);
      /*  Drawable original = context
                .getResources()
                .getDrawable(especialidad.get(position).foto,null);*/


        /*Bitmap originalBitmap =((BitmapDrawable)original).getBitmap();
        RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(context.getResources(),originalBitmap);
        roundedBitmapDrawable.setCircular(true);

        holder.fotoPersona.setImageDrawable(roundedBitmapDrawable);*/
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public  static  class  PersonaViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView nombreEspeciliadad;
        TextView nombreEspecilista;
        TextView descripcion;
        ImageView fotoPersona;

        public  PersonaViewHolder(@NonNull View itemView){
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cv);
            nombreEspeciliadad = (TextView) itemView.findViewById(R.id.textNombreEspecialidad);
            descripcion = (TextView) itemView.findViewById(R.id.textNombreEspecialista);
            nombreEspecilista = (TextView) itemView.findViewById(R.id.txtdescripcion);
            fotoPersona = (ImageView) itemView.findViewById(R.id.fotoEspecilidad);
        }


    }
}
