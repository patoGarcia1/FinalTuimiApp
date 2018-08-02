package com.example.pato.finaltuimiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pato.finaltuimiapp.data.model.User;
import com.example.pato.finaltuimiapp.data.remote.APIService;
import com.example.pato.finaltuimiapp.data.remote.OnSuccessCallback;
import com.example.pato.finaltuimiapp.data.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button botonEntrar;
    Button registrar;
    EditText etUser;
    EditText etPass;
    static User usuarioNuevo;
    private Context context;
    private APIService APIServ;
    private SharedPreferences sharedPreferences;
    TextView textnombre;
    TextView textmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrar = (Button) findViewById(R.id.registrar);
        botonEntrar = (Button) findViewById(R.id.botonEntrar);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);



        //usuario nuevo harcodeado
        usuarioNuevo = new User(etUser, etPass);



        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });


            botonEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String usuario = etUser.getText().toString().trim();
                    final String contraseña =  etPass.getText().toString().trim();

                    RetrofitClient.getUser(new OnSuccessCallback() {
                        @Override
                        public void execute(Object body) {
                            //Lo que se debe hacer con la respuesta del servidor
                            //ListView postLv = (ListView) findViewById(R.id.postLv); //El listview
                            Toast.makeText(context, "Banca", Toast.LENGTH_SHORT).show();
                            //Le asigno el adapter, al cual le paso el contexto y la lista de posts que vino
                            //postLv.setAdapter(new PostAdapter(getBaseContext(), (List<Post>) body));

                        }
                    });



                }
            });

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
        Toast toast1 = Toast.makeText(getApplicationContext(),"Hola "+usuarioNuevo.getUser(),Toast.LENGTH_SHORT);
        toast1.show();
    }




}
