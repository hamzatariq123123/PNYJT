package com.pnytrainings.pnyjt1.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pnytrainings.pnyjt1.R;

public class MultiMediaActivity extends AppCompatActivity {

    Button btnCamra;
    Button btnGallery;

    ImageView displayImage;


    private static final int REQUEST_PERMISSIONS_CAMERA = 0;
    private static final int REQUEST_PERMISSIONS_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_media);

        btnCamra = findViewById(R.id.camera);
        btnGallery = findViewById(R.id.gallery);
        displayImage = findViewById(R.id.imageView);

        btnCamra.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                int result = ActivityCompat.checkSelfPermission(MultiMediaActivity.this,
                        Manifest.permission.CAMERA);

                if (result == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT > 22) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                REQUEST_PERMISSIONS_CAMERA);
                    }
                } else {
                    launchCamera();
                }

            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = ActivityCompat.checkSelfPermission(MultiMediaActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (result == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT > 22) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS_GALLERY);
                    }
                } else {
                    launchGallery();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSIONS_GALLERY){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                launchGallery();
            }else {
                Toast.makeText(this, "Gallery Permission is Denied", Toast.LENGTH_SHORT).show();
            }

        }else if(requestCode ==  REQUEST_PERMISSIONS_CAMERA){

            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                launchCamera();
            }else {
                Toast.makeText(this, "Camera Permission is Denied", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void launchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(takePictureIntent, REQUEST_PERMISSIONS_CAMERA);
    }

    public void launchGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, REQUEST_PERMISSIONS_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_PERMISSIONS_CAMERA) {
            if (resultCode == RESULT_OK) {

                // data is the varable carrying image data .

                Bundle extras = data.getExtras();

                //A bitmap is a type of memory organization or image file format
                // used to store digital
                // images. The term bitmap comes from the computer programming
                // terminology, meaning just
                // a map of bits, a spatially mapped array of bits.

                Bitmap imageBitmap = (Bitmap) extras.get("data");
                displayImage.setImageBitmap(imageBitmap);

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == REQUEST_PERMISSIONS_GALLERY) {
            if (resultCode == RESULT_OK) {
                if (data != null) {

                    Uri selectedImage = data.getData();

                    displayImage.setImageURI(selectedImage);
                }

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled gallery ", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }
}
