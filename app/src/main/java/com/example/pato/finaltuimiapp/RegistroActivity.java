package com.example.pato.finaltuimiapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pato.finaltuimiapp.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegistroActivity extends AppCompatActivity {

    private static Context context;
    EditText etUser;
    EditText  etEmail;
    EditText etPass;
    EditText etPass2;
    Button bt1;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUser = (EditText) findViewById(R.id.etUser);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        etPass2 = (EditText) findViewById(R.id.etPass2);
        bt1 = (Button) findViewById(R.id.bt1);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("usuarios");


        bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        String usuario = etUser.getText().toString();
                        String correo = etEmail.getText().toString();
                        String contraseña = etPass.getText().toString();
                    if (TextUtils.isEmpty(userId)) {
                        createUser(usuario, contraseña, correo);

                        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Usuario ya registrado", Toast.LENGTH_LONG).show();
                    }
                    }

        }
        );}

    private void createUser(String usuario, String correo, String contraseña) {
        if (TextUtils.isEmpty(userId)) {
            userId = databaseReference.push().getKey();
        }
        User user = new User(usuario, correo, contraseña);

        databaseReference.child(userId).setValue(user);
        /*databaseReference.child("usuario").child(userId).setValue(usuario);
        databaseReference.child("contraseña").child(userId).setValue(contraseña);
        databaseReference.child("correo").child(userId).setValue(correo);*/

        addUserChange();
    }

    public void addUserChange(){
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Toast.makeText(getBaseContext(), "ciova!", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getBaseContext(), "Agregado!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getBaseContext(), "Cancela!", Toast.LENGTH_LONG).show();
            }
        });
    }

}