package com.example.ingdaniel.personamaterial;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ING DANIEL on 20/05/2017.
 */

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.PersonaViewHolder> {

    private ArrayList<Persona> personas;

    private OnPersonaClickListener clickListener;

    public AdaptadorPersona(ArrayList<Persona> personas, OnPersonaClickListener clickListener){
        this.personas=personas;
        this.clickListener=clickListener;
    }

    @Override
    public AdaptadorPersona.PersonaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //la forma
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_persona,parent,false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorPersona.PersonaViewHolder holder, int position) {
        //datos
        final Persona p = personas.get(position);
        holder.foto.setImageResource(Integer.parseInt(p.getUrlfoto()));
        holder.cedula.setText(p.getCedula());
        holder.nombre.setText(p.getNombre());
        holder.apellido.setText(p.getApellido());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.OnPersonaClick(p);
            }
        });

    }


    @Override
    public int getItemCount() {
        return personas.size();
    }


    public static class PersonaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView cedula;
        private TextView nombre;
        private TextView apellido;
        private View view;

        public PersonaViewHolder(View itemView) {
            super(itemView);
            view =(itemView);
            foto =(ImageView)itemView.findViewById(R.id.foto);
            cedula =(TextView)itemView.findViewById(R.id.txtCedulap);
            nombre=(TextView)itemView.findViewById(R.id.txtNombrep);
            apellido=(TextView)itemView.findViewById(R.id.txtApellidop);
        }
    }


    public interface OnPersonaClickListener{
        void OnPersonaClick(Persona p);
    }

}


