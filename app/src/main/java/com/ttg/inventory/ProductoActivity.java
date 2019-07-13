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
import com.ttg.inventory.Model.Producto;

import java.util.List;
import java.util.Map;

import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class ProductoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ProductoActivity";


    private ImageButton imgscaner;
    private MVBarcodeScanner.ScanningMode modo_Escaneo;
    private int CODE_SCAN = 1;


    TextView editnombre;
    TextView editcodigo;
    TextView editcreserva;
    TextView editvcompra;
    TextView editcantidad;
    TextView editvventa;
    TextView editobservacion;

    Button btAdd;

    private FirebaseFirestore firestoreDB;
    String id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        UI();

        editnombre = findViewById(R.id.editNombre);
        editcodigo = findViewById(R.id.editCodigo);
        editcreserva = findViewById(R.id.editCreserva);
        editvcompra = findViewById(R.id.editVcompra);
        editcantidad = findViewById(R.id.editCantidad);
        editvventa = findViewById(R.id.editVventa);
        editobservacion = findViewById(R.id.editObservacion);
        btAdd = findViewById(R.id.btAdd);


        firestoreDB = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            id = bundle.getString("UpdateProductoId");

            editnombre.setText(bundle.getString("UpdateProductoNombre"));
            editcodigo.setText(bundle.getString("UpdateProductoCodigo"));
            editcreserva.setText(bundle.getString("UpdateProductoCreserva"));
            editvcompra.setText(bundle.getString("UpdateProductoVcompra"));
            editcantidad.setText(bundle.getString("UpdateProductoCantidad"));
            editvventa.setText(bundle.getString("UpdateProductoVventa"));
            editobservacion.setText(bundle.getString("UpdateProductoObservacion"));

        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = editnombre.getText().toString();
                String codigo = editcodigo.getText().toString();
                String creserva = editcreserva.getText().toString();
                String vcompra = editvcompra.getText().toString();
                String cantidad = editcantidad.getText().toString();
                String vventa = editvventa.getText().toString();
                String observacion = editobservacion.getText().toString();


                if (nombre.length() > 0) {
                    if (id.length() > 0) {
                        updateProducto(nombre, codigo, creserva, vcompra, cantidad, vventa, observacion);
                    } else {
                        addProducto(nombre, codigo, creserva, vcompra, cantidad, vventa, observacion);
                    }
                }

                finish();
            }
        });
    }

    private void updateProducto(String nombre, String codigo, String creserva, String vcompra, String cantidad, String vventa, String observacion) {
        Map<String, Object> producto = (new Producto(nombre, codigo, creserva, vcompra, cantidad, vventa, observacion)).toMap();

        firestoreDB.collection("producto")
                .document(id)
                .set(producto)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVogetIdid) {
                        Log.e(TAG, "Producto document update successful!");
                        Toast.makeText(getApplicationContext(), "Producto has been updated!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Producto document", e);
                        Toast.makeText(getApplicationContext(), "Producto could not be updated!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addProducto(String nombre, String codigo, String creserva, String vcompra, String cantidad, String vventa, String observacion) {
        Map<String, Object> producto = new Producto(nombre, codigo, creserva, vcompra, cantidad, vventa, observacion).toMap();

        firestoreDB.collection("producto")
                .add(producto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "Producto has been added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Producto document", e);
                        Toast.makeText(getApplicationContext(), "Producto could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void UI() {
        imgscaner = findViewById(R.id.imgscaner);

        editcodigo = (EditText) findViewById(R.id.editCodigo);

        imgscaner.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgscaner) {
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
}


