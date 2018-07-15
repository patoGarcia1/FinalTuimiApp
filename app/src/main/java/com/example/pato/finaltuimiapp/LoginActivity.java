package com.example.pato.finaltuimiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button botonEntrar;
    Button registrar;
    EditText usuario;
    EditText contraseña;
    static Usuario  usuarioNuevo;
    private Context context;
    private SharedPreferences sharedPreferences;
    TextView textnombre;
    TextView textmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrar = (Button) findViewById(R.id.registrar);
        botonEntrar = (Button) findViewById(R.id.botonEntrar);
        usuario = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contraseña);
        context = this;


        //usuario nuevo harcodeado
        usuarioNuevo = new Usuario(usuario, contraseña);


        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });


        if (!username.isEmpty() && !password.isEmpty()) {
            ingresoUsuario();
        } else {
            botonEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLogin(usuario.getText().toString(), contraseña.getText().toString())) {
                        sharedPreferences = context.getSharedPreferences(getResources().getString(R.string.app_name), MODE_PRIVATE);
                        sharedPreferences.edit()
                                .putString("username", usuarioNuevo.getNombre().toString())
                                .putString("password", usuarioNuevo.getPassword().toString())
                                .apply();
                        ingresoUsuario();
                    }else
                    {
                        mensajeErrorLogin();
                    }

                }
            });

        }
    }

    //private void ingresoUsuario (EditText usuario, EditText contraseña)
    private void ingresoUsuario ()
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        mensajeBienvenido();
    }

    private void mensajeBienvenido()
    {
        Toast toast1 = Toast.makeText(getApplicationContext(),"Hola "+usuarioNuevo.getNombre(),Toast.LENGTH_SHORT);
        toast1.show();
    }

    private void mensajeErrorLogin()
    {
        Toast toast1 = Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT);
        toast1.show();
    }

    public static Usuario getUsuarioNuevo()
    {
        return usuarioNuevo;
    }

    private boolean isLogin(String user, String pass) {
        return user.equals(usuarioNuevo.getNombre().toString()) && pass.equals(usuarioNuevo.getPassword().toString());
    }
}
