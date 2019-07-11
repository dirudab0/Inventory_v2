package com.ttg.inventory.Adapter;

public class VentaRecyclerViewAdapter  {

   /* private List<Venta> ventaList;
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

        holder.title.setText(venta.getTitle());
        holder.content.setText(venta.getContent());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVenta(venta);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVenta(venta.getId(), itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ventaList.size();
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

    private void updateVenta(Venta venta) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("UpdateNoteId", venta.getId());
        intent.putExtra("UpdateNoteTitle", venta.getTitle());
        intent.putExtra("UpdateNoteContent", venta.getContent());
        context.startActivity(intent);
    }

    private void deleteVenta(String id, final int position) {
        firestoreDB.collection("notes")
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
    */
}

