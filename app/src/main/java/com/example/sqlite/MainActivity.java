package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.sqlite.Entity.Contacto;
import com.example.sqlite.adaptadores.ListaContactosAdapter;
import com.example.sqlite.db.DbContactos;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //VARIABLES
    RecyclerView listaContacto;
    ArrayList<Contacto> listaArray;
    //Button btncrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btncrear = findViewById(R.id.btncrear);

        listaContacto = findViewById(R.id.listar);
        listaContacto.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        DbContactos database_contactos = new DbContactos(MainActivity.this);
        listaArray = new ArrayList<>();

        //Instanciamos del adaptaer
        //Enviamos el resultado de la consulta SQL por parametro en su constructor
        ListaContactosAdapter adapter = new ListaContactosAdapter(database_contactos.listarContactos());
        //En el RecylerView se mostrara la informacion estructurada y ordenada
        listaContacto.setAdapter(adapter);


        /*btncrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DbHelper dbhelper = new DbHelper(MainActivity.this);

                //Indicamos que vamos a escribir en nuestra base de datos
                SQLiteDatabase database = dbhelper.getWritableDatabase();

                //Si es diferente de null significa que se creo correctamente la Base de datos
                if (database != null) {
                    Toast.makeText(MainActivity.this,"BASE DE DATOS CREADA !" + dbhelper.table_contactos+dbhelper.database_nombre, Toast.LENGTH_LONG).show();
                    database.close();
                }else{
                    Toast.makeText(MainActivity.this,"ERROR #1 AL CREAR LA BASE DE DATOS !!", Toast.LENGTH_LONG).show();
                }
            }
        });*/


    }

    //Con este metodo mostramos el Activity de Menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_nuevo:
                nuevoregistros();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void nuevoregistros(){
        //Para pasar de una vista a otra.

        Intent intent = new Intent(MainActivity.this, NuevoActivity2.class);
        startActivity(intent);
    }
}