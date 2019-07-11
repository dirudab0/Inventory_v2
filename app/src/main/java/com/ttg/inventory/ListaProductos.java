package com.ttg.inventory;


import androidx.appcompat.app.AppCompatActivity;


public class ListaProductos extends AppCompatActivity {

  /*  private static final String TAG = "DocSnippets";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //private FirebaseFirestore db =FirebaseFirestore.getInstance();
    private CollectionReference productobookRef= db.collection("productos");



    RecyclerView rv;
    List<Producto> productos;

    private ProductoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference referencia = database.getReference("productos");

        //setUpRecycerView();

        FloatingActionButton fabAddicionar = (FloatingActionButton) findViewById(R.id.fabAddicionar);

        fabAddicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(), ProductoActivity.class);
                startActivity(i);

            }
        });

    }

    /*private void setUpRecycerView() {

        Query query=productobookRef.orderBy("nombre", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Producto> opciones=new FirestoreRecyclerOptions.Builder<Producto>()
                .setQuery(query, Producto.class)
                .build();

         adapter = new ProductoAdapter(opciones);

        RecyclerView recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    */

    //----------------------------------------------------
/*
    public void getAllUsers() {
        // [START get_all_users]
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        // [END get_all_users]
    }

    //----------------------------------------------------

    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

*/

}