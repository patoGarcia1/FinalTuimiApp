package com.example.pato.finaltuimiapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pato.finaltuimiapp.data.model.User;
import com.example.pato.finaltuimiapp.data.remote.APIService;
import com.example.pato.finaltuimiapp.data.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private APIService APIServ;
    private static Context context;
    EditText etUser;
    EditText  etEmail;
    EditText etPass;
    EditText etPass2;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUser = (EditText) findViewById(R.id.etUser);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        etPass2 = (EditText) findViewById(R.id.etPass2);
        bt1 = (Button) findViewById(R.id.bt1);

        APIServ = APIUtils.getAPIService();



        bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        String usuario = etUser.getText().toString().trim();
                        String correo = etEmail.getText().toString().trim();
                        String contrasena = etPass.getText().toString().trim();
                        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(correo) &&
                                !TextUtils.isEmpty(contrasena)) {
                            registrarUser(usuario, correo, contrasena);
                            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }


        }
        );}

        public void registrarUser(String usuario, String correo, String contrasena) {
            APIServ.saveUser(usuario, correo, contrasena).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if(response.isSuccessful()) {
                        Toast.makeText(context, "Registro efectuado", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(context, "ERROR - Usuario ya registrado", Toast.LENGTH_LONG).show();                }
            });


        }


    }

