package com.ttg.inventory.ui.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.ttg.inventory.R;
import com.ttg.inventory.data.controller.MySingleton;
import com.ttg.inventory.data.controller.SessionHandler;
import com.ttg.inventory.ui.dashboard.DashboardActivity;
import com.ttg.inventory.ui.registrar.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "fullName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMPTY = "";

    private EditText txtLGuserName;
    private EditText txtLGpassword;
    private String username;
    private String password;
    private ProgressDialog pDialog;

    private String login_url = "http://riolab.com.co/inventory/login.php";
    private SessionHandler session;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionHandler(getApplicationContext());
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        if(session.isLoggedIn()){

        }

        setContentView(R.layout.activity_login);

        txtLGuserName = findViewById(R.id.LGuserName);
        txtLGpassword = findViewById(R.id.LGpassword);
        final Button loginButton = findViewById(R.id.btLGLogin);
        final TextView registrarText = findViewById(R.id.btLGRegistrar);
        final ProgressBar loadingProgressBar = findViewById(R.id.LGloading);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieve the data entered in the edit texts
                username = txtLGuserName.getText().toString().toLowerCase().trim();
                password = txtLGpassword.getText().toString().trim();
                if (validateInputs()) {
                    login();
                }
            }
        });

        registrarText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg;
                intentReg = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intentReg);
            }
        });
    }

    private void loadDashboard() {
        Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(i);
        finish();

    }

    /**
     * Display Progress bar while Logging in
     */

    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Logging In.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void login() {
        displayLoader();

        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, login_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();

                        try {
                            //Check if user got logged in successfully

                            if (response.getInt(KEY_STATUS) == 0) {
                                session.loginUser(username,response.getString(KEY_FULL_NAME));
                                loadDashboard();

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
        loadDashboard();
    }

    /**
     * Validates inputs and shows error if any
     * @return
     */
    private boolean validateInputs() {
        if(KEY_EMPTY.equals(username)){
            txtLGuserName.setError("Username cannot be empty");
            txtLGuserName.requestFocus();
            return false;
        }
        if(KEY_EMPTY.equals(password)){
            txtLGpassword.setError("Password cannot be empty");
            txtLGpassword.requestFocus();
            return false;
        }
        return true;
    }

}
