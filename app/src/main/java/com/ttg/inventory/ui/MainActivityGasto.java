package com.ttg.inventory.ui;

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
import com.ttg.inventory.Adapter.GastoRecyclerViewAdapter;
import com.ttg.inventory.Model.Gasto;
import com.ttg.inventory.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivityGasto extends AppCompatActivity {



    private static final String TAG = "MainActivityGasto";

    private RecyclerView recyclerView;
    private GastoRecyclerViewAdapter mAdapter;


    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gasto);

        recyclerView = findViewById(R.id.rvGastoList);
        firestoreDB = FirebaseFirestore.getInstance();

        loadGastoList();

        firestoreListener = firestoreDB.collection("gasto")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, "Listen failed!", e);
                            return;
                        }

                        List<Gasto> gastoList = new ArrayList<>();

                        for (DocumentSnapshot doc : documentSnapshots) {
                            Gasto gasto = doc.toObject(Gasto.class);
                            gasto.setUid(doc.getId());
                            gastoList.add(gasto);
                        }

                        mAdapter = new GastoRecyclerViewAdapter(gastoList, getApplicationContext(), firestoreDB);
                        recyclerView.setAdapter(mAdapter);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }

    private void loadGastoList() {
        firestoreDB.collection("gasto")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Gasto> gastoList = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                Gasto gasto = doc.toObject(Gasto.class);
                                gasto.setUid(doc.getId());
                                gastoList.add(gasto);
                                Log.d(TAG, gasto.getNombre());

                            }

                            mAdapter = new GastoRecyclerViewAdapter(gastoList, getApplicationContext(), firestoreDB);
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
                Intent intent = new Intent(this, GastoActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
