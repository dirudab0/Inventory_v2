package com.ttg.inventory;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ttg.inventory.Model.Venta;

import java.util.List;
import java.util.Map;

import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class VentaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "VentaActivity";

    private ImageButton imgscanerV;
    private MVBarcodeScanner.ScanningMode modo_Escaneo;
    private int CODE_SCAN = 1;

    TextView editcodigo;
    TextView editnombre;
    TextView editcliente;
    TextView editfpago;
    TextView editcantidad;
    TextView editvalor;
    TextView editdescripcion;
    Button btAdd;

    private FirebaseFirestore firestoreDB;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        UI();

        editcodigo=findViewById(R.id.editCodigo);
        editnombre=findViewById(R.id.editNombre);
        editcliente=findViewById(R.id.editCliente);
        editfpago=findViewById(R.id.editFPago);
        editcantidad=findViewById(R.id.editCantidad);
        editvalor=findViewById(R.id.editValor);
        editdescripcion=findViewById(R.id.editDescripcion);
        btAdd = findViewById(R.id.btAdd);

        firestoreDB = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            id = bundle.getString("UpdateVentaId");

            editcodigo.setText(bundle.getString("UpdateVentaCodigo"));
            editnombre.setText(bundle.getString("UpdateVentaNombre"));
            editcliente.setText(bundle.getString("UpdateVentaCliente"));
            editfpago.setText(bundle.getString("UpdateVentaFpago"));
            editcantidad.setText(bundle.getString("UpdateVentaCantidad"));
            editvalor.setText(bundle.getString("UpdateVentaValor"));
//            editdescripcion.setText(bundle.getString("UpdateVentaDescripcion"));

        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo= editcodigo.getText().toString();
                String nombre= editnombre.getText().toString();
                String cliente= editcliente.getText().toString();
                String cantidad= editcantidad.getText().toString();
                String fpago= editfpago.getText().toString();
                String valor= editvalor.getText().toString();
                String descripcion= editdescripcion.getText().toString();


                if (nombre.length() > 0) {
                    if (id.length() > 0) {
                        updateVenta(id,  codigo,  nombre,  cliente,  fpago,  cantidad,  valor,  descripcion);
                    } else {
                        addVenta(codigo,  nombre,  cliente,  fpago,  cantidad,  valor,  descripcion);
                    }
                }

                finish();
            }
        });
    }

    private void updateVenta(String uid, String codigo, String nombre, String cliente, String fpago, String cantidad, String valor, String descripcion) {
        Map<String, Object> venta = (new Venta( uid,  codigo,  nombre,  cliente,  fpago,  cantidad,  valor,  descripcion)).toMap();

        firestoreDB.collection("venta")
                .document(id)
                .set(venta)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVogetIdid) {
                        Log.e(TAG, "Venta document update successful!");
                        Toast.makeText(getApplicationContext(), "Venta has been updated!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Venta document", e);
                        Toast.makeText(getApplicationContext(), "Venta could not be updated!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addVenta(String codigo, String nombre, String cliente, String fpago, String cantidad, String valor, String descripcion) {
        Map<String, Object> venta = new Venta(codigo,  nombre,  cliente,  fpago,  cantidad,  valor,  descripcion).toMap();

        firestoreDB.collection("venta")
                .add(venta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Venta has been added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Venta document", e);
                        Toast.makeText(getApplicationContext(), "Venta could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void UI() {
        imgscanerV = findViewById(R.id.imgscanerV);

        editcodigo = (EditText) findViewById(R.id.editCodigo);

        imgscanerV.setOnClickListener(this);

    }

    public void onClick(View view) {
        if (view.getId() == R.id.imgscanerV) {
            modo_Escaneo = MVBarcodeScanner.ScanningMode.SINGLE_AUTO;
        }

        new MVBarcodeScanner.Builder().setScanningMode(modo_Escaneo).setFormats(Barcode.ALL_FORMATS)
                .build()
                .launchScanner(this, CODE_SCAN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_SCAN) {
            if (resultCode == RESULT_OK && data != null
                    && data.getExtras() != null) {

                if (data.getExtras().containsKey(MVBarcodeScanner.BarcodeObject)) {
                    Barcode mBarcode = data.getParcelableExtra(MVBarcodeScanner.BarcodeObject);
                    editcodigo.setText(mBarcode.rawValue);
                } else if (data.getExtras().containsKey(MVBarcodeScanner.BarcodeObjects)) {
                    List<Barcode> mBarcodes = data.getParcelableArrayListExtra(MVBarcodeScanner.BarcodeObjects);
                    StringBuilder s = new StringBuilder();
                    for (Barcode b:mBarcodes){
                        s.append(b.rawValue + "\n");
                    }
                    editcodigo.setText(s.toString());
                }
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}