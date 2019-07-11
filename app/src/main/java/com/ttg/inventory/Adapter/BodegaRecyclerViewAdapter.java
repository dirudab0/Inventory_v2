package com.ttg.inventory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ttg.inventory.BodegaActivity;
import com.ttg.inventory.Model.Bodega;
import com.ttg.inventory.R;

import java.util.List;

public class BodegaRecyclerViewAdapter extends RecyclerView.Adapter<BodegaRecyclerViewAdapter.ViewHolder> {

    private List<Bodega> bodegaList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public BodegaRecyclerViewAdapter(List<Bodega> bodegaList, Context context, FirebaseFirestore firestoreDB) {
        this.bodegaList = bodegaList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @Override
    public BodegaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bodega, parent, false);

        return new BodegaRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BodegaRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPosition = position;
        final Bodega bodega = bodegaList.get(itemPosition);

        holder.editnombre.setText(bodega.getNombre());
        holder.editdireccion.setText(bodega.getDireccion());
        holder.editcorreo.setText(bodega.getCorreo());
        holder.edittelefono.setText(bodega.getTelefono());





        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBodega(bodega);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBodega(bodega.getUid(), itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bodegaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView editnombre;
        TextView editdireccion;
        TextView editcorreo;
        TextView edittelefono;
        TextView editiadicional;
        ImageView edit;
        ImageView delete;



        ViewHolder(View view) {
            super(view);
            editnombre=view.findViewById(R.id.editNombre);
            editdireccion=view.findViewById(R.id.editDireccion);
            editcorreo=view.findViewById(R.id.editCorreo);
            edittelefono=view.findViewById(R.id.editTelefono);
            editiadicional=view.findViewById(R.id.editIAdicional);

            edit = view.findViewById(R.id.ivEdit);
            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateBodega(Bodega bodega) {
        Intent intent = new Intent(context, BodegaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("UpdateBodegaId", bodega.getUid());
        intent.putExtra("UpdateBodegaNombre", bodega.getNombre());
        intent.putExtra("UpdateBodegaDireccion", bodega.getDireccion());
        intent.putExtra("UpdateBodegaCorreo", bodega.getCorreo());
        intent.putExtra("UpdateBodegaTelefono", bodega.getTelefono());
        intent.putExtra("UpdateBodegaIAdicional", bodega.getIadicional());

        context.startActivity(intent);
    }

    private void deleteBodega(String id, final int position) {
        firestoreDB.collection("bodega")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        bodegaList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, bodegaList.size());
                        Toast.makeText(context, "Bodega has been deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

