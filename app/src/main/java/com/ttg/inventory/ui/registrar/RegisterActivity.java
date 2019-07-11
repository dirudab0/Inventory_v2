package com.ttg.inventory.ui.registrar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ttg.inventory.R;
import com.ttg.inventory.data.controller.MySingleton;
import com.ttg.inventory.data.controller.SessionHandler;
import com.ttg.inventory.ui.dashboard.DashboardActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";
    private EditText txtRGfullName;
    private EditText txtRGuserName;
    private EditText txtRGpassword;
    private EditText txtRGonfirmPassword;
    private String fullName;
    private String username;
    private String password;
    private String confirmPassword;

    private ProgressDialog pDialog;
    private String register_url = "http://riolab.com.co/inventory/register.php";
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        setContentView(R.layout.activity_register);

        txtRGfullName = findViewById(R.id.RGfullName);
        txtRGuserName = findViewById(R.id.RGuserName);
        txtRGpassword = findViewById(R.id.RGpassword);
        txtRGonfirmPassword= findViewById(R.id.RGconfirPassword);


        Button register = findViewById(R.id.btRGRegistrar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                fullName = txtRGfullName.getText().toString().trim();
                username = txtRGuserName.getText().toString().toLowerCase().trim();
                password = txtRGpassword.getText().toString().trim();
                confirmPassword = txtRGonfirmPassword.getText().toString().trim();

                if (validateInputs()) {
                    registerUser();
                }

            }
        });
    }

    /**
     * Display Progress bar while registering
     */
    private void displayLoader() {
        pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setMessage("Signing Up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    /**
     * Launch Dashboard Activity on Successful Sign Up
     */
    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(i);
        finish();

    }

    private void registerUser() {
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_FULL_NAME, fullName);
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                session.loginUser(username,fullName);
                                loadDashboard();

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing
                                txtRGuserName.setError("Username already taken!");
                                txtRGuserName.requestFocus();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if (KEY_EMPTY.equals(fullName)) {
            txtRGfullName.setError("Por favor digitar nombre completo");
            txtRGfullName.requestFocus();
            return false;

        }
        if (KEY_EMPTY.equals(username)) {
            txtRGuserName.setError("Por favor digital su correo electronico");
            txtRGuserName.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(password)) {
            txtRGpassword.setError("Digital password");
            txtRGpassword.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(confirmPassword)) {
            txtRGonfirmPassword.setError("Confirmar Password");
            txtRGonfirmPassword.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            txtRGonfirmPassword.setError("Password no coincide");
            txtRGonfirmPassword.requestFocus();
            return false;
        }

        return true;
    }

}
