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
import com.ttg.inventory.Model.Gasto;
import com.ttg.inventory.R;

import java.util.Map;

public class GastoActivity extends AppCompatActivity {

    private static final String TAG = "GastoActivity";

    TextView editnombre;
    TextView editfecha;
    TextView edittgasto;
    TextView editfpago;
    TextView editdescripcion;
    Button btAdd;

    private FirebaseFirestore firestoreDB;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        editnombre=findViewById(R.id.editNombre);
        editfecha=findViewById(R.id.editFecha);
        edittgasto=findViewById(R.id.editTGasto);
        editfpago=findViewById(R.id.editFPago);
        editdescripcion=findViewById(R.id.editDescripcion);
        btAdd = findViewById(R.id.btAdd);

        firestoreDB = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            id = bundle.getString("UpdateGastoId");

            editnombre.setText(bundle.getString("UpdateGastoNombre"));
            editfecha.setText(bundle.getString("UpdateGastoFecha"));
            edittgasto.setText(bundle.getString("UpdateGastoTGastos"));
            editfpago.setText(bundle.getString("UpdateGastoFPago"));
            editdescripcion.setText(bundle.getString("UpdateGastoDescripcion"));

        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre= editnombre.getText().toString();
                String fecha= editfecha.getText().toString();
                String tgasto= edittgasto.getText().toString();
                String fpago= editfpago.getText().toString();
                String descripcion= editdescripcion.getText().toString();


                if (nombre.length() > 0) {
                    if (id.length() > 0) {
                        updateGasto(id, nombre,fecha,tgasto,fpago,descripcion);
                    } else {
                        addGasto(nombre,fecha,tgasto,fpago,descripcion);
                    }
                }

                finish();
            }
        });
    }

    private void updateGasto(String uid, String nombre, String fecha, String tgasto, String fpago, String descripcion) {
        Map<String, Object> gasto = (new Gasto(id, nombre,fecha,tgasto,fpago,descripcion)).toMap();

        firestoreDB.collection("gasto")
                .document(id)
                .set(gasto)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVogetIdid) {
                        Log.e(TAG, "Gasto document update successful!");
                        Toast.makeText(getApplicationContext(), "Gasto has been updated!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Gasto document", e);
                        Toast.makeText(getApplicationContext(), "Gasto could not be updated!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addGasto(String nombre, String fecha, String tgasto, String fpago, String descripcion) {
        Map<String, Object> gasto = new Gasto(nombre,fecha,tgasto,fpago,descripcion).toMap();

        firestoreDB.collection("gasto")
                .add(gasto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Gasto has been added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Gasto document", e);
                        Toast.makeText(getApplicationContext(), "Gasto could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
