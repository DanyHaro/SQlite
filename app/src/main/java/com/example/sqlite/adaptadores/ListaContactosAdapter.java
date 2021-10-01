package com.example.sqlite.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.Entity.Contacto;
import com.example.sqlite.ModificarActivity;
import com.example.sqlite.R;
import java.util.ArrayList;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {


    //VARIABLES
    ArrayList<Contacto> listContact;

    public ListaContactosAdapter(ArrayList<Contacto> listContact){
        this.listContact = listContact;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto,null, false);
        return new ContactoViewHolder(vista);
    }

    //ASIGNAR LOS ELEMENTOS A CADA PROPIEDAD (nombre,telefono,email)
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.nombre.setText(listContact.get(position).getNombre());
        holder.telefono.setText(listContact.get(position).getTelefono());
        holder.email.setText(listContact.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,telefono,email;


        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombre_contact);
            telefono = itemView.findViewById(R.id.telef_contact);
            email = itemView.findViewById(R.id.email_contact);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Cuando Seleccione un Contacto lo llevara al ModificarActivity
                    Context context = view.getContext();
                    Intent intent = new Intent(context,ModificarActivity.class);

                    //Para mostrar los datos correspondientes, le enviamos el ID
                    intent.putExtra("ID", listContact.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}