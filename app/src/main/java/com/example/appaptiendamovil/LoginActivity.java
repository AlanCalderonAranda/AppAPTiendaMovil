package com.example.appaptiendamovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText etUsuario,etContra;
    public Button btnEntrar;
    public TextView tvRegister;

    ArrayList<String> usuarios = new ArrayList<>();

    private Bundle reciboUsuarios = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Recibo los usuarios
        reciboUsuarios = getIntent().getExtras();
        usuarios = reciboUsuarios.getStringArrayList("usuarios");
        etUsuario = findViewById(R.id.etUsuario);
        etContra = findViewById(R.id.etContra);
        btnEntrar = findViewById(R.id.btnEntrar);
        tvRegister = findViewById(R.id.tvRegister);

        btnEntrar.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEntrar:{
                if(etUsuario.getText().toString().isEmpty()){
                    Toast.makeText(this, "Ingresa un usuario", Toast.LENGTH_SHORT).show();
                }else{
                    if(etContra.getText().toString().isEmpty()){
                        Toast.makeText(this, "Ingresa Contrasena", Toast.LENGTH_SHORT).show();
                    }else{
                        String expresionRegular = "^\\w+@gmail\\.com$";
                        Pattern pattern = Pattern.compile(expresionRegular);
                        Matcher matcher = pattern.matcher(etUsuario.getText().toString());
                        if(matcher.matches()){
                            //Tengo que ver si el correo y la contrasena esta en mi BD
                            boolean bandera=false;
                            String nombre = "";
                            for(int i=0;i<usuarios.size()/3;i++){
                                if(usuarios.get(i*3).equalsIgnoreCase(etUsuario.getText().toString()) && usuarios.get((i*3)+1).equalsIgnoreCase(etContra.getText().toString())){
                                    nombre = usuarios.get((i*3)+2);
                                    bandera=true;
                                    break;
                                }
                            }
                            if(bandera==true){
                                Bundle mandoDatos = new Bundle();
                                mandoDatos.putString("usuario",nombre);
                                Intent intent = new Intent(LoginActivity.this,InicioActivity.class);
                                intent.putExtras(mandoDatos);
                                startActivity(intent);
                                finish();
                                break;
                            }else
                                Toast.makeText(this, "El usuario que ingresaste no existe o su contrasena es incorrecta", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this, "El correo debe ser @gmail.com", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                break;
            }
            case R.id.tvRegister:{
                Bundle mandoDatosRegistro = new Bundle();
                mandoDatosRegistro.putStringArrayList("usuarios",usuarios);
                Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
                intent.putExtras(mandoDatosRegistro);
                startActivity(intent);
                break;
            }
        }
    }
}