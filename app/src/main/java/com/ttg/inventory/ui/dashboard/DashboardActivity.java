package com.ttg.inventory.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ttg.inventory.ConfiguracionInicial;
import com.ttg.inventory.MainActivityBodega;
import com.ttg.inventory.MainActivityGasto;
import com.ttg.inventory.R;
import com.ttg.inventory.Ventas;
import com.ttg.inventory.ui.informes.InformesActivity;
import com.ttg.inventory.ui.user.UserActivity;



public class DashboardActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private CardView cardBodega;
    private CardView cardProducto;
    private CardView cardUsuarios;
    private CardView cardInformes;
    private CardView cardVenta;
    private CardView cardGastos;
    private CardView cardConfiguracionInicial;
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
                intentBog = new Intent(DashboardActivity.this, MainActivityBodega.class);
                DashboardActivity.this.startActivity(intentBog);
            }
        });

       /* cardProducto = findViewById(R.id.inventario);

        cardProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPro;
                intentPro = new Intent(DashboardActivity.this, City.class);
                DashboardActivity.this.startActivity(intentPro);
            }
        });*/

        cardVenta = findViewById(R.id.ventas);

        cardVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPro;
                intentPro = new Intent(DashboardActivity.this, Ventas.class);
                DashboardActivity.this.startActivity(intentPro);
            }
        });

        cardGastos = findViewById(R.id.gastos);

        cardGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPro;
                intentPro = new Intent(DashboardActivity.this, MainActivityGasto.class);
                DashboardActivity.this.startActivity(intentPro);
            }
        });

        cardConfiguracionInicial = findViewById(R.id.infoInicial);

        cardConfiguracionInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPro;
                intentPro = new Intent(DashboardActivity.this, ConfiguracionInicial.class);
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
