package com.ttg.inventory.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ttg.inventory.Model.Bodega;
import com.ttg.inventory.R;

import java.util.Map;

public class BodegaActivity extends AppCompatActivity {

    private static final String TAG = "Bodega";

    TextView editnombre;
    TextView editdireccion;
    TextView editcorreo;
    TextView edittelefono;
    TextView editiadicional;
    Button btAdd;

    private FirebaseFirestore firestoreDB;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodega);

        editnombre=findViewById(R.id.editNombre);
        editdireccion=findViewById(R.id.editDireccion);
        editcorreo=findViewById(R.id.editCorreo);
        edittelefono=findViewById(R.id.editTelefono);
        editiadicional=findViewById(R.id.editIAdicional);
        btAdd = findViewById(R.id.btAdd);

        firestoreDB = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            id = bundle.getString("UpdateBodegaId");

            editnombre.setText(bundle.getString("UpdateBodegaNombre"));
            editdireccion.setText(bundle.getString("UpdateBodegaDireccion"));
            editcorreo.setText(bundle.getString("UpdateBodegaCorreo"));
            edittelefono.setText(bundle.getString("UpdateBodegaTelefono"));
            editiadicional.setText(bundle.getString("UpdateBodegaIAdicional"));

        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre= editnombre.getText().toString();
                String direccion= editdireccion.getText().toString();
                String correo= editcorreo.getText().toString();
                String telefono= edittelefono.getText().toString();
                String iadicional= editiadicional.getText().toString();


                if (nombre.length() > 0) {
                    if (id.length() > 0) {
                        updateBodega(id, nombre,  direccion,  correo,  telefono,  iadicional);
                    } else {
                        addBodega(nombre,  direccion,  correo,  telefono,  iadicional);
                    }
                }

                finish();
            }
        });
    }

    private void updateBodega(String uid, String nombre, String direccion, String correo, String telefono, String iadicional) {
        Map<String, Object> bodega = (new Bodega(id, nombre,  direccion,  correo,  telefono,  iadicional)).toMap();

        firestoreDB.collection("bodega")
                .document(id)
                .set(bodega)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVogetIdid) {
                        Log.e(TAG, "Bodega document update successful!");
                        Toast.makeText(getApplicationContext(), "Bodega has been updated!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Bodega document", e);
                        Toast.makeText(getApplicationContext(), "Bodega could not be updated!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addBodega(String nombre, String direccion, String correo, String telefono, String iadicional) {
        Map<String, Object> bodega = new Bodega( nombre,  direccion,  correo,  telefono,  iadicional).toMap();

        firestoreDB.collection("bodega")
                .add(bodega)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Bodega has been added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Bodega document", e);
                        Toast.makeText(getApplicationContext(), "Bodega could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}