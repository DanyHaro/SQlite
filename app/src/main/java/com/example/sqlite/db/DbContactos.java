package com.example.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.sqlite.Entity.Contacto;

import java.util.ArrayList;

public class DbContactos extends DbHelper{

    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    ////////// INSERTAR REGISTROS
    public long insertarContactos(String nombre, String telefono, String correo_electronico){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //Funcion para registrar datos

            ContentValues values = new ContentValues();
            //primer parametro es el nombre de la columna
            //segundo parametro es el la variable por parametro
            values.put("NOMBRE", nombre);
            values.put("TELEFONO", telefono);
            values.put("CORREO_ELECTRONICO", correo_electronico);

            //primer parametro es el nombre del la tabla al cual vamos a insertar
            //table_contactos es el nombre de la tabla. como heredamos de DbHelper, podemos usarla
            //db.insert devuelve el id del registro insertado
            id = db.insert(table_contactos, null, values);
        }catch(Exception e){
            e.toString();
        }
        return id;
    }

    /////LISTAR LOS CONTACTOS REGISTRADOS

    public ArrayList<Contacto> listarContactos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contacto> listacontactos = new ArrayList<>();

        Contacto contacts = null;
        Cursor cursocontactos = null;

        
        cursocontactos = db.rawQuery(" SELECT * FROM " + table_contactos,null );
        if (cursocontactos.moveToFirst()) {
            do {
                contacts = new Contacto();
                //posicion ()
                contacts.setId(cursocontactos.getInt(0));
                contacts.setNombre(cursocontactos.getString(1));
                contacts.setTelefono(cursocontactos.getString(2));
                contacts.setCorreo_electronico(cursocontactos.getString(3));

                listacontactos.add(contacts);

            } while (cursocontactos.moveToNext());
        }

        cursocontactos.close();

        return listacontactos;
    }


    ////// MOSTRAR PARA MODIFICAR

    public Contacto modificarContactos(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        Contacto contacts = null;
        Cursor cursocontactos = null;


        cursocontactos = db.rawQuery(" SELECT * FROM " + table_contactos + " WHERE ID = " + id + " LIMIT 1 ",null );
        if (cursocontactos.moveToFirst()) {
                contacts = new Contacto();
                //posicion ()
                contacts.setId(cursocontactos.getInt(0));
                contacts.setNombre(cursocontactos.getString(1));
                contacts.setTelefono(cursocontactos.getString(2));
                contacts.setCorreo_electronico(cursocontactos.getString(3));
        }

        cursocontactos.close();

        return contacts;
    }




    ////////// ACTUALIZAR LOS DATOS

    public Boolean actualizarDatos(int id, String nombre, String telefono, String correo_electronico){


        Boolean verificado = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // OTRA MANERA DE INSERTAR DATOS

            db.execSQL(" UPDATE " + table_contactos + " SET NOMBRE = ' " + nombre + " ', TELEFONO = ' "+ telefono +" ', CORREO_ELECTRONICO = ' " + correo_electronico + " ' WHERE ID = ' " + id + " ' ");
            verificado = true;
        }catch(Exception e){
            e.toString();
            verificado = false;
        }  finally {
            //para cerrar la conexion de la BD
            db.close();
        }
        return verificado;
    }




    ///////////ELIMINAR CONTACTO

    public Boolean eliminarContacto(int id){


        Boolean verificado = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            // OTRA MANERA DE INSERTAR DATOS

            db.execSQL("DELETE FROM " + table_contactos + " WHERE ID = '" + id + "'");
            verificado = true;
        }catch(Exception e){
            e.toString();
            verificado = false;
        }  finally {
            //para cerrar la conexion de la BD
            db.close();
        }
        return verificado;
    }

}
