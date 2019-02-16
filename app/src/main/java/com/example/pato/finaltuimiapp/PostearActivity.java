package com.example.pato.finaltuimiapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pato.finaltuimiapp.model.Post;
import com.google.android.cameraview.CameraView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PostearActivity extends AppCompatActivity {

    private Context context;
    Button botonPostear;
    ImageView imageView;
    TextInputEditText descripcion;
    private Location loc;
    private LocationManager locManager;
    Post post;
    String imageString;
    String coordenada;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postear);

        context = this;
        botonPostear = (Button) findViewById(R.id.botonpostear);
        imageView = (ImageView) findViewById(R.id.image);
        descripcion = (TextInputEditText) findViewById(R.id.descripcion);

        Intent intent = getIntent();
        String sUrlImgage = intent.getStringExtra("urlImage");
        loadImageFromStorage(sUrlImgage);


        botonPostear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getBaseContext(), "No es posible acceder a las coordenadas", Toast.LENGTH_LONG).show();
                }else
                {

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.power);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                    locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    coordenada = "coordenada";
                    //coordenada = loc.getLongitude()+"@"+loc.getLatitude();
                    post = new Post(descripcion.toString(),imageString, coordenada);

                    createPost(descripcion.toString(), imageString, coordenada);
                }

                Intent main = new Intent(context, MainActivity.class);
                main.putExtra("desc",descripcion.toString());
                main.putExtra("imagen",imageString);
                main.putExtra("coord",coordenada);
                startActivity(main);
            }
        });


    }

    private void createPost(String desc, String imagen, String coord) {
        if (TextUtils.isEmpty("userId")) {
            userId = databaseReference.push().getKey();
        }
        Post post = new Post(desc, imagen, coord);

        databaseReference.child(userId).setValue(post);


        addPostChange();
    }

    public void addPostChange() {
        databaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Post post= dataSnapshot.getValue(Post.class);

                // Check for null
                if (post == null) {
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
    private void loadImageFromStorage(String path)
    {
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.image);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
