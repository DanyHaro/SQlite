package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.db.DbContactos;

public class NuevoActivity2 extends AppCompatActivity {

    EditText txtnombre, txttelefono, txtemail;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo2);

        txtnombre = findViewById(R.id.txt_nombre);
        txttelefono = findViewById(R.id.txt_telefono);
        txtemail = findViewById(R.id.txt_email);
        btnsave = findViewById(R.id.btn_savechange);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //enviamos por parametro el contexto
                DbContactos dbcontactos = new DbContactos(NuevoActivity2.this);
                long id = dbcontactos.insertarContactos(txtnombre.getText().toString(),txttelefono.getText().toString(),txtemail.getText().toString());
                if (id > 0) {
                    Toast.makeText(NuevoActivity2.this, "REGISTRO GUARDADO !", Toast.LENGTH_LONG).show();
                    clearInputs();
                }else{
                    Toast.makeText(NuevoActivity2.this, "ERROR #200 !", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    //Metodo para limpiar los inputs despues que se ha guardado los datos

    private void clearInputs(){
        txtnombre.setText("");
        txttelefono.setText("");
        txtemail.setText("");

    }
}