package com.example.appaptiendamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText etNombre,etApellidoPaterno,etApellidoMaterno,etCorreo,etContrasena,etTelefono;

    public Button btnRegistrar;

    public TextView tvIniciasesion;

    private Bundle reciboDatos = new Bundle();

    private ArrayList<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        reciboDatos = getIntent().getExtras();
        datos = reciboDatos.getStringArrayList("usuarios");
        etNombre = findViewById(R.id.etNombre);
        etApellidoPaterno = findViewById(R.id.etApellidoPaterno);
        etApellidoMaterno = findViewById(R.id.etApellidoMaterno);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etTelefono = findViewById(R.id.etTelefono);


        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvIniciasesion = findViewById(R.id.tvIniciasesion);

        btnRegistrar.setOnClickListener(this);
        tvIniciasesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistrar:{
                if(camposVacios()==false){
                    Toast.makeText(this, "Error, alguno de los campos no tiene que estar vacio", Toast.LENGTH_SHORT).show();
                }else{
                    if(etTelefono.getText().toString().length()==10){
                        //agrego en el ArraayList
                        datos.add(etCorreo.getText().toString());
                        datos.add(etContrasena.getText().toString());
                        datos.add(etNombre.getText().toString());

                        Bundle mandoDatosRegistro = new Bundle();
                        mandoDatosRegistro.putStringArrayList("usuarios",datos);
                        Intent intent = new Intent(RegistroActivity.this,MainActivity.class);
                        intent.putExtras(mandoDatosRegistro);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this, "El numero telefonico debe ser de 10 digitos", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
            case R.id.tvIniciasesion:{
                Bundle mandoDatos = new Bundle();
                mandoDatos.putStringArrayList("usuarios",datos);
                Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
                intent.putExtras(mandoDatos);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

    private boolean camposVacios() {
        boolean regreso = false;
        if(!(etNombre.getText().toString().isEmpty() || etApellidoPaterno.getText().toString().isEmpty() || etApellidoMaterno.getText().toString().isEmpty() ||
                etCorreo.getText().toString().isEmpty() || etContrasena.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty()) ){
            regreso = true;
        }
        return regreso;
    }
}