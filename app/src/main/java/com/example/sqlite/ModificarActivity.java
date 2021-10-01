package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.Entity.Contacto;
import com.example.sqlite.db.DbContactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModificarActivity extends AppCompatActivity {

    EditText txtnombre,txttelefono,txtemail;
    Button btnsaveChange;
    Contacto contact;
    int id = 0;
    Boolean verificado = false;
    FloatingActionButton btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtnombre = findViewById(R.id.txt_nombre);
        txttelefono = findViewById(R.id.txt_telefono);
        txtemail = findViewById(R.id.txt_email);
        btnsaveChange = findViewById(R.id.btn_savechange);
        btndelete = findViewById(R.id.btneliminar);

        if (savedInstanceState == null) {
            System.out.println(savedInstanceState);
            System.out.println("CONCHEDTUAMRE EXTRA");
            //El extra viene a ser el ID enviado en el Adapter
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                id= Integer.parseInt(null);
            }else{
                //La llave especificado en el Adapter para el extra es "ID"
                System.out.println(extra);
                id = extra.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
            System.out.println(id);
        }

        //INSTANCIAMOS DE DbContactos para usar el metodo modificar
        DbContactos dbContactos = new DbContactos(ModificarActivity.this);
        contact = dbContactos.modificarContactos(id);
        System.out.println(contact + "CONTACT");

        if (contact != null) {
            txtnombre.setText(contact.getNombre());
            txttelefono.setText(contact.getTelefono());
            txtemail.setText(contact.getCorreo_electronico());

            //Ocultar el boton Save
            //btnsaveChange.setVisibility(View.INVISIBLE);

            //Al momento de dar click no me habra el teclado del emulador
            txtnombre.setInputType(InputType.TYPE_NULL);
        }
        btnsaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtnombre.getText().toString().equals("") && !txttelefono.getText().toString().equals("") && !txtemail.getText().toString().equals("")) {
                    verificado = dbContactos.actualizarDatos(id, txtnombre.getText().toString(),txttelefono.getText().toString(),txtemail.getText().toString());

                    if (verificado == true) {
                        Toast.makeText(ModificarActivity.this,"REGISTRO ACTUALIZADO !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ModificarActivity.this, MainActivity.class);
                        intent.putExtra("ID",id);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ModificarActivity.this,"ERROR !!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(ModificarActivity.this,"TODOS LOS CAMPOS SON OBLIGATORIOS !", Toast.LENGTH_LONG).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ModificarActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbContactos.eliminarContacto(id);
                        //eliminarContacto retorna un Boolean
                        if (dbContactos.eliminarContacto(id)) {
                            Toast.makeText(ModificarActivity.this,"CONTACTO ELIMINADO !", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ModificarActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        System.out.println("ID ES : " + id);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ModificarActivity.this,"ERROR AL ELIMINAR ! !", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });
    }
}