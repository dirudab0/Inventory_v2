package com.ttg.inventory.ui.producto;


import androidx.appcompat.app.AppCompatActivity;

public class ProductoActivity extends AppCompatActivity {

 /*   EditText Codigo, Nombre, Reserva, Pventa, Cantidad, PCompra,  Observacion;
    Spinner Unidad, Categoria;


    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference() ;

    public TextView showJsonData ;

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    FloatingActionButton fabAddicionar;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        Codigo = findViewById(R.id.editNombre);
        Nombre = findViewById(R.id.editNombre);
        Reserva = findViewById(R.id.editCorreo);
        Pventa = findViewById(R.id.editTelefono);
        Cantidad = findViewById(R.id.editCantidad);
        PCompra = findViewById(R.id.editPCompra);
        Observacion = findViewById(R.id.editAdicional);

        Unidad = (Spinner)findViewById(R.id.spinnerUnidad);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Unidad,android.R.layout.simple_spinner_item);
        Unidad.setAdapter(adapter);

        Categoria = (Spinner)findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Categoria,android.R.layout.simple_spinner_item);
        Categoria.setAdapter(adapter1);

        inicializarFirebase();

        fabAddicionar=(FloatingActionButton)findViewById(R.id.fabAddicionar);

        fabAddicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = Codigo.getText().toString();
                String nombre = Nombre.getText().toString();
                String reserva = Reserva.getText().toString();
                String pventa = Pventa.getText().toString();
                String cantidad = Cantidad.getText().toString();
                String pcompra = PCompra.getText().toString();
                String observacion = Observacion.getText().toString();


                if (Codigo.equals("")||Nombre.equals("")||Reserva.equals("")||Pventa.equals("")||Cantidad.equals("")||PCompra.equals("")){
                    Validacion();
                }
                else {
                    Producto producto= new Producto();
                    producto.setUid(UUID.randomUUID().toString());
                    producto.setCodigo(codigo);
                    producto.setNombre(nombre);
                    producto.setReserva(reserva);
                    producto.setPventa(pventa);
                    producto.setCantidad(cantidad);
                    producto.setPcompra(pcompra);
                    producto.setObservacion(observacion);

                    databaseReference.child("producto").child(producto.getUid()).setValue(producto);
                    Toast.makeText(v.getContext(),"Producto Agregado",Toast.LENGTH_LONG).show();

                    ClearCaja();
                }
                
            }
       });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private void ClearCaja() {
        Codigo.setText("");
        Nombre.setText("");
        Reserva.setText("");
        Pventa.setText("");
        Cantidad.setText("");
        PCompra.setText("");
        Observacion.setText("");
    }

    private void Validacion() {
        String codigo = Codigo.getText().toString();
        String nombre = Nombre.getText().toString();
        String reserva = Reserva.getText().toString();
        String pventa = Pventa.getText().toString();
        String cantidad = Cantidad.getText().toString();
        String pcompra = PCompra.getText().toString();


        if (codigo.equals("")||nombre.equals("")||reserva.equals("")||pventa.equals("")||cantidad.equals("")||pcompra.equals("")){
            Codigo.setError("Requerido");
        }


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
    */

}




