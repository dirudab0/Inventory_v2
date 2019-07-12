package com.ttg.inventory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.ttg.inventory.Adapter.ProductoRecyclerViewAdapter;
import com.ttg.inventory.Model.Producto;

import java.util.ArrayList;
import java.util.List;

public class MainActivityProducto extends AppCompatActivity {

    private static final String TAG = "MainActivityProducto";

    private RecyclerView recyclerView;
    private ProductoRecyclerViewAdapter mAdapter;


    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_producto);

        recyclerView = findViewById(R.id.rvProductoList);
        firestoreDB = FirebaseFirestore.getInstance();

        loadProductoList();

        firestoreListener = firestoreDB.collection("producto")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, "Listen failed!", e);
                            return;
                        }

                        List<Producto> productoList = new ArrayList<>();

                        for (DocumentSnapshot doc : documentSnapshots) {
                            Producto producto = doc.toObject(Producto.class);
                            producto.setUid(doc.getId());
                            productoList.add(producto);
                        }

                        mAdapter = new ProductoRecyclerViewAdapter(productoList, getApplicationContext(), firestoreDB);
                        recyclerView.setAdapter(mAdapter);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }

    private void loadProductoList() {
        firestoreDB.collection("producto")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Producto> productoList = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Producto producto = doc.toObject(Producto.class);
                                producto.setUid(doc.getId());
                                productoList.add(producto);
                                Log.d(TAG, producto.getNombre());

                            }

                            mAdapter = new ProductoRecyclerViewAdapter(productoList, getApplicationContext(), firestoreDB);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            if (item.getItemId() == R.id.add) {
                Intent intent = new Intent(this, ProductoActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
