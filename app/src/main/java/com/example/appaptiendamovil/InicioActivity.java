package com.example.appaptiendamovil;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView ivTienda,ivSoporte,ivChat,ivWeb;
    public TextView tvNombrePersona,tvSoporte;
    public Button btnLogout;
    int REQUEST_CODE = 200;

    private Bundle reciboUsuario = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        tvNombrePersona = findViewById(R.id.tvNombrePersona);
        ivTienda = findViewById(R.id.ivTienda);
        ivSoporte = findViewById(R.id.ivSoporte);
        ivChat = findViewById(R.id.ivChat);
        ivWeb = findViewById(R.id.ivWeb);
        btnLogout = findViewById(R.id.btnLogout);

        reciboUsuario = getIntent().getExtras();
        tvNombrePersona.setText(reciboUsuario.getString("usuario"));

        ivTienda.setOnClickListener(this);
        ivSoporte.setOnClickListener(this);
        ivWeb.setOnClickListener(this);
        ivChat.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivTienda:{
                Intent intent = new Intent(InicioActivity.this,TiendaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ivSoporte:{
                //Antes que nada pedimos el permiso
                int permisoLLamada = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
                if(permisoLLamada == PackageManager.PERMISSION_GRANTED){
                    //SI SI TENEMOS PERMISO ENTONCES
                    Intent intentLLamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+52 1 55 8326 8419"));
                    startActivity(intentLLamada);
                }else{//SI NO LO PEDIMOS
                    Toast.makeText(this, "SE REALIZARA UNA LLAMADA A NUESTRA CLINICA", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE);
                }
                break;
            }
            case R.id.ivWeb:{
                String urlWeb = "https://beautycreationscosmetics.com.mx/";
                Uri linkWeb = Uri.parse(urlWeb);
                Intent intento = new Intent(new Intent(Intent.ACTION_VIEW,linkWeb));
                startActivity(intento);
                break;
            }
            case R.id.btnLogout:{
                Intent intent = new Intent(InicioActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        if(view.getId() == ivChat.getId()){
                String urlChat = "https://wa.link/77c82h";
                Uri linkChat = Uri.parse(urlChat);
                Intent intent2 = new Intent(new Intent(Intent.ACTION_VIEW,linkChat));
                startActivity(intent2);
        }
    }

}