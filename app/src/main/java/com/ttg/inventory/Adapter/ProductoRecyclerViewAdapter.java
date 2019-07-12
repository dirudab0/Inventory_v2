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
import com.ttg.inventory.Model.Producto;
import com.ttg.inventory.ProductoActivity;
import com.ttg.inventory.R;

import java.util.List;

public class ProductoRecyclerViewAdapter  extends RecyclerView.Adapter<ProductoRecyclerViewAdapter.ViewHolder> {


    private List<Producto> productoList;
    private Context context;
    private FirebaseFirestore firestoreDB;

    public ProductoRecyclerViewAdapter(List<Producto> productoList, Context context, FirebaseFirestore firestoreDB) {
            this.productoList = productoList;
            this.context = context;
            this.firestoreDB = firestoreDB;
    }

    @Override
    public ProductoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

            return new ProductoRecyclerViewAdapter.ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(ProductoRecyclerViewAdapter.ViewHolder holder, int position) {

        final int itemPosition = position;
        final Producto producto = productoList.get(itemPosition);

        holder.editnombre.setText(producto.getNombre());
        holder.editcodigo.setText(producto.getCodigo());
        holder.editvventa.setText(producto.getVventa());
        holder.editcantidad.setText(producto.getCantidad());

        holder.edit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                updateProducto(producto);

            }

        });

        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                deleteProducto(producto.getUid(), itemPosition);

            }

        });
    }



    @Override
    public int getItemCount() {
            return productoList.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView editnombre;
        TextView editcodigo;
        TextView editcreserva;
        TextView editvcompra;
        TextView editcantidad;
        TextView editvventa;
        TextView editobservacion;

        ImageView edit;
        ImageView delete;



        ViewHolder(View view) {
            super(view);

            editnombre=view.findViewById(R.id.editNombre);
            editcodigo=view.findViewById(R.id.editCodigo);
            editcreserva=view.findViewById(R.id.editCreserva);
            editvcompra=view.findViewById(R.id.editVcompra);
            editcantidad=view.findViewById(R.id.editCantidad);
            editvventa=view.findViewById(R.id.editVventa);
            editobservacion=view.findViewById(R.id.editObservacion);

            edit = view.findViewById(R.id.ivEdit);
            delete = view.findViewById(R.id.ivDelete);
        }
    }


    private void updateProducto(Producto producto) {
        Intent intent = new Intent(context, ProductoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.putExtra("UpdateProductoId", producto.getUid());
            intent.putExtra("UpdateProductoNombre", producto.getNombre());
            intent.putExtra("UpdateProductoCodigo", producto.getCodigo());
            intent.putExtra("UpdateProductoCreserva", producto.getCreserva());
            intent.putExtra("UpdateProductoVcompra", producto.getVcompra());
            intent.putExtra("UpdateProductoCantidad", producto.getCantidad());
            intent.putExtra("UpdateProductoVventa", producto.getVventa());
            intent.putExtra("UpdateProductoObservacion", producto.getObservacion());

            context.startActivity(intent);

    }

    private void deleteProducto(String id, final int position) {
            firestoreDB.collection("producto")
                    .document(id)
                    .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            productoList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, productoList.size());
                            Toast.makeText(context, "Producto has been deleted!", Toast.LENGTH_SHORT).show();
                        }
                    });

    }
}

