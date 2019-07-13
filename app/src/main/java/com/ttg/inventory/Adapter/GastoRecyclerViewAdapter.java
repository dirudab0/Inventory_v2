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
import com.ttg.inventory.ui.GastoActivity;
import com.ttg.inventory.Model.Gasto;
import com.ttg.inventory.R;

import java.util.List;

public class GastoRecyclerViewAdapter extends RecyclerView.Adapter<GastoRecyclerViewAdapter.ViewHolder> {

    private List<Gasto> gastoList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public GastoRecyclerViewAdapter(List<Gasto> gastoList, Context context, FirebaseFirestore firestoreDB) {
        this.gastoList = gastoList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @Override
    public GastoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasto, parent, false);

        return new GastoRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GastoRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPosition = position;
        final Gasto gastos = gastoList.get(itemPosition);

        holder.editnombre.setText(gastos.getNombre());
        holder.editfecha.setText(gastos.getFecha());
        holder.edittgasto.setText(gastos.getTgasto());
        holder.editdescripcion.setText(gastos.getDescripcion());





        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGasto(gastos);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGasto(gastos.getUid(), itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gastoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        TextView editnombre;
        TextView editfecha;
        TextView edittgasto;
        TextView editdescripcion;
        ImageView edit;
        ImageView delete;



        ViewHolder(View view) {
            super(view);
            editnombre=view.findViewById(R.id.editNombre);
            editfecha=view.findViewById(R.id.editFecha);
            edittgasto=view.findViewById(R.id.editTGasto);
            editdescripcion=view.findViewById(R.id.editDescripcion);

            edit = view.findViewById(R.id.ivEdit);
            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateGasto(Gasto gastos) {
        Intent intent = new Intent(context, GastoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("UpdateGastoId", gastos.getUid());
        intent.putExtra("UpdateGastoNombre", gastos.getNombre());
        intent.putExtra("UpdateGastoFecha", gastos.getFecha());
        intent.putExtra("UpdateGastoTGasto", gastos.getTgasto());
        intent.putExtra("UpdateGastoFpago", gastos.getFpago());
        intent.putExtra("UpdateGastoDescripcion", gastos.getDescripcion());

        context.startActivity(intent);
    }

    private void deleteGasto(String id, final int position) {
        firestoreDB.collection("gasto")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        gastoList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, gastoList.size());
                        Toast.makeText(context, "Gasto has been deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

