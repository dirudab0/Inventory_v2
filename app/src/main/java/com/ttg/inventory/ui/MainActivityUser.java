package com.ttg.inventory.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.ttg.inventory.Adapter.UserRecyclerViewAdapter;

import com.ttg.inventory.Model.User;
import com.ttg.inventory.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

public class MainActivityUser extends AppCompatActivity {

    private static final String TAG = "Usuarios";

    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter mAdapter;


    private FirebaseFirestore firestoreDB;
    private ListenerRegistration firestoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        recyclerView = findViewById(R.id.rvUserList);
        firestoreDB = FirebaseFirestore.getInstance();

        loadUserList();

        firestoreListener = firestoreDB.collection("User")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e(TAG, "Listen failed!", e);
                            return;
                        }

                        List<User> userList = new ArrayList<>();

                        for (DocumentSnapshot doc : documentSnapshots) {
                            User user = doc.toObject(User.class);
                            user.setUid(doc.getId());
                            userList.add(user);
                        }

                        mAdapter = new UserRecyclerViewAdapter(userList, getApplicationContext(), firestoreDB);
                        recyclerView.setAdapter(mAdapter);
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        firestoreListener.remove();
    }

    private void loadUserList() {
        firestoreDB.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> userList = new ArrayList<>();

                            for (DocumentSnapshot doc : task.getResult()) {
                                User user = doc.toObject(User.class);
                                user.setUid(doc.getId());
                                userList.add(user);
                            }

                            mAdapter = new UserRecyclerViewAdapter(userList, getApplicationContext(), firestoreDB);
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
                Intent intent = new Intent(this, BodegaActivity.class);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
