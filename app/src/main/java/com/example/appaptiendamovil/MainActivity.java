package com.example.appaptiendamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    public Button btnLogin;
    
    public TextView tvRegistrate;

    private Bundle reciboDatos = new Bundle();
    private ArrayList<String> usuarios = new ArrayList<>();
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llenaUsuarios(usuarios);

        reciboDatos = getIntent().getExtras();
        if(!(reciboDatos == null)){//Si recibo Datos es nulo es que es la primera vez que entro al programa
            //Como no es la primera vez y vengo de registro obtengo los nuevos usuarios registrados
            usuarios = reciboDatos.getStringArrayList("usuarios");
        }
        tvRegistrate = findViewById(R.id.tvRegistrate);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        tvRegistrate.setOnClickListener(this);
    }

    private void llenaUsuarios(ArrayList<String> usuarios) {
            usuarios.add("lore@gmail.com");
            usuarios.add("1234567890");
            usuarios.add("Lorena Iraan");
            usuarios.add("angeles@gmail.com");
            usuarios.add("1234567890");
            usuarios.add("Maria de los Angeles");
            usuarios.add("alan@gmail.com");
            usuarios.add("1234567890");
            usuarios.add("Alan");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin:{
                //LE MANDO LOS USUARIOS EN LA BD
                Bundle mandoDatos = new Bundle();
                mandoDatos.putStringArrayList("usuarios",usuarios);
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.putExtras(mandoDatos);
                startActivity(intent);
                break;
            }
            case R.id.tvRegistrate:{
                Bundle mandoDatosRegistro = new Bundle();
                mandoDatosRegistro.putStringArrayList("usuarios",usuarios);
                Intent intent = new Intent(MainActivity.this,RegistroActivity.class);
                intent.putExtras(mandoDatosRegistro);
                startActivity(intent);
                break;
            }
        }
    }
}