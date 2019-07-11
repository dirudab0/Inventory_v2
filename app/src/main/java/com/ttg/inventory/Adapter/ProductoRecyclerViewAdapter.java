package com.ttg.inventory.Adapter;

/*
public class ProductoRecyclerViewAdapter extends RecyclerView.Adapter<ProductoRecyclerViewAdapter.ViewHolder> {

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

        holder.title.setText(producto.getTitle());
        holder.content.setText(producto.getContent());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProducto(producto);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProducto(producto.getId(), itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView edit;
        ImageView delete;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            content = view.findViewById(R.id.tvContent);

            edit = view.findViewById(R.id.ivEdit);
            delete = view.findViewById(R.id.ivDelete);
        }
    }

    private void updateProducto(Producto producto) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("UpdateNoteId", producto.getId());
        intent.putExtra("UpdateNoteTitle", producto.getTitle());
        intent.putExtra("UpdateNoteContent", producto.getContent());
        context.startActivity(intent);
    }

    private void deleteProducto(String id, final int position) {
        firestoreDB.collection("notes")
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
*/
