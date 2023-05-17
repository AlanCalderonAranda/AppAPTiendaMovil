package com.example.appaptiendamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;


import java.util.ArrayList;


public class CarritoActivity extends AppCompatActivity implements View.OnClickListener {
    public ListView lvCarrito;
    public TextView tvPrecioNeto,tvTotal,tvIva;
    private Bundle reciboCarrito = new Bundle();
    private ArrayList<String> carrito;
    public Button btnCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        lvCarrito = (ListView) findViewById(R.id.lvCarrito);
        tvPrecioNeto = findViewById(R.id.tvPrecioNeto);
        tvTotal = findViewById(R.id.tvTotal);
        tvIva = findViewById(R.id.tvIva);
        btnCompra = findViewById(R.id.btnCompra);

        reciboCarrito = getIntent().getExtras();
        carrito = reciboCarrito.getStringArrayList("carrito");
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,carrito);
        lvCarrito.setAdapter(adapter);

        tvPrecioNeto.setText(PrecioNeto());
        tvIva.setText(String.valueOf(Double.parseDouble(tvPrecioNeto.getText().toString()) * .16) );
        tvTotal.setText(String.valueOf(  (Double.parseDouble(tvPrecioNeto.getText().toString())) + 99 + Double.parseDouble(tvIva.getText().toString()) ));
        btnCompra.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnCompra.getId()){
            System.out.println("BOTON");
            mostrarConfirmacion();
        }
    }

    private String PrecioNeto() {
        int neto = 0;
        for (int i = 0; i < carrito.size(); i++) {
            if (carrito.get(i).equalsIgnoreCase("Gel Fijador"))
                neto += neto + 500;
            else
                if (carrito.get(i).equalsIgnoreCase("Beauty Creations - Bronceador Hot Mama"))
                    neto += neto + 400;
                else
                    if (carrito.get(i).equalsIgnoreCase("Brow Tame Setting Gel - Gel Fijador De Cejas"))
                        neto += neto + 300;
                    else
                        if (carrito.get(i).equalsIgnoreCase("Flawless Stay Powder"))
                            neto += neto + 300;
                        else
                            neto += neto + 900;
        }
        return neto+"";
    }
    private void mostrarConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("Compra realizada con exito\nSe regresara al inicio");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(CarritoActivity.this,InicioActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
    }
}