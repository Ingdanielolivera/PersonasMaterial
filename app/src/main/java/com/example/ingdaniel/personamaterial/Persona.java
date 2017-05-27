package com.example.ingdaniel.personamaterial;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ING DANIEL on 20/05/2017.
 */

public class Persona {
    private String foto;
    private String cedula;
    private String nombre;
    private String apellido;


    public Persona(String foto, String cedula, String nombre, String apellido) {
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public void guardar (Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexión de base de datos en modo escritura
        PersonaSQLiteOpenHelper aux =new PersonaSQLiteOpenHelper(contexto,"DBPersonas",null);
        db = aux.getWritableDatabase();

      /*  //insertar forma 1
        sql ="INSERT INTO Personas Values('"+this.getFoto()+"','"+this.getCedula()+"','"+this.getNombre()+"','"+this.getApellido()+"','"+this.getSexo()+"','"+this.getPasatiempo()+"')";
        db.execSQL(sql);*/

        //insertar forma 2
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getApellido());
        //Inserto en la base de datos
        db.insert("Personas",null, nuevoRegistro);
        //cierro la conexión
        db.close();
    }

    public void eliminar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexión de base de datos en modo escritura
        PersonaSQLiteOpenHelper aux = new PersonaSQLiteOpenHelper(contexto, "DBPersonas", null);
        db = aux.getWritableDatabase();

        //Colsulta sql para borrar
        sql="DELETE FROM Personas where cedula='"+getCedula()+"'";
        //Ejecutar
        db.execSQL(sql);
        //cierro la conexión
        db.close();
    }


    public void modificar (Context contexto) {
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexión de base de datos en modo escritura
        PersonaSQLiteOpenHelper aux = new PersonaSQLiteOpenHelper(contexto, "DBPersonas", null);
        db = aux.getWritableDatabase();

        //consulta para actualizar
        sql ="UPDATE Personas"+ " SET nombre='"+this.getNombre()+"',"+"apellido='"+this.getApellido()+"',"+"'"+ "WHERE cedula='"+this.getCedula()+"'";
        //Ejecutar
        db.execSQL(sql);
        //cierro la conexión
        db.close();
    }


}

