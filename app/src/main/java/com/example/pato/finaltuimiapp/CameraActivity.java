package com.example.pato.finaltuimiapp;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.cameraview.CameraView;
import com.google.android.cameraview.CameraViewImpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {


    private Context context;
    CameraView cameraView;
    Button botonClickCamera;
    Button botonRotarCamera;
    Bitmap image;
    Intent postear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        context = this;
        botonClickCamera = (Button) findViewById(R.id.clickcamera);
        botonRotarCamera = (Button) findViewById(R.id.rotarcamera);
        cameraView = (CameraView) findViewById(R.id.camera_view);


        //permisos
        if(!isCameraPermissionGranted())
            checkCameraPermission();
        if(!isStoragePermissionGranted())
            checkStoragePermission();
        if(!isLocationPermissionGranted())
            checkLocationPermission();

        cameraView.setOnPictureTakenListener(new CameraViewImpl.OnPictureTakenListener() {
            @Override
            public void onPictureTaken(Bitmap bitmap, int rotationDegrees) {
                image = startSavingPhoto(bitmap, rotationDegrees);
            }
        });

        botonClickCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    cameraView.takePicture();

                new CountDownTimer(3000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        Toast.makeText(context, "Procesando..."+(millisUntilFinished/1000), Toast.LENGTH_SHORT).show();
                       // final ProgressDialog progressDialog = ProgressDialog.show(context, "Imagen",
                       //         "Procesando...", false, false);
                    }
                    public void onFinish() {
                        postear = new Intent(context, PostearActivity.class);
                        String sUrl = saveToInternalStorage(image);
                        postear.putExtra("urlImage",sUrl);
                        startActivity(postear);
                    }
                }.start();
            }
        });

        botonRotarCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.switchCamera();
            }
        });

        cameraView.setOnCameraErrorListener(new CameraViewImpl.OnCameraErrorListener() {
            @Override
            public void onCameraError(Exception e) {
                Toast.makeText(CameraActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        cameraView.setOnTurnCameraFailListener(new CameraViewImpl.OnTurnCameraFailListener() {
            @Override
            public void onTurnCameraFail(Exception e) {
                Toast.makeText(CameraActivity.this, "Switch Camera Failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isStoragePermissionGranted() && isCameraPermissionGranted()) {
            cameraView.start();
        } else {
            if (!isCameraPermissionGranted())
                checkCameraPermission();
            if (!isStoragePermissionGranted())
                checkStoragePermission();
        }
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    private Bitmap startSavingPhoto(Bitmap bitmap, int rotationDegrees){
        Matrix matrix = new Matrix();
        matrix.postRotate(-rotationDegrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private boolean isCameraPermissionGranted(){
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED);
    }
    private boolean isStoragePermissionGranted(){
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED);
    }
    private boolean isLocationPermissionGranted(){
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }
    private void checkCameraPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
    }
    private void checkStoragePermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }
    private void checkLocationPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }


    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

}
