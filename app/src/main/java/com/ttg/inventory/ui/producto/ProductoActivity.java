package com.ttg.inventory.ui.producto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ttg.inventory.R;
import com.ttg.inventory.SimpleScannerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    String JSON_STRING = "{\"Producto\":{\"Codigo\":\"123456\",\"Nombre\":Leche}}";
    public EditText  ACodigo, ANombre, ACantidadReserva, APrecioVenta, ACantidadProducto, APrecioCompra,  AObservacion;
    public Spinner AUnidad, ACategoria;

    public TextView  showJsonData ;

    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    FloatingActionButton fabAddicionar;

    EditText etCodigo;
    Spinner Unidad;
    Spinner Categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        etCodigo = findViewById(R.id.editCodigo);

        Unidad = (Spinner)findViewById(R.id.spinnerUnidad);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Unidad,android.R.layout.simple_spinner_item);
        Unidad.setAdapter(adapter);

        Categoria = (Spinner)findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Categoria,android.R.layout.simple_spinner_item);
        Categoria.setAdapter(adapter1);

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

    public   void   init ( )   {
        ACodigo=(EditText)findViewById(R.id.editCodigo );
        ANombre=(EditText )   findViewById ( R . id . editNombre ) ;
        ACantidadReserva   =   ( EditText )   findViewById ( R . id . editCantidadReserva ) ;
        APrecioVenta   =   ( EditText )   findViewById ( R . id . editPrecioVenta ) ;
        ACantidadProducto   =   ( EditText )   findViewById ( R . id . editCantidadProducto ) ;
        APrecioCompra   =   ( EditText )   findViewById ( R . id . editPecioCompra ) ;
        AUnidad   =   ( Spinner )   findViewById ( R . id . spinnerUnidad ) ;
        ACategoria   =   ( Spinner )   findViewById ( R . id . spinnerCategoria ) ;
        AObservacion   =   ( EditText )   findViewById ( R . id . editObservacion ) ;

    }
    private   void   ConvertToJsonData ( )   {
        // get texts from all edittexts
        String getCodigo=ACodigo.getText().toString();
        String getNombre=ANombre.getText().toString();
        String getCantidadReserva=ACantidadReserva.getText().toString();
        String getPrecioVenta=APrecioVenta.getText().toString();
        String getCantidadProducto=ACantidadProducto.getText().toString();
        String getPrecioCompra=APrecioCompra.getText().toString();
        String getUnidad=AUnidad.getContext().toString();
        String getCategoria=ACategoria.getContext().toString();
        String getObservacion=AObservacion.getText().toString();


        // Check if all fields are filled or not
        if   ( getCodigo.length()!=0 && ! getCodigo.equals("")
                && getNombre.length()!=0 && ! getNombre.equals("")
                && getCantidadReserva.length()!=0 && ! getCantidadReserva.equals("")
                && getPrecioVenta.length()!=0 && ! getPrecioVenta.equals("")
                && getCantidadProducto.length()!=0 && ! getCantidadProducto.equals("")
                && getPrecioCompra.length()!=0 && ! getPrecioCompra.equals("")
                && getUnidad.length()!=0 && ! getUnidad.equals("")
                && getCategoria.length()!=0 && ! getCategoria.equals("")) {
            try   {

                JSONObject  dataObject   =   new   JSONObject ( ) ; // JsonObject for
                // storing edittext
                // fields
                dataObject.put("Codigo",getCodigo);
                dataObject.put("Nombre",getNombre);
                dataObject.put("Cantidad Reserva",getCantidadReserva);
                dataObject.put("Precio Venta",getPrecioVenta);
                dataObject.put("Cantidad Producto",getCantidadProducto);
                dataObject.put("Precio Compra",getPrecioCompra);
                dataObject.put("Unidad",getUnidad);
                dataObject.put("Categoria",getCategoria);
                dataObject.put("Observacion",getObservacion);


                JSONArray  mainArray   =   new   JSONArray ( ) ; // JsonArray to put the
                // dataObject into
                // jsonArray, since
                // there can be more
                // data if data is saved
                // in database so the
                // jsonObject data will
                // be put into jsonArray
                mainArray . put ( dataObject ) ;

                // Finally put the jsonArray into another jsonObject with "data"
                // keyvlaue or any other keyvalue
                JSONObject  mainObject   =   new   JSONObject ( ) ;   // This object is
                // optional you can
                // convert json data
                // according to your
                // need
                mainObject . put ( "data" ,   mainArray ) ;

                // Now, set the converted json data to textview to see the
                // actual format
                showJsonData . setText ( "Converted JSON data is nn"
                        +   mainObject . toString ( ) ) ;
            }   catch   ( Exception   e )   {
                e . printStackTrace ( ) ;

                // If any exception occurs then display error
                showJsonData . setText ( "Error in converting data." ) ;
            }

        }
        // If all fileds or one of the filed is empty show toast
        else   {
            Toast . makeText ( ProductoActivity . this ,   "All fields are required." ,
                    Toast . LENGTH_SHORT ) . show ( ) ;
        }

    }
    public class Producto{
        private String Codigo;
        private String Nombre;
        private String CantidadReserva;
        private String PrecioVenta;
        private String CantidadProducto;
        private String PrecioCompra;
        private String Unidad;
        private String Categoria;
        private String Observacion;
        private Producto producto ;

        public Producto() {

        }
        public Producto(String Codigo,String Nombre,String CantidadReserva,String PrecioVenta,String CantidadProducto,String PrecioCompra,String Unidad,String Categoria,String Observacion) {
            this.Codigo=Codigo;
            this.Nombre=Nombre;
            this.CantidadReserva=CantidadReserva;
            this.PrecioVenta=PrecioVenta;
            this.CantidadProducto=CantidadProducto;
            this.PrecioCompra=PrecioCompra;
            this.Unidad=Unidad;
            this.Categoria=Categoria;
            this.Observacion=Observacion;
        }

        public String getCodigo() {
            return Codigo;
        }

        public void setCodigo(String Codigo) {
            this.Codigo = Codigo;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String Nombre) {
            this.Nombre = Nombre;
        }
        public String getCantidadReserva() {
            return CantidadReserva;
        }

        public void setCantidadReserva(String CantidadReserva) {
            this.CantidadReserva = CantidadReserva;
        }
        public String getPrecioVenta() {
            return PrecioVenta;
        }

        public void setPrecioVenta(String PrecioVenta) {
            this.PrecioVenta = PrecioVenta;
        }
        public String getCantidadProducto() {
            return CantidadProducto;
        }

        public void setCantidadProducto(String CantidadProducto) {
            this.CantidadProducto = CantidadProducto;
        }
        public String getPrecioCompra() {
            return PrecioCompra;
        }

        public void setPrecioCompra(String PrecioCompra) {
            this.PrecioCompra = PrecioCompra;
        }
        public String getUnidad() {
            return Unidad;
        }

        public void setUnidad(String Unidad) {
            this.Unidad = Unidad;
        }
        public String getCategoria() {
            return Categoria;
        }

        public void setCategoria(String Categoria) {
            this.Categoria = Categoria;
        }
        public String getObservacion() {
            return Observacion;
        }

        public void setObservacion(String Observacion) {
            this.Observacion = Observacion;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.producto != null) {
                sb.append("\n prodcucto:" + this.producto.toString());
            }
            return sb.toString();

            //return Codigo+","+Nombre + ", " +CantidadReserva + ", " +PrecioVenta + ", " +CantidadProducto + ", " +PrecioCompra + ", " +Unidad + ", " +Categoria + ", " +Observacion;
        }

    }

}




