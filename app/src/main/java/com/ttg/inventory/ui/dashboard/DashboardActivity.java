package com.ttg.inventory.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.ttg.inventory.R;
import com.ttg.inventory.ui.bodega.BodegaActivity;
import com.ttg.inventory.ui.informes.InformesActivity;
import com.ttg.inventory.ui.producto.ProductoActivity;
import com.ttg.inventory.ui.user.UserActivity;


public class DashboardActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private CardView cardBodega;
    private CardView cardProducto;
    private CardView cardUsuarios;
    private CardView cardInformes;
    private CardView cardToma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardBodega = findViewById(R.id.bodega);

        cardBodega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBog;
                intentBog = new Intent(DashboardActivity.this, BodegaActivity.class);
                DashboardActivity.this.startActivity(intentBog);
            }
        });

        cardProducto = findViewById(R.id.producto);

        cardProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPro;
                intentPro = new Intent(DashboardActivity.this, ProductoActivity.class);
                DashboardActivity.this.startActivity(intentPro);
            }
        });

        cardUsuarios = findViewById(R.id.usuarios);

        cardUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUser;
                intentUser = new Intent(DashboardActivity.this, UserActivity.class);
                DashboardActivity.this.startActivity(intentUser);
            }
        });

        cardInformes = findViewById(R.id.informes);

        cardInformes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInf;
                intentInf = new Intent(DashboardActivity.this, InformesActivity.class);
                DashboardActivity.this.startActivity(intentInf);
            }
        });
    }
}
