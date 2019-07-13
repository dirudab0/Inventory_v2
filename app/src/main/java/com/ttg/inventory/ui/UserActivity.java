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
import com.ttg.inventory.Model.User;
import com.ttg.inventory.R;

import java.util.Map;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "Bodega";

    TextView editfullname;
    TextView editusername;
    TextView editpassword;
    TextView editcpassword;

    Button btAdd;

    private FirebaseFirestore firestoreDB;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodega);

        editfullname=findViewById(R.id.RGfullName);
        editusername=findViewById(R.id.RGuserName);
        editpassword=findViewById(R.id.RGpassword);
        editcpassword=findViewById(R.id.RGconfirPassword);

        btAdd = findViewById(R.id.btRGRegistrar);

        firestoreDB = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            id = bundle.getString("UpdateUserId");

            editfullname.setText(bundle.getString("UpdateUserFullname"));
            editusername.setText(bundle.getString("UpdateUserUsername"));
            editpassword.setText(bundle.getString("UpdateUserpassword"));


        }

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname= editfullname.getText().toString();
                String username= editusername.getText().toString();
                String password= editpassword.getText().toString();
                String cpassword= editcpassword.getText().toString();


                if (fullname.length() > 0) {
                    if (id.length() > 0) {
                        updateUser(id, fullname,  username,  password);
                    } else {
                        addUser(fullname,  username,  password);
                    }
                }

                finish();
            }
        });
    }

    private void updateUser(String uid, String fullname, String username, String password) {
        Map<String, Object> user = (new User(id, fullname,  username,  password)).toMap();

        firestoreDB.collection("User")
                .document(id)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVogetIdid) {
                        Log.e(TAG, "Usuario document update successful!");
                        Toast.makeText(getApplicationContext(), "Bodega has been updated!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Usuario adding Bodega document", e);
                        Toast.makeText(getApplicationContext(), "Bodega could not be updated!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addUser(String fullname, String username, String password) {
        Map<String, Object> user = new User( fullname,  username,  password).toMap();

        firestoreDB.collection("User")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(getApplicationContext(), "User has been added!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error adding Bodega document", e);
                        Toast.makeText(getApplicationContext(), "User could not be added!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}