package com.ttg.inventory.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.ttg.inventory.R;

import java.util.HashMap;
import java.util.Map;


public class ConfiguracionInicial extends AppCompatActivity {

    private static final String TAG = "DocSnippets";



    FloatingActionButton fabAddicionar;

    public static final String NOMBRE_KEY = "nombre";
    public static final String CONTACTO_KEY = "contacto";
    public static final String CORREO_KEY = "correo";
    public static final String TELEFONO_KEY = "telefono";
    public static final String ADICIONAL_KEY = "adicional";

    private DocumentReference mDocRef= FirebaseFirestore.getInstance().document("IInicial/0");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_inicial);
        //setup();

        fabAddicionar=(FloatingActionButton)findViewById(R.id.fabAddicionar);

        fabAddicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIInicial(v);
            }
        });

    }


    public void setup() {
        // [START get_firestore_instance]
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // [END get_firestore_instance]

        // [START set_firestore_settings]
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        // [END set_firestore_settings]
    }

    private void saveIInicial(View view){

        EditText nombrev =(EditText)findViewById(R.id.editNombre);
        EditText contactov =(EditText)findViewById(R.id.editContacto);
        EditText correov =(EditText)findViewById(R.id.editCorreo);
        EditText telefonov =(EditText)findViewById(R.id.editTelefono);
        EditText adicionalv =(EditText)findViewById(R.id.editAdicional);

        String nombret = nombrev.getText().toString();
        String contactot = contactov.getText().toString();
        String correot = correov.getText().toString();
        String telefonot = telefonov.getText().toString();
        String adicionalt = adicionalv.getText().toString();

        if (nombret.isEmpty()||contactot.isEmpty()){ return; }

        Map<String,Object> dataToSave=new HashMap<String, Object>();
        dataToSave.put(NOMBRE_KEY,nombret);
        dataToSave.put(CONTACTO_KEY,contactot);
        dataToSave.put(CORREO_KEY,correot);
        dataToSave.put(TELEFONO_KEY,telefonot);
        dataToSave.put(ADICIONAL_KEY,adicionalt);

        mDocRef.set (dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                //Log.d(TAG, "Documento Almacenado");
                Toast.makeText(ConfiguracionInicial.this,"Datos Almacenados",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Log.w(TAG, "No Almacenado", e);
                Toast.makeText(ConfiguracionInicial.this,"Datos No Almacenados " + e ,Toast.LENGTH_LONG).show();
            }
        });
    }

}
