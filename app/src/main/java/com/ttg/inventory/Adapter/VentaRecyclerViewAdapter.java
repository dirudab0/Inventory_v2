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
import com.ttg.inventory.Model.Venta;
import com.ttg.inventory.R;
import com.ttg.inventory.VentaActivity;

import java.util.List;

public class VentaRecyclerViewAdapter  extends RecyclerView.Adapter<VentaRecyclerViewAdapter.ViewHolder> {

    private List<Venta> ventaList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public VentaRecyclerViewAdapter(List<Venta> ventaList, Context context, FirebaseFirestore firestoreDB) {
        this.ventaList = ventaList;
        this.context = context;
        this.firestoreDB = firestoreDB;
    }

    @Override
    public VentaRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venta, parent, false);

        return new VentaRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VentaRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPosition = position;
        final Venta venta = ventaList.get(itemPosition);

        holder.editcodigo.setText(venta.getCodigo());
        holder.editnombre.setText(venta.getNombre());
        holder.editcliente.setText(venta.getCliente());
        holder.editfpago.setText(venta.getFpago());
        //holder.editcantidad.setText(venta.getCantidad());
        holder.editvalor.setText(venta.getValor());
       // holder.editdescripcion.setText(venta.getDescripcion());





        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVenta(venta);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVenta(venta.getUid(), itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ventaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView editcodigo;
        TextView editnombre;
        TextView editcliente;
        TextView editfpago;
        TextView editcantidad;
        TextView editvalor;
        TextView editdescripcion;


        ImageView edit;
        ImageView delete;



        ViewHolder(View view) {
            super(view);

            editcodigo=view.findViewById(R.id.editCodigo);
            editnombre=view.findViewById(R.id.editNombre);
            editcliente=view.findViewById(R.id.editCliente);
            editfpago=view.findViewById(R.id.editFPago);
            editcantidad=view.findViewById(R.id.editCantidad);
            editvalor=view.findViewById(R.id.editValor);
            editdescripcion=view.findViewById(R.id.editDescripcion);

            edit = view.findViewById(R.id.ivEdit);
            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateVenta(Venta venta) {
        Intent intent = new Intent(context, VentaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("UpdateVentaId", venta.getUid());
        intent.putExtra("UpdateVentaCodigo", venta.getCodigo());
        intent.putExtra("UpdateVentaNombre", venta.getNombre());
        intent.putExtra("UpdateVentaCliente", venta.getCliente());
        intent.putExtra("UpdateVentaFpago", venta.getFpago());
        intent.putExtra("UpdateVentaValor", venta.getValor());
        intent.putExtra("UpdateVentaDescripcion", venta.getDescripcion());



        context.startActivity(intent);
    }

    private void deleteVenta(String id, final int position) {
        firestoreDB.collection("venta")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        ventaList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, ventaList.size());
                        Toast.makeText(context, "Venta has been deleted!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
