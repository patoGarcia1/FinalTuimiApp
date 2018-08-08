package com.example.pato.finaltuimiapp;

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

public class LoginActivity extends AppCompatActivity {

    private Button botonEntrar;
    private Button registrar;
    private EditText etUser;
    private EditText etPass;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrar = (Button) findViewById(R.id.registrar);
        botonEntrar = (Button) findViewById(R.id.botonEntrar);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference().child("usuarios").child("userId");



        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });


            botonEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(TextUtils.isEmpty("userId")){

                        Toast.makeText(getBaseContext(), "Registrate salame", Toast.LENGTH_LONG).show();
                    }
                    else{
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                User user = dataSnapshot.getValue(User.class);

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();

                            }
                        });
                        Toast.makeText(getBaseContext(), "Atroden", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
}
