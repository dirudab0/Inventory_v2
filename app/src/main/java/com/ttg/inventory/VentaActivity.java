package com.ttg.inventory;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ttg.inventory.Model.Venta;

import java.util.Map;

public class VentaActivity extends AppCompatActivity {

    private static final String TAG = "VentaActivity";

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

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


    public void launchSimpleActivity(View v) {
        launchActivity(SimpleScannerActivity.class);
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

}


//-----------------------------------------

/*{
    String JSON_STRING = "{\"Producto\":{\"Codigo\":\"123456\",\"Nombre\":Leche}}";
    public EditText ACodigo, ANombre, ACantidadReserva, APrecioVenta, ACantidadProducto, APrecioCompra,  AObservacion;
    public Spinner AUnidad, ACategoria;

    public TextView showJsonData ;

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    FloatingActionButton fabAddicionar;

    EditText etCodigo;
    Spinner Unidad;
    Spinner Categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        fabAddicionar=(FloatingActionButton)findViewById(R.id.fabAddicionar);

        fabAddicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void launchSimpleActivity(View v) {

        launchActivity(SimpleScannerActivity.class);
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZXING_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }
}*/


/*
public class MainActivity extends AppCompatActivity {
    TextView barcodeInfo;
    SurfaceView cameraView;
    CameraSource cameraSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.txtContent);

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.CODE_128);

        //QR_CODE) .build();
        cameraSource = new CameraSource .Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480) .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try { cameraSource.start(cameraView.getHolder()); }
                catch (IOException ie) { Log.e("CAMERA SOURCE", ie.getMessage()); } }
                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }
                @Override public void surfaceDestroyed(SurfaceHolder holder) { cameraSource.stop(); } });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override import android.support.v7.app.AppCompatActivity; import android.os.Bundle; import android.util.Log; import android.util.SparseArray; import android.view.SurfaceHolder; import android.view.SurfaceView; import android.view.View; import android.widget.Button; import android.widget.TextView; import java.io.IOException; import com.google.android.gms.vision.CameraSource; import com.google.android.gms.vision.Detector; import com.google.android.gms.vision.Frame; import com.google.android.gms.vision.barcode.Barcode; import com.google.android.gms.vision.barcode.BarcodeDetector; public class MainActivity extends AppCompatActivity { TextView barcodeInfo; SurfaceView cameraView; CameraSource cameraSource; @Override protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main); cameraView = (SurfaceView) findViewById(R.id.camera_view); barcodeInfo = (TextView) findViewById(R.id.txtContent); BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this) .setBarcodeFormats(Barcode.CODE_128)//QR_CODE) .build(); cameraSource = new CameraSource .Builder(this, barcodeDetector) .setRequestedPreviewSize(640, 480) .build(); cameraView.getHolder().addCallback(new SurfaceHolder.Callback() { @Override public void surfaceCreated(SurfaceHolder holder) { try { cameraSource.start(cameraView.getHolder()); } catch (IOException ie) { Log.e("CAMERA SOURCE", ie.getMessage()); } } @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { } @Override public void surfaceDestroyed(SurfaceHolder holder) { cameraSource.stop(); } }); barcodeDetector.setProcessor(new Detector.Processor<Barcode>() { @Override public void release() { } @Override public void receiveDetections(Detector.Detections<Barcode> detections) { final SparseArray<Barcode> barcodes = detections.getDetectedItems(); if (barcodes.size() != 0) { barcodeInfo.post(new Runnable() { // Use the post method of the TextView public void run() { barcodeInfo.setText( // Update the TextView barcodes.valueAt(0).displayValue ); } }); } } }); } }
            public void release() { }
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {
                        // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(
                                    // Update the TextView
                                    barcodes.valueAt(0).displayValue );
                        }
                    });
                }
            }
        });
    }
}*/